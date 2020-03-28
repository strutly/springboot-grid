package com.tsing.grid.service.sys;


import com.tsing.grid.entity.sys.SysLog;
import com.tsing.grid.vo.req.SysLogPageReqVO;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;


public interface LogService {

    PageRespVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void insert(SysLog sysLog);

    void delete(List<Integer> logIds);
}
