package com.tsing.grid.entity.sys;

import com.tsing.grid.config.GenerateUUID;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    private String code;

    private String name;

    private String icon="layui-icon-home";

    private String perms;

    private String url;

    private String method;

    private Integer pid;

    @Transient
    private String pidName;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}