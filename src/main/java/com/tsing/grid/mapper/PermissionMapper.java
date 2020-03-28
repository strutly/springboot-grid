package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.sys.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> selectChild(Integer pid);
}
