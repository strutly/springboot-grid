package com.tsing.grid.controller.manager;

import com.tsing.grid.aop.annotation.LogAnnotation;
import com.tsing.grid.service.sys.AdminRoleService;
import com.tsing.grid.util.DataResult;
import com.tsing.grid.vo.req.AdminRoleOperationReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/manager")
@RestController
@Api(tags = "组织管理-用户和角色关联接口")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;
    
    @PostMapping("/admin/role")
    @ApiOperation(value = "修改或者新增用户角色接口")
    @LogAnnotation(title = "用户和角色关联接口",action = "修改或者新增用户角色")
    public DataResult operationUserRole(@RequestBody @Valid AdminRoleOperationReqVO vo){
        adminRoleService.setAdminRole(vo);
        return DataResult.success();
    }
}
