package com.tsing.grid.vo.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
@Data
public class DrugerPageRespVO {

    @ApiModelProperty("id")
    private Integer id;

    @Excel(name = "网格名称", orderNum = "0")
    @ApiModelProperty("网格名称")
    private String  gname;//网格名称

    @Excel(name = "社区名称", orderNum = "1")
    @ApiModelProperty("社区名称")
    private String  cname;//社区名称

    @Excel(name = "姓名", orderNum = "2")
    @ApiModelProperty("姓名")
    private String name;//姓名

    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "3")
    @ApiModelProperty("性别")
    private Short sex;//1男，2女

    @Excel(name = "生日", exportFormat = "yyyy-MM-dd", orderNum = "4")
    @ApiModelProperty("生日")
    private Date birthday;//出生日期

    @Excel(name = "身份证号码", orderNum = "5")
    @ApiModelProperty("身份证号码")
    private String cardNo;//身份证号码

    @Excel(name = "联系方式", orderNum = "6")
    @ApiModelProperty("联系方式")
    private String phone;//联系方式

    @Excel(name = "头像", orderNum = "7")
    @ApiModelProperty("头像")
    private String pic;//头像

    @Excel(name = "婚姻", replace = {"已婚_1", "未婚_2"}, orderNum = "8")
    @ApiModelProperty("婚姻")
    private Short marry;//1已婚，2未婚

    @Excel(name = "文化程度", orderNum = "9")
    @ApiModelProperty("文化程度")
    private String education;//文化程度

    @Excel(name = "民族", orderNum = "10")
    @ApiModelProperty("民族")
    private String nation;//民族

    @Excel(name = "所属社工", orderNum = "11")
    @ApiModelProperty("所属社工")
    private String workerName;//所属社工

    @Excel(name = "社工电话", orderNum = "12")
    @ApiModelProperty("社工电话")
    private String workerPhone;//社工电话

    @Excel(name = "居住情况", replace = {"独居_1", "与家人居住_2","与朋友/同事合租_3"}, orderNum = "13")
    @ApiModelProperty("居住情况")
    private Short liveStatus;//居住情况1独居 2与家人居住 3与朋友/同事合租

    @Excel(name = "就业情况", orderNum = "14")
    @ApiModelProperty("就业情况")
    private String workStatus;//就业情况

    @Excel(name = "居住地址", orderNum = "15")
    @ApiModelProperty("居住地址")
    private String address;//居住地址

    @Excel(name = "户籍", orderNum = "16")
    @ApiModelProperty("户籍")
    private String domicile;//户籍

    @Excel(name = "搬入时间", exportFormat = "yyyy-MM-dd", orderNum = "17")
    @ApiModelProperty("搬入时间")
    private Date intoDate;//搬入时间

    @Excel(name = "搬出时间", exportFormat = "yyyy-MM-dd", orderNum = "18")
    @ApiModelProperty("搬出时间")
    private Date outDate;//搬出时间

    @Excel(name = "初次发现时间", exportFormat = "yyyy-MM-dd", orderNum = "19")
    @ApiModelProperty("初次发现时间")
    private Date firstTime;//初次发现时间

    @Excel(name = "毒品类型", orderNum = "20")
    @ApiModelProperty("毒品类型")
    private String type;//毒品类型

    @Excel(name = "当前管控地区", orderNum = "21")
    @ApiModelProperty("当前管控地区")
    private String controlArea;//当前管控地区

    @Excel(name = "管控现状", orderNum = "22")
    @ApiModelProperty("管控现状")
    private String controlStatus;//管控现状

    @Excel(name = "管控类型", replace = {"责令社区戒毒_1", "责令社区康复_2","均无_3"}, orderNum = "23")
    @ApiModelProperty("管控类型")
    private Short exhortStatus;//1责令社区戒毒  2责令社区康复   3均无

    @Excel(name = "风险等级", replace = {"极端风险_1", "高风险_2","中风险_3","低风险_4"}, orderNum = "24")
    @ApiModelProperty("风险等级")
    private Short grade;//1极端风险   2高风险   3中风险   4低风险

    @Excel(name = "备注", orderNum = "25")
    @ApiModelProperty("备注")
    private String memo;//备注

    @ApiModelProperty("纬度")
    private BigDecimal latitude;//纬度

    @ApiModelProperty("经度")
    private BigDecimal longitude;//经度

    @ApiModelProperty("管辖状态")
    @Excel(name = "管辖状态",replace = {"管辖中_0", "搬走_-1"}, orderNum = "26")
    private Short status;//在管辖范围内，-1已经搬走

    @ApiModelProperty("新增时间")
    private Date createTime;//新增时间

    @ApiModelProperty("更新时间")
    private Date updateTime;//更新时间

    @ApiModelProperty("网格员信息")
    @ExcelEntity
    private GridmanDataRespVO vo;


}
