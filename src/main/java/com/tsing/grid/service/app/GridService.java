package com.tsing.grid.service.app;

import com.tsing.grid.entity.app.Grid;
import com.tsing.grid.vo.req.GridPageReqVO;
import com.tsing.grid.vo.resp.GridDataRespVO;
import com.tsing.grid.vo.resp.GridPageRespVO;
import com.tsing.grid.vo.resp.GridRespVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;

public interface GridService {

    PageRespVO<GridPageRespVO> findPage(GridPageReqVO vo);

    List<GridRespVO> selectByCid(Integer cid);

    List<GridDataRespVO> findByName(String name);

}
