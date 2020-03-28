package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.app.Grid;
import com.tsing.grid.vo.req.GridPageReqVO;
import com.tsing.grid.vo.resp.GridDataRespVO;
import com.tsing.grid.vo.resp.GridPageRespVO;
import com.tsing.grid.vo.resp.GridRespVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GridMapper extends BaseMapper<Grid> {

    List<GridRespVO> selectByCid(Integer cid);

    List<GridDataRespVO> findByName(String name);

    GridPageRespVO detail(Integer id);

    List<GridPageRespVO> getList(GridPageReqVO vo);
}
