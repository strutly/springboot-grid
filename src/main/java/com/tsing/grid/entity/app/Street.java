package com.tsing.grid.entity.app;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/8 11:53
 * @Version 1.0
 */
@Data
@Table(name = "app_street")
public class Street implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;

    private String name;//名称

    private String boundary;//边界点

    private String center;//中心点

    private String memo;// 街道信息描述

    private Date createTime;//新增时间

    private Date updateTime;//更新时间
}
