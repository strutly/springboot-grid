package com.tsing.grid.vo.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lj
 * @Date 2020/3/22 14:12
 * @Version 1.0
 */
@Data
public class GridmanDataRespVO {

    @ApiModelProperty("姓名")
    @Excel(name = "网格员", orderNum = "27")
    private String name;

    @ApiModelProperty("管控通号码")
    @Excel(name = "网格员管控通号码", orderNum = "28")
    private String phone;

    @ApiModelProperty("手机号码")
    @Excel(name = "网格员手机号码", orderNum = "29")
    private String tel;
}
