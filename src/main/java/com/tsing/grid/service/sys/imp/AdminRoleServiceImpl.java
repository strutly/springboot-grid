package com.tsing.grid.service.sys.imp;

import com.tsing.grid.entity.sys.AdminRole;
import com.tsing.grid.mapper.AdminRoleMapper;
import com.tsing.grid.service.sys.AdminRoleService;
import com.tsing.grid.vo.req.AdminRoleOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminRoleServiceImpl  implements AdminRoleService {
	
	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Override
	public void deleteByAid(Integer aid) {
        adminRoleMapper.deleteByAid(aid);
	}

	@Override
	public void deleteByRid(Integer rid) {
        adminRoleMapper.deleteByRid(rid);
	}

	@Override
	public List<Integer> getRidsByAid(Integer  aid) {
		return adminRoleMapper.getRidsByAid(aid);
	}

	@Override
	public void setAdminRole(AdminRoleOperationReqVO vo) {
		List<AdminRole> adminRoles = new ArrayList<>();
		Integer aid = vo.getAid();
		Date cdt = new Date();
		for(Integer rid:vo.getRids()){
			AdminRole adminRole = new AdminRole();
			adminRole.setAid(aid);
			adminRole.setRid(rid);
			adminRole.setCreateTime(cdt);
			adminRoles.add(adminRole);
		}
		deleteByAid(vo.getAid());
		adminRoleMapper.setAdminRole(adminRoles);
	}
}
