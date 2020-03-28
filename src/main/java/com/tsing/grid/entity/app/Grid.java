package com.tsing.grid.entity.app;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lijing
 *
 */
@Data
@Entity
@Table(name = "app_grid")
public class Grid implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;

	private Integer cid;//社区id

	private String name;// 网格名称名称

	private String boundary;//边界点

	private String center;//中心点

	private String memo;// 网格信息描述

	private Integer gid;//网格员id

	private Date createTime;//新增时间

	private Date updateTime;//更新时间


}
