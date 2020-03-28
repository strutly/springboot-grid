package com.tsing.grid.service.sys;

import com.tsing.grid.vo.req.AdminRoleOperationReqVO;

import java.util.List;

public interface AdminRoleService {

	void deleteByAid(Integer aid);
	
	void deleteByRid(Integer rid);
	
	List<Integer> getRidsByAid(Integer aid);

	void setAdminRole(AdminRoleOperationReqVO vo);
}

