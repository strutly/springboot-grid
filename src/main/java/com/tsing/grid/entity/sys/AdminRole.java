package com.tsing.grid.entity.sys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 * @author lj
 *
 */
@Data
@Entity
@Table(name="sys_admin_role")
public class AdminRole  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer aid;//账号id
    private Integer rid;//权限id
    private Date createTime;//新增时间
}