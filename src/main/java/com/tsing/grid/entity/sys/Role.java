package com.tsing.grid.entity.sys;

import com.tsing.grid.config.GenerateUUID;
import com.tsing.grid.vo.resp.PermissionRespNode;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 * @author lj
 *
 */
@Data
@Entity
@Table(name = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

	private String name;

    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    @Transient
    private List<PermissionRespNode> permissionRespNodes;

}