package com.tsing.grid.controller.app;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.service.app.StreetService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.PolygonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 
 * @author lijing
 *
 */
@RestController
@RequestMapping(value = "/manager")
@Api(tags = "应用模块-街道管理")
public class StreetController{
	
	@Autowired
	private StreetService streetService;
	
	@GetMapping(value = "/street")
	@ApiOperation(value = "获取街道信息")
	@RequiresPermissions("app:street:index")
	@LogAnnotation(title = "街道管理", action = "获取街道信息")
	public DataResult<PolygonVO> index() {
		DataResult<PolygonVO> result = DataResult.success();
		result.setData(streetService.getLast());
		return result;
	}

	@PostMapping(value = "/street")
	@ApiOperation(value = "编辑街道信息")
	@RequiresPermissions("app:street:edit")
	@LogAnnotation(title = "街道管理", action = "编辑街道信息")
	public DataResult<PolygonVO> index(@RequestBody @Valid PolygonVO vo) {
		System.out.println(vo.getMemo());
		System.out.println(vo.getName());
		System.out.println("vo.getName()");
		System.out.println(vo);
		DataResult<PolygonVO> result = DataResult.success();
		result.setData(streetService.edit(vo));
		return result;
	}
}
