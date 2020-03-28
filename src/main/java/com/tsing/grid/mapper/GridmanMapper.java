package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.app.Gridman;
import com.tsing.grid.vo.req.GridmanPageVO;
import com.tsing.grid.vo.resp.GridmanDataRespVO;
import com.tsing.grid.vo.resp.GridmanRespVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GridmanMapper extends BaseMapper<Gridman> {
    GridmanRespVO detail(Integer id);
    List<GridmanRespVO> getList(GridmanPageVO vo);

    GridmanDataRespVO detail2(Integer id);
}
