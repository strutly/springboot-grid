package com.tsing.grid.config;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BaseMapper<T> extends Mapper<T>{
     
}