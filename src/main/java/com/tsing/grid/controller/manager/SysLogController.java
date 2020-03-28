package com.tsing.grid.controller.manager;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.sys.SysLog;
import com.tsing.grid.service.sys.LogService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.SysLogPageReqVO;
import com.tsing.grid.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/manager/sys")
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class SysLogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    //@RequiresPermissions("sys:log:list")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理",action = "分页查询系统操作日志")
    public DataResult<PageRespVO<SysLog>> pageInfo(SysLogPageReqVO vo){
        DataResult<PageRespVO<SysLog>> result = DataResult.success();
        result.setData(logService.pageInfo(vo));
        return result;
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    //@RequiresPermissions("sys:log:deleted")
    @LogAnnotation(title = "系统操作日志管理",action = "删除系统操作日志")
    public DataResult deleted(@RequestBody List<Integer> logIds){
        logService.delete(logIds);
        return DataResult.success();
    }
}
