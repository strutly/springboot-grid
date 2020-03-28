package com.tsing.grid.mapper;

import com.tsing.grid.entity.sys.AdminRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleMapper{
    void setAdminRole(List<AdminRole> adminRoles);
    List<Integer> getRidsByAid(Integer aid);
    void deleteByRid(Integer rid);
    void deleteByAid(Integer aid);
	
}

