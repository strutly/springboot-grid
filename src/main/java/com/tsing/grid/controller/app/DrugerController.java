package com.tsing.grid.controller.app;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.service.app.DrugerService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.util.ExcelUtils;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.req.DrugerAddReqVO;
import com.tsing.grid.vo.resp.DrugerDetailResp;
import com.tsing.grid.vo.resp.DrugerPageRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author lijing
 *
 */
@RestController
@RequestMapping(value = "/manager")
@Api(tags = "应用模块-吸毒人员管理")
public class DrugerController{
	
	@Autowired
	private DrugerService drugerService;

	@GetMapping("drugers")
	@ApiOperation(value = "分页获取吸毒人员列表接口")
	@RequiresPermissions("app:druger:list")
	@LogAnnotation(title = "吸毒人员管理", action = "分页获取吸毒人员列表")
	public DataResult<PageRespVO<DrugerPageRespVO>> pageInfo(DrugerPageVO vo) {
		System.out.println(vo.toString());
		DataResult<PageRespVO<DrugerPageRespVO>> result = DataResult.success();
		result.setData(drugerService.findPage(vo));
		return result;
	}

	@PostMapping("/druger")
	@ApiOperation(value = "新增吸毒人员接口")
	@RequiresPermissions("sys:admin:add")
	@LogAnnotation(title = "吸毒人员管理",action = "新增吸毒人员")
	public DataResult addUser(@RequestBody @Valid DrugerAddReqVO vo){
		drugerService.insert(vo);
		return DataResult.success();
	}



	@GetMapping("druger/export")
	@ApiOperation(value = "导出吸毒人员列表接口")
	@RequiresPermissions("app:druger:list")
	@LogAnnotation(title = "吸毒人员管理", action = "导出吸毒人员列表")
	public void export(DrugerPageVO vo,HttpServletResponse response) {
		List<DrugerPageRespVO> drugerPageRespVOS = drugerService.getList(vo);
		ExcelUtils.exportExcel(drugerPageRespVOS,"吸毒人员列表","吸毒人员管理",DrugerPageRespVO.class,"吸毒人员.xls",response);
	}


	@GetMapping("druger/{id}")
	@ApiOperation(value = "吸毒人员详细接口")
	@RequiresPermissions("app:druger:detail")
	@LogAnnotation(title = "吸毒人员管理", action = "获取吸毒人员详细")
	public DataResult<DrugerDetailResp> detail(@PathVariable("id") Integer id) {
		DataResult<DrugerDetailResp> result = DataResult.success();
		result.setData(drugerService.detail(id));
		return result;
	}

	@DeleteMapping("druger")
	@ApiOperation(value = "吸毒人员删除接口")
	@RequiresPermissions("app:druger:delete")
	@LogAnnotation(title = "吸毒人员管理", action = "获取吸毒人员删除")
	public DataResult<DrugerDetailResp> delete(@RequestBody @ApiParam(value = "用户id集合") List<Integer> ids) {
		DataResult<DrugerDetailResp> result = DataResult.success();
		drugerService.delete(ids);
		return result;
	}
}
