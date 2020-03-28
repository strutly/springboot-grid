package com.tsing.grid.service.sys.imp;

import com.tsing.grid.entity.sys.Permission;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.AdminRoleMapper;
import com.tsing.grid.mapper.PermissionMapper;
import com.tsing.grid.mapper.RolePermissionMapper;
import com.tsing.grid.service.sys.PermissionService;
import com.tsing.grid.vo.req.PermissionAddReqVO;
import com.tsing.grid.vo.req.PermissionUpdateReqVO;
import com.tsing.grid.vo.resp.PermissionRespNode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public Permission selectByPrimaryKey(Integer id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insert(PermissionAddReqVO vo) {
		Permission permission=new Permission();
		BeanUtils.copyProperties(vo,permission);
		permission.setCreateTime(new Date());
		permission.setUpdateTime(new Date());
		int count = permissionMapper.insert(permission);
		if(count!=1){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"保存失败,请稍后再试");
		}
	}

	@Override
	public void deleteByPrimaryKey(Integer pid) {
		Permission permission = permissionMapper.selectByPrimaryKey(pid);
		/**
		 * step1
		 * 判断是否有下级元素
		 */
		List<Permission> childs = permissionMapper.selectChild(pid);
		if(!childs.isEmpty()){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"该菜单权限存在子集关联，不允许删除");
		}
		/**
		 * step2
		 * 删除role_permission
		 */
		rolePermissionMapper.deleteByPid(pid);
		/**
		 * step3
		 * 删除该permission
		 */
		permissionMapper.deleteByPrimaryKey(pid);

	}

	@Override
	public void update(PermissionUpdateReqVO vo) {
		Permission permission = permissionMapper.selectByPrimaryKey(vo.getId());
		if(null == permission){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"修改对象不存在!");
		}
		/**
		 * 当类型变更或者是父级变更时进行验证
		 */
		if(permission.getType()!=vo.getType()||!permission.getPid().equals(vo.getPid())){
			verify(vo);
		}
		Permission update = new Permission();
		BeanUtils.copyProperties(vo,update);
		update.setUpdateTime(new Date());
		int count = permissionMapper.updateByPrimaryKeySelective(update);
		if(count!=1){
			throw new BusinessException(BaseExceptionType.USER_ERROR,"保存失败,请稍后再试");
		}
	}


	/**
	 * 操作后的菜单类型是目录的时候 父级必须为目录
	 * 操作后的菜单类型是菜单的时候 父类必须为目录类型
	 * 操作后的菜单类型是按钮的时候 父类必须为菜单类型
	 * 操作的不能有
	 */
	private void verify(PermissionUpdateReqVO vo){
		Permission parent = permissionMapper.selectByPrimaryKey(vo.getPid());
		switch (vo.getType()){
			case 1:
				if(parent!=null){
					if(parent.getType()!=1){
						throw new BusinessException(BaseExceptionType.USER_ERROR,"父类必须为目录类型");
					}
				}else if(!vo.getPid().equals("0")){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"父类必须为目录类型");
				}
				break;
			case 2:
				if(parent==null||parent.getType()!=1){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"父类必须为目录类型");
				}
				if(StringUtils.isEmpty(vo.getUrl())){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"请添加地址URL");
				}
				break;
			case 3:
				if(parent==null || parent.getType()!=2){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"父类必须为菜单类型");
				}
				if(StringUtils.isEmpty(vo.getPerms())){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"请添加授权码");
				}
				if(StringUtils.isEmpty(vo.getUrl())){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"请添加地址URL");
				}
				if(StringUtils.isEmpty(vo.getMethod())){
					throw new BusinessException(BaseExceptionType.USER_ERROR,"请添加方法类型");
				}
				break;
		}
		/**
		 * id 不为空表明为修改
		 */
		if(!StringUtils.isEmpty(vo.getId())) {
			List<Permission> list = permissionMapper.selectChild(vo.getId());
			if (!list.isEmpty()) {
				throw new BusinessException(BaseExceptionType.USER_ERROR,"子级菜单不为空,请重新修改");
			}
		}

	}

	@Override
	public List<Permission> selectAll() {
		List<Permission> permissions = permissionMapper.selectAll();
		for(Permission permission:permissions){
			Permission parent = permissionMapper.selectByPrimaryKey(permission.getPid());
			if(null!=parent){
				permission.setPidName(parent.getName());
			}
		}
		return permissions;
	}

	@Override
	public List<Permission> getPermission(Integer aid) {
		List<Integer> roleIds = adminRoleMapper.getRidsByAid(aid);
		if(roleIds.isEmpty()){
			return null;
		}
		List<Integer> permissionIds = rolePermissionMapper.getPidsByRids(roleIds);
		if (permissionIds.isEmpty()){
			return null;
		}
		Example example = new Example(Permission.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", permissionIds);
		List<Permission> result = permissionMapper.selectByExample(example);
		return result;
	}

	@Override
	public List<String> getPNames(Integer aid) {
		List<Permission> permissions = getPermission(aid);
		List<String> names = permissions.stream().map(permission -> permission.getName()).collect(Collectors.toList());
		return names;
	}

	@Override
	public List<PermissionRespNode> permissionTreeList(Integer userId) {
		List<Permission> list = getPermission(userId);
		return getTree(list,true);
	}

	private List<PermissionRespNode> getTree(List<Permission> all,boolean type){

		List<PermissionRespNode> list=new ArrayList<>();
		if (all==null||all.isEmpty()){
			return list;
		}
		for(Permission permission:all){
			if(permission.getPid().equals(0)){
				PermissionRespNode permissionRespNode=new PermissionRespNode();
				BeanUtils.copyProperties(permission,permissionRespNode);
				permissionRespNode.setTitle(permission.getName());

				if(type){
					permissionRespNode.setChildren(getChildExcBtn(permission.getId(),all));
				}else {
					permissionRespNode.setChildren(getChildAll(permission.getId(),all));
				}
				list.add(permissionRespNode);
			}
		}
		return list;
	}

	private List<PermissionRespNode>getChildAll(Integer id,List<Permission> all){

		List<PermissionRespNode> list=new ArrayList<>();
		for(Permission permission:all){
			if(permission.getPid().equals(id)){
				PermissionRespNode permissionRespNode=new PermissionRespNode();
				BeanUtils.copyProperties(permission,permissionRespNode);
				permissionRespNode.setTitle(permission.getName());
				permissionRespNode.setChildren(getChildAll(permission.getId(),all));
				list.add(permissionRespNode);
			}
		}
		return list;
	}

	private List<PermissionRespNode> getChildExcBtn(Integer id,List<Permission> all){

		List<PermissionRespNode> list = new ArrayList<>();
		for(Permission permission:all){
			if(permission.getPid().equals(id)&&permission.getType()!=3){
				PermissionRespNode permissionRespNode=new PermissionRespNode();
				BeanUtils.copyProperties(permission,permissionRespNode);
				permissionRespNode.setTitle(permission.getName());
				permissionRespNode.setChildren(getChildExcBtn(permission.getId(),all));
				list.add(permissionRespNode);
			}
		}
		return list;
	}

	@Override
	public List<PermissionRespNode> selectAllByTree() {
		List<Permission> list = selectAll();
		return getTree(list,false);
	}

	@Override
	public Set<String> getPermsByAid(Integer aid) {
		List<Permission> permissions = getPermission(aid);
		Set<String> perms = new HashSet<>();
		for(Permission permission:permissions){
			if(!StringUtils.isEmpty(permission.getPerms())){
				perms.add(permission.getPerms());
			}
		}
		return perms;
	}

	@Override
	public List<PermissionRespNode> selectAllMenuByTree(Integer permissionId) {

		List<Permission> list=selectAll();
		if(!list.isEmpty()&&!StringUtils.isEmpty(permissionId)){
			for (Permission permission:list){
				if (permission.getId().equals(permissionId)){
					list.remove(permission);
					break;
				}
			}
		}
		List<PermissionRespNode> result=new ArrayList<>();
		//新增顶级目录是为了方便添加一级目录
		PermissionRespNode respNode=new PermissionRespNode();
		respNode.setId(0);
		respNode.setTitle("默认顶级菜单");
		respNode.setSpread(true);
		respNode.setChildren(getTree(list,true));
		result.add(respNode);
		return result;
	}
}
