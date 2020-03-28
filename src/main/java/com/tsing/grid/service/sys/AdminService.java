package com.tsing.grid.service.sys;

import com.tsing.grid.entity.sys.Admin;
import com.tsing.grid.vo.req.AdminAddReqVO;
import com.tsing.grid.vo.req.AdminPageReqVO;
import com.tsing.grid.vo.req.AdminPwdReqVO;
import com.tsing.grid.vo.req.AdminUpdateReqVO;
import com.tsing.grid.vo.resp.AdminRoleRespVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;


public interface AdminService {
	void insert(AdminAddReqVO admin);
	void update(AdminUpdateReqVO vo);
	void deleteAdmins(List<Integer> ids);
	void updatePwd(Integer id, AdminPwdReqVO admin);
	List<Admin> findAll();
	Admin findByLoginName(String loginName);
	PageRespVO<Admin> pageInfo(AdminPageReqVO adminPageReqVO);
    AdminRoleRespVO getAdminRole(Integer aid);
	void setAdminRole(Integer userId, List<Integer> roleIds);
}
