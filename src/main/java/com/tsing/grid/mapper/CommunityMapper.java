package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.app.Community;
import com.tsing.grid.vo.resp.CommunityRespVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityMapper extends BaseMapper<Community> {
    Community findByName(String name);
    CommunityRespVO findAndGrids(Integer id);
    List<CommunityRespVO> statistics();
}
