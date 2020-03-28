package com.tsing.grid.entity.app;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * 人员统计备份(AppStatistics)实体类
 *
 * @author makejava
 * @since 2020-03-21 14:51:26
 */
@Data
@Entity
@Table(name = "app_statistics")
public class Statistics implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
    * id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer id;
    /**
    * 统计数据
    */
    private String date;
    /**
    * 2018春季 2018夏季
    */
    private String quarter;
    /**
    * 1为按社区统计，2为按等级统计，3为按年限统计
    */
    private Integer type;
    /**
    * 1为吸毒人员2物流企业3精神障碍人员4616人员(涉疆涉维)5前科人员
    */
    private Integer category;
    /**
    * 统计时间
    */
    private Date create_time;

}