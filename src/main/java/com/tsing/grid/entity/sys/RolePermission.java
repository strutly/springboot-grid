package com.tsing.grid.entity.sys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色&&权限中间表
 * @author lj
 *
 */

@Data
@Entity
@Table(name = "sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

	/*{@link Role.id}*/
    private Integer rid;
    /*{@link Permission.id}*/
    private Integer pid;

    private Date createTime;
}