package com.tsing.grid.entity.app;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 吸毒人员回访记录
 * @author lijing
 *
 */
@Data
@Entity
@Table(name = "app_d_visit")
public class DVisit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;

	private Integer did;//吸毒人员id

	private Date vTime;//回访时间

	private String vName;//回访人

	private String pic;//回访照片

	private String memo;//回访备注

	private Integer state=0;//-1为删除

	private Date createTime;//新增时间

	private Date updateTime;//更新时间
	

}
