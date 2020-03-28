package com.tsing.grid.controller.app;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.app.Community;
import com.tsing.grid.service.app.CommunityService;
import com.tsing.grid.service.app.StreetService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.CommunityPageReqVO;
import com.tsing.grid.vo.resp.CommunityRespVO;
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
@Api(tags = "应用模块-统计管理")
public class StatisticsController {
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
    private StreetService streetService;

	@GetMapping("statistics")
    @ApiOperation(value = "统计列表")
    @RequiresPermissions("app:statistics:index")
    @LogAnnotation(title = "统计管理", action = "统计列表")
    public DataResult<List<CommunityRespVO>> statistics() {
        DataResult<List<CommunityRespVO>> result = DataResult.success();
        result.setData(communityService.statistics());
        return result;
    }

    @GetMapping("statistics/{type}")
    @ApiOperation(value = "统计列表")
    @RequiresPermissions("app:community:list")
    @LogAnnotation(title = "统计管理", action = "统计列表")
    public DataResult<List<CommunityRespVO>> statisticsByType(@PathVariable("type") Integer type) {
        DataResult<List<CommunityRespVO>> result = DataResult.success();
        result.setData(communityService.statistics());
        return result;
    }



}
