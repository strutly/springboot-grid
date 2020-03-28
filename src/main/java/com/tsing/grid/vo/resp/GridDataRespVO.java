package com.tsing.grid.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lj
 * @Date 2020/3/23 13:13
 * @Version 1.0
 */
@Data
public class GridDataRespVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("网格名称")
    private String name;//网格名称

    @ApiModelProperty("网格员姓名")
    private String mname;//网格员姓名

    @ApiModelProperty("网格员电话")
    private String mtel;//网格员电话

    @ApiModelProperty("社区名")
    private String cname;//社区名
}
