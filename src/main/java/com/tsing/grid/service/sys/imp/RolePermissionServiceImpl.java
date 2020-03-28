package com.tsing.grid.service.sys.imp;

import com.tsing.grid.entity.sys.RolePermission;
import com.tsing.grid.mapper.RolePermissionMapper;
import com.tsing.grid.service.sys.RolePermissionService;
import com.tsing.grid.vo.req.RolePermissionOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;


	@Override
	public void deleteByRid(Integer rid) {
		rolePermissionMapper.deleteByRid(rid);
	}

	@Override
	public List<Integer> getPidsByRids(List<Integer> roleIds) {
		return rolePermissionMapper.getPidsByRids(roleIds);
	}

	@Override
	public void setRolePermission(RolePermissionOperationReqVO vo) {
		Date cdt = new Date();
		Integer rid = vo.getRid();
		/**
		 * step 1
		 * 删除现有的关系
		 */
		rolePermissionMapper.deleteByRid(rid);

		/**
		 * step 2
		 * 将现有的添加
		 */
		List<RolePermission> rolePermissions = new ArrayList<>();
		for (Integer permissionId:vo.getPermissionIds()){
			RolePermission rolePermission = new RolePermission();
			rolePermission.setPid(permissionId);
			rolePermission.setRid(rid);
			rolePermission.setCreateTime(cdt);
			rolePermissions.add(rolePermission);
		}
		rolePermissionMapper.setRolePermission(rolePermissions);
	}

	@Override
	public void deleteById(Integer pid) {
		rolePermissionMapper.deleteByPid(pid);
	}

	@Override
	public List<Integer> getRids(Integer permissionId) {
		return rolePermissionMapper.getRoleIds(permissionId);
	}

	@Override
	public List<Integer> getPidsByRid(Integer roleId) {
		return rolePermissionMapper.getPermissionIdsByRoleId(roleId);
	}
}
