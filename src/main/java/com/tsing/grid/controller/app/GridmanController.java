package com.tsing.grid.controller.app;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.app.Gridman;
import com.tsing.grid.service.app.GridmanService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.util.ExcelUtils;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.req.GridmanPageVO;
import com.tsing.grid.vo.resp.DrugerPageRespVO;
import com.tsing.grid.vo.resp.GridmanRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author lijing
 *
 */
@RestController
@RequestMapping(value = "/manager")
@Api(tags = "应用模块-网格员管理")
public class GridmanController{

    @Autowired
    private GridmanService gridmanService;

    @GetMapping("gridmans")
    @ApiOperation(value = "网格员列表接口")
    @RequiresPermissions("app:gridman:list")
    @LogAnnotation(title = "网格员管理", action = "分页获取网格员列表")
    public DataResult<PageRespVO<GridmanRespVO>> pageInfo(GridmanPageVO vo) {
        DataResult<PageRespVO<GridmanRespVO>> result = DataResult.success();
        result.setData(gridmanService.findPage(vo));
        return result;
    }

    @GetMapping("gridman/{id}")
    @ApiOperation(value = "网格员列表接口")
    @RequiresPermissions("app:gridman:list")
    @LogAnnotation(title = "网格员管理", action = "分页获取网格员列表")
    public DataResult<GridmanRespVO> detail(@PathVariable("id") Integer id) {
        DataResult<GridmanRespVO> result = DataResult.success();
        result.setData(gridmanService.detail(id));
        return result;
    }

    @GetMapping("gridman/export")
    @ApiOperation(value = "导出吸毒人员列表接口")
    @RequiresPermissions("app:druger:list")
    @LogAnnotation(title = "吸毒人员管理", action = "导出吸毒人员列表")
    public void export(GridmanPageVO vo, HttpServletResponse response) {
        List<GridmanRespVO> gridmens = gridmanService.getList(vo);
        ExcelUtils.exportExcel(gridmens,"网格人员列表","网格人员管理",DrugerPageRespVO.class,"网格人员.xls",response);
    }

}
