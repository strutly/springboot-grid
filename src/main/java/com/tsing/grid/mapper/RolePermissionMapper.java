package com.tsing.grid.mapper;

import com.tsing.grid.entity.sys.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper{
    List<Integer> getPidsByRids(List<Integer> rids);
    List<Integer> getRoleIds(Integer permissionId);
    List<Integer> getPermissionIdsByRoleId(Integer roleId);

    void setRolePermission(List<RolePermission> rolePermissions);

    void deleteByRid(Integer rid);

    void deleteByPid(Integer pid);

}
