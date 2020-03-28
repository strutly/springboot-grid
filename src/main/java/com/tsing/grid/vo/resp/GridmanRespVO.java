package com.tsing.grid.vo.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author lj
 * @Date 2020/3/10 22:50
 * @Version 1.0
 */
@Data
public class GridmanRespVO {

    @ApiModelProperty("id主键")
    private Integer id;

    @ApiModelProperty("网格id")
    private Integer gid;

    @Excel(name = "姓名", orderNum = "0")
    @ApiModelProperty("姓名")
    private String name;//姓名

    @ApiModelProperty("头像")
    private String pic;//头像

    @Excel(name = "性别", replace = {"男_1", "女_2"}, orderNum = "1")
    @ApiModelProperty("姓别1男2女")
    private Short sex=1;//姓别1男2女

    @Excel(name = "管控通号码",orderNum = "2")
    @ApiModelProperty("管控通号码")
    private String phone;//管控通号码

    @Excel(name = "出生日期", exportFormat = "yyyy-MM-dd", orderNum = "3")
    @ApiModelProperty("出生日期")
    private Date birthday;//出生日期

    @Excel(name = "籍贯", orderNum = "4")
    @ApiModelProperty("籍贯")
    private String palce;//籍贯

    @Excel(name = "民族", orderNum = "5")
    @ApiModelProperty("民族")
    private String nation;//民族

    @Excel(name = "学历", orderNum = "6")
    @ApiModelProperty("学历")
    private String education;//学历

    @Excel(name = "血型", orderNum = "7")
    @ApiModelProperty("血型")
    private String blood;//血型

    @Excel(name = "在职状态", orderNum = "8")
    @ApiModelProperty("在职状态")
    private String jobStatus;//在职状态

    @Excel(name = "政治面貌", orderNum = "9")
    @ApiModelProperty("政治面貌")
    private String political;//政治面貌

    @Excel(name = "管理楼栋数", orderNum = "10")
    @ApiModelProperty("管理楼栋数")
    private Integer bsize=0;//管理楼栋数

    @Excel(name = "楼栋长数量", orderNum = "11")
    @ApiModelProperty("楼栋长数量")
    private Integer gsize=0;//楼栋长数量

    @Excel(name = "三小长所数", orderNum = "12")
    @ApiModelProperty("三小长所数")
    private Integer tsize=0;//三小长所数

    @Excel(name = "房屋间套数", orderNum = "13")
    @ApiModelProperty("房屋间套数")
    private Integer rsize=0;//房屋间套数

    @Excel(name = "职务", orderNum = "14")
    @ApiModelProperty("职务")
    private String post;//职务

    @Excel(name = "所属大队", orderNum = "15")
    @ApiModelProperty("所属大队")
    private String belongTeam;//所属大队

    @Excel(name = "人员类别", orderNum = "16")
    @ApiModelProperty("人员类别")
    private String type;//人员类别

    @Excel(name = "单位名称", orderNum = "17")
    @ApiModelProperty("单位名称")
    private String unitName;//单位名称

    @Excel(name = "联系电话", orderNum = "18")
    @ApiModelProperty("联系电话")
    private String tel;//联系电话

    @Excel(name = "紧急联系人", orderNum = "19")
    @ApiModelProperty("紧急联系人")
    private String contactMan;//紧急联系人

    @Excel(name = "紧急联系人电话", orderNum = "20")
    @ApiModelProperty("紧急联系人电话")
    private String contactTel;//紧急联系人电话

    @Excel(name = "地址", orderNum = "21")
    @ApiModelProperty("地址")
    private String address;//地址

    @ApiModelProperty("所属信息")
    @ExcelCollection(name = "所属信息", orderNum = "22")
    private List<BelongRespVO> vo;

}
