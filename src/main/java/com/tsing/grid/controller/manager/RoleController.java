package com.tsing.grid.controller.manager;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.entity.sys.Role;
import com.tsing.grid.service.sys.RoleService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.RoleAddReqVO;
import com.tsing.grid.vo.req.RolePageReqVO;
import com.tsing.grid.vo.req.RoleUpdateReqVO;
import com.tsing.grid.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
@Api(tags = "组织模块-角色管理")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @LogAnnotation(title = "角色管理",action = "新增角色")
    public DataResult<Role> addRole(@RequestBody @Valid RoleAddReqVO vo){
        DataResult<Role> result= DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @LogAnnotation(title = "角色管理",action = "删除角色")
    public DataResult deleted(@PathVariable("id") Integer id){
        roleService.deletedRole(id);
        return DataResult.success();
    }

    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @LogAnnotation(title = "角色管理",action = "更新角色信息")
    public DataResult updateDept(@RequestBody @Valid RoleUpdateReqVO vo){
        roleService.updateRole(vo);
        return DataResult.success();
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口")
    @LogAnnotation(title = "角色管理",action = "查询角色详情")
    public DataResult<Role> detailInfo(@PathVariable("id") Integer id){
        DataResult<Role> result=DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }

    @GetMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    @LogAnnotation(title = "角色管理",action = "分页获取角色信息")
    public DataResult<PageRespVO<Role>> pageInfo(RolePageReqVO vo){
        DataResult<PageRespVO<Role>> result=DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }
}
