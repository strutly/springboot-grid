package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.sys.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    Admin findByLoginName(String id);
}
