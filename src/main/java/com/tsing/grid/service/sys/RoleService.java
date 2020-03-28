package com.tsing.grid.service.sys;

import com.tsing.grid.entity.sys.Role;
import com.tsing.grid.vo.req.RoleAddReqVO;
import com.tsing.grid.vo.req.RolePageReqVO;
import com.tsing.grid.vo.req.RoleUpdateReqVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;

public interface RoleService {

    Role addRole(RoleAddReqVO vo);

    void updateRole(RoleUpdateReqVO vo);

    Role detailInfo(Integer id);

    void deletedRole(Integer id);

    PageRespVO<Role> pageInfo(RolePageReqVO vo);

    List<Role> getRoleInfoByUserId(Integer userId);

    List<String> getRoleNames(Integer userId);

    List<Role> selectAllRoles();

    void deleteByAid(Integer aid);
}
