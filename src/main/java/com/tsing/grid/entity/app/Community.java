package com.tsing.grid.entity.app;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 社区
 * @author lijing
 *
 */
@Data
@Entity
@Table(name = "app_community")
public class Community implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;

	private String name;// 社区名称

	private String boundary;//边界点

	private String center;//中心点

	private String memo;// 社区信息描述

	private Date createTime;//新增时间

	private Date updateTime;//更新时间

}
