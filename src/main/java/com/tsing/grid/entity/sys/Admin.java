package com.tsing.grid.entity.sys;

import com.tsing.grid.config.GenerateUUID;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 用户账号表
 * @author lj
 *
 */
@Data
@Entity
@Table(name = "sys_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    //0:禁止登录
    public static final Short UNVALID = -1;
    //1:有效
    public static final Short VALID = 0;


    /**名称*/
    private String name;

    /**登录帐号*/
    private String loginName;

    /**密码*/
    private String password;

    /**登录帐号*/
    private Integer unionId=0;

    /**盐*/
    private String salt;

    /**创建时间*/
    private Date createTime;

    /**最后登录时间*/
    private Date updateTime;

    /**0:有效，-1:禁止登录*/
    private Short status = VALID;
}
