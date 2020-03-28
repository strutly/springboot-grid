package com.tsing.grid.service.sys;

import com.tsing.grid.vo.req.RolePermissionOperationReqVO;

import java.util.List;

public interface RolePermissionService {

	void deleteByRid(Integer rid);

	List<Integer> getPidsByRids(List<Integer> roleIds);

	void setRolePermission(RolePermissionOperationReqVO vo);

	void deleteById(Integer id);

	List<Integer> getRids(Integer pid);

	List<Integer> getPidsByRid(Integer rid);
}

