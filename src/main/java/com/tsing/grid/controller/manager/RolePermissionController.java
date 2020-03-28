package com.tsing.grid.controller.manager;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.service.sys.RolePermissionService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.RolePermissionOperationReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
@Api(tags = "组织管理-角色和菜单关联接口")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/role/permission")
    @ApiOperation(value = "修改或者新增角色菜单权限接口")
    @LogAnnotation(title = "角色和菜单关联接口",action = "修改或者新增角色菜单权限")
    public DataResult operationRolePermission(@RequestBody @Valid RolePermissionOperationReqVO vo){
        rolePermissionService.setRolePermission(vo);
        return DataResult.success();
    }
}
