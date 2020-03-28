package com.tsing.grid.mapper;

import com.tsing.grid.config.BaseMapper;
import com.tsing.grid.entity.app.Druger;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.resp.ChartDataRespVO;
import com.tsing.grid.vo.resp.DrugerPageRespVO;
import com.tsing.grid.vo.resp.DrugerDetailResp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugerMapper extends BaseMapper<Druger> {

    List<DrugerPageRespVO> findPage(DrugerPageVO vo);

    DrugerDetailResp detail(Integer id);

    List<ChartDataRespVO> chartByCid();

    List<ChartDataRespVO> chartByGrade();

    List<ChartDataRespVO> chartByTime();
}
