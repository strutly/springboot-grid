package com.tsing.grid.service.app;

import com.tsing.grid.entity.app.Community;
import com.tsing.grid.vo.PolygonVO;
import com.tsing.grid.vo.req.CommunityPageReqVO;
import com.tsing.grid.vo.resp.CommunityRespVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;

public interface CommunityService {

    void insert(PolygonVO vo);

    CommunityRespVO detail(Integer id);

    List<Community> getAll();

    PageRespVO<Community> pageInfo(CommunityPageReqVO vo);

    List<CommunityRespVO> statistics();
}
