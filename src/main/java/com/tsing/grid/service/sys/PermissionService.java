package com.tsing.grid.service.sys;

import com.tsing.grid.entity.sys.Permission;
import com.tsing.grid.vo.req.PermissionAddReqVO;
import com.tsing.grid.vo.req.PermissionUpdateReqVO;
import com.tsing.grid.vo.resp.PermissionRespNode;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    Permission selectByPrimaryKey(Integer id);

    void insert(PermissionAddReqVO vo);

    void deleteByPrimaryKey(Integer id);

    void update(PermissionUpdateReqVO permission);

    List<Permission> selectAll();

    List<Permission> getPermission(Integer aid);

    List<String> getPNames(Integer aid);

    List<PermissionRespNode> permissionTreeList(Integer userId);

    List<PermissionRespNode> selectAllMenuByTree(Integer permissionId);

    List<PermissionRespNode> selectAllByTree();

    Set<String> getPermsByAid(Integer aid);
}
