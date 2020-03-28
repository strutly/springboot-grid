package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.app.Street;
import com.tsing.grid.entity.sys.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetMapper extends BaseMapper<Street> {
    Integer maxId();
}
