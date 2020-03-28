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
@Table(name = "app_grid_man")
public class Gridman implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
	private Integer id;

	private String name;//姓名

	private String pic;//头像

	private Integer sex=1;//姓别1男2女

	private String phone;//管控通号码

	private Date birthday;//出生日期

	private String palce;//籍贯

	private String nation;//民族

	private String education;//学历

	private String blood;//血型

	private String jobStatus;//在职状态

	private String political;//政治面貌

	private Integer bsize=0;//管理楼栋数

	private Integer gsize=0;//楼栋长数量

	private Integer tsize=0;//三小长所数

	private Integer rsize=0;//房屋间套数

	private String post;//职务

	private String belongTeam;//所属大队

	private String type;//人员类别

	private String unitName;//单位名称

	private String tel;//联系电话

	private String contactMan;//紧急联系人

	private String contactTel;//紧急联系人电话

	private String address;//地址

	private Integer  state=0;//-1为删除

	private Date createTime;//新增时间

	private Date updateTime;//更新时间
}
