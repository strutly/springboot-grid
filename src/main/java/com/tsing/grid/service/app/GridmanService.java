package com.tsing.grid.service.app;

import com.tsing.grid.entity.app.Gridman;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.req.GridmanPageVO;
import com.tsing.grid.vo.resp.DrugerDetailResp;
import com.tsing.grid.vo.resp.DrugerPageRespVO;
import com.tsing.grid.vo.resp.GridmanRespVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;

public interface GridmanService {

    PageRespVO<GridmanRespVO> findPage(GridmanPageVO vo);

    List<GridmanRespVO> getList(GridmanPageVO vo);

    GridmanRespVO detail(Integer id);
}
