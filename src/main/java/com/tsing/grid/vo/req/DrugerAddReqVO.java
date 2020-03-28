package com.tsing.grid.vo.req;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/21 22:15
 * @Version 1.0
 */
@Data
public class DrugerAddReqVO {

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;//姓名

    @ApiModelProperty("性别")
    private Integer sex=1;//1男，2女

    @ApiModelProperty("生日")
    private Date birthday;//出生日期

    @ApiModelProperty("身份证号码")
    private String cardNo;//身份证号码

    @ApiModelProperty("联系方式")
    private String phone;//联系方式

    @ApiModelProperty("头像")
    private String pic;//头像

    @ApiModelProperty("婚姻")
    private Short marry;//1已婚，2未婚

    @ApiModelProperty("文化程度")
    private String education;//文化程度

    @ApiModelProperty("民族")
    private String nation;//民族

    @ApiModelProperty("所属社工")
    private String workerName;//所属社工

    @ApiModelProperty("社工电话")
    private String workerPhone;//社工电话

    @ApiModelProperty("居住情况")
    private Short liveStatus;//居住情况1独居 2与家人居住 3与朋友/同事合租

    @ApiModelProperty("就业情况")
    private String workStatus;//就业情况

    @ApiModelProperty("居住地址")
    private String address;//居住地址

    @ApiModelProperty("户籍")
    private String domicile;//户籍

    @ApiModelProperty("搬入时间")
    private Date intoDate;//搬入时间

    @ApiModelProperty("搬出时间")
    private Date outDate;//搬出时间

    @ApiModelProperty("初次发现时间")
    private Date firstTime;//初次发现时间

    @ApiModelProperty("毒品类型")
    private String type;//毒品类型

    @ApiModelProperty("当前管控地区")
    private String controlArea;//当前管控地区

    @ApiModelProperty("管控现状")
    private String controlStatus;//管控现状

    @ApiModelProperty("管控类型")
    private Short exhortStatus;//1责令社区戒毒  2责令社区康复   3均无

    @ApiModelProperty("风险等级")
    private Short grade;//1极端风险   2高风险   3中风险   4低风险

    @ApiModelProperty("备注")
    private String memo;//备注

    @ApiModelProperty("纬度")
    private BigDecimal latitude;//纬度

    @ApiModelProperty("经度")
    private BigDecimal longitude;//经度

    @ApiModelProperty("管辖状态")
    private Short status;//在管辖范围内，-1已经搬走
}
