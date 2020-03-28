package com.tsing.grid.service.sys.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.sys.Role;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.RoleMapper;
import com.tsing.grid.service.sys.AdminRoleService;
import com.tsing.grid.service.sys.PermissionService;
import com.tsing.grid.service.sys.RolePermissionService;
import com.tsing.grid.service.sys.RoleService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.RoleAddReqVO;
import com.tsing.grid.vo.req.RolePageReqVO;
import com.tsing.grid.vo.req.RolePermissionOperationReqVO;
import com.tsing.grid.vo.req.RoleUpdateReqVO;
import com.tsing.grid.vo.resp.PageRespVO;
import com.tsing.grid.vo.resp.PermissionRespNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private AdminRoleService adminRoleService;


	@Override
	public Role addRole(RoleAddReqVO vo) {
		Role role = new Role();
		role.setDescription(vo.getDescription());
		role.setName(vo.getName());
		role.setStatus(vo.getStatus());
		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		roleMapper.insert(role);
		return role;
	}

	@Override
	public void updateRole(RoleUpdateReqVO vo) {
		Role sysRole = roleMapper.selectByPrimaryKey(vo.getId());
		if (null==sysRole){
			log.error("传入 的 id:{}不合法",vo.getId());
			throw new BusinessException(BaseExceptionType.USER_ERROR,"id不合法");
		}

		BeanUtils.copyProperties(vo,sysRole);
		sysRole.setUpdateTime(new Date());
		roleMapper.updateByPrimaryKeySelective(sysRole);

		rolePermissionService.deleteByRid(vo.getId());

		if( null != vo.getPermissions() && !vo.getPermissions().isEmpty()){

			RolePermissionOperationReqVO reqVO = new RolePermissionOperationReqVO();
			reqVO.setRid(vo.getId());
			reqVO.setPermissionIds(vo.getPermissions());
			rolePermissionService.setRolePermission(reqVO);
		}
	}

	@Override
	public Role detailInfo(Integer id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		if(role==null){
			log.error("传入 的 id:{}不合法",id);
			throw new BusinessException(BaseExceptionType.USER_ERROR,"id不合法");
		}
		List<PermissionRespNode> permissionRespNodes = permissionService.selectAllByTree();

		Set<Integer> checkList = new HashSet<>(rolePermissionService.getPidsByRid(role.getId()));
		setheckced(permissionRespNodes,checkList);
		role.setPermissionRespNodes(permissionRespNodes);

		return role;
	}

	private void setheckced(List<PermissionRespNode> list, Set<Integer> checkList){
		for(PermissionRespNode node:list){
			if(checkList.contains(node.getId())&&(node.getChildren()==null||node.getChildren().isEmpty())){
				node.setChecked(true);
			}
			setheckced((List<PermissionRespNode>) node.getChildren(),checkList);
		}
	}


	@Override
	public void deletedRole(Integer rid) {
		/**
		 * step1
		 * 删除admin_role
		 *
		 */
		adminRoleService.deleteByRid(rid);
		/**
		 * step2
		 * 删除 role_permission
		 */
		rolePermissionService.deleteByRid(rid);
		/**
		 * step3
		 * 删除该role
		 */
		roleMapper.deleteByPrimaryKey(rid);
	}

	@Override
	public PageRespVO<Role> pageInfo(RolePageReqVO vo) {
		PageHelper.startPage(vo.getPageNum(),vo.getPageSize());

		Example example = new Example(Role.class);
		Example.Criteria criteria = example.createCriteria();

		if(!StringUtils.isEmpty(vo.getRoleName())){
			criteria.andLike("name", "%"+vo.getRoleName()+"%");
		}

		if(!StringUtils.isEmpty(vo.getStatus())){
			criteria.andEqualTo("status", vo.getStatus());
		}

		List<Role> list = roleMapper.selectByExample(example);

		return PageUtils.getPageVO(list);
	}


	@Override
	public List<Role> getRoleInfoByUserId(Integer aid) {

		List<Integer> roleIds= adminRoleService.getRidsByAid(aid);
		if (roleIds.isEmpty()){
			return null;
		}

		Example example = new Example(Role.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", roleIds);
		return roleMapper.selectByExample(example);
	}

	@Override
	public List<String> getRoleNames(Integer aid) {
		List<Role> roles =  getRoleInfoByUserId(aid);
		List<String> names = roles.stream().map(role -> role.getName()).collect(Collectors.toList());
		return names;
	}

	@Override
	public List<Role> selectAllRoles() {
		return roleMapper.selectAll();
	}

	@Override
	public void deleteByAid(Integer aid) {
		Example example = new Example(Role.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("aid", aid);
		roleMapper.selectByExample(example);
	}

}
