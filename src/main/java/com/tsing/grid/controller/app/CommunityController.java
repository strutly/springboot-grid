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
@Api(tags = "应用模块-社区管理")
public class CommunityController{
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
    private StreetService streetService;

	@GetMapping("communities")
    @ApiOperation(value = "分页获取社区列表接口")
    @RequiresPermissions("app:community:list")
    @LogAnnotation(title = "社区管理", action = "分页获取社区列表")
    public DataResult<PageRespVO<Community>> pageInfo(CommunityPageReqVO vo) {
        DataResult<PageRespVO<Community>> result = DataResult.success();
        result.setData(communityService.pageInfo(vo));
        return result;
    }

    @GetMapping("communities/all")
    @ApiOperation(value = "获取所有社区接口")
    @RequiresPermissions("app:community:all")
    @LogAnnotation(title = "社区管理", action = "获取所有社区")
    public DataResult<List<Community>> all() {
        DataResult<List<Community>> result = DataResult.success();
        result.setData(communityService.getAll());
        return result;
    }

    @GetMapping("community/{id}")
    @ApiOperation(value = "获取社区详细接口")
    @RequiresPermissions("app:community:detail")
    @LogAnnotation(title = "社区管理", action = "获取社区详细接口")
    public DataResult<CommunityRespVO> detail(@PathVariable("id") Integer id) {
        DataResult<CommunityRespVO> result = DataResult.success();
        result.setData(communityService.detail(id));
        return result;
    }

}
