package com.tsing.grid.service.app;

import com.tsing.grid.vo.req.DrugerAddReqVO;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.resp.*;

import java.util.List;

public interface DrugerService {
    PageRespVO<DrugerPageRespVO> findPage(DrugerPageVO vo);
    List<DrugerPageRespVO> getList(DrugerPageVO vo);
    DrugerDetailResp detail(Integer id);
    void delete(List<Integer> ids);
    List<ChartDataRespVO> chartByCid();
    List<ChartDataRespVO> chartByGrade();
    List<ChartDataRespVO> chartByTime();

    void insert(DrugerAddReqVO vo);

}
