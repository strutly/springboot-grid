package com.tsing.grid.controller.manager;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.sys.Permission;
import com.tsing.grid.service.sys.PermissionService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.util.HttpContextUtils;
import com.tsing.grid.vo.req.PermissionAddReqVO;
import com.tsing.grid.vo.req.PermissionUpdateReqVO;
import com.tsing.grid.vo.resp.PermissionRespNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/manager")
@RestController
@Api(tags = "组织模块-菜单权限管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取用户的权限菜单
     * @return
     */
    @GetMapping("/home/permissions")
    @ApiOperation(value ="获取首页数据接口")
    public DataResult<List<PermissionRespNode>> getHomeInfo(){
        System.out.println(12);
        Integer aid = HttpContextUtils.getAid();
        DataResult<List<PermissionRespNode>> result = DataResult.success();
        result.setData(permissionService.permissionTreeList(aid));
        return result;
    }

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "新增菜单权限")
    public DataResult<Permission> addPermission(@RequestBody @Valid PermissionAddReqVO vo){
        DataResult<Permission> result=DataResult.success();
        permissionService.insert(vo);
        return result;
    }

    @DeleteMapping("/permission/{id}")
    @ApiOperation(value = "删除菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "删除菜单权限")
    public DataResult deleted(@PathVariable("id") Integer id){
        DataResult result=DataResult.success();
        permissionService.deleteByPrimaryKey(id);
        return result;
    }

    @PutMapping("/permission")
    @ApiOperation(value = "更新菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "更新菜单权限")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo){
        DataResult result=DataResult.success();
        permissionService.update(vo);
        return result;
    }

    @GetMapping("/permission/{id}")
    @ApiOperation(value = "查询菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "查询菜单权限")
    public DataResult<Permission> detailInfo(@PathVariable("id") Integer id){
        DataResult<Permission> result = DataResult.success();
        result.setData(permissionService.selectByPrimaryKey(id));
        return result;
    }

    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有菜单权限接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有菜单权限")
    public DataResult<List<Permission>> getAllMenusPermission(){
        DataResult<List<Permission>> result = DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有目录菜单树")
    public DataResult<List<PermissionRespNode>> getAllMenusPermissionTree(@RequestParam(required = false) Integer permissionId){
        DataResult<List<PermissionRespNode>> result=DataResult.success();
        result.setData(permissionService.selectAllMenuByTree(permissionId));
        return result;
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "获取所有目录菜单树接口")
    @LogAnnotation(title = "菜单权限管理",action = "获取所有目录菜单树")
    public DataResult<List<PermissionRespNode>> getAllPermissionTree(){
        DataResult<List<PermissionRespNode>> result=DataResult.success();
        result.setData(permissionService.selectAllByTree());
        return result;
    }
}
