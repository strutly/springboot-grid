package com.tsing.grid.controller.app;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.app.Grid;
import com.tsing.grid.service.app.GridService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.GridPageReqVO;
import com.tsing.grid.vo.resp.GridDataRespVO;
import com.tsing.grid.vo.resp.GridPageRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @author lijing
 *
 */
@RestController
@RequestMapping(value = "/manager")
@Api(tags = "应用模块-网格管理")
public class GridController{
	
	@Autowired
	private GridService gridService;


	@GetMapping("grids")
    @ApiOperation(value = "分页获取网格列表接口")
    @RequiresPermissions("app:grid:list")
    @LogAnnotation(title = "网格管理", action = "分页获取网格列表")
    public DataResult<PageRespVO<GridPageRespVO>> pageInfo(GridPageReqVO vo) {
        DataResult<PageRespVO<GridPageRespVO>> result = DataResult.success();
        result.setData(gridService.findPage(vo));
        return result;
    }

    @GetMapping("grids/{name}")
    @ApiOperation(value = "根据名称获取网格接口")
    @RequiresPermissions("app:grid:list")
    @LogAnnotation(title = "网格管理", action = "根据名称获取网格")
    public DataResult<List<GridDataRespVO>> findByName(@PathVariable("name") String name) {
        DataResult<List<GridDataRespVO>> result = DataResult.success();
        result.setData(gridService.findByName(name));
        return result;
    }
}
