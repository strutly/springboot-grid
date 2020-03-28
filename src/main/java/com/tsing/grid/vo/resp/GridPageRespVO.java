package com.tsing.grid.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lj
 * @Date 2020/3/24 11:29
 * @Version 1.0
 */
@Data
public class GridPageRespVO {

    @ApiModelProperty("社区id")
    private Integer id;

    @ApiModelProperty("社区id")
    private String cid;//社区id

    @ApiModelProperty("社区名称")
    private String cname;//社区名称

    @ApiModelProperty("单位名称")
    private String name;// 网格名称名称

    @ApiModelProperty("边界点")
    private String boundary;//边界点

    @ApiModelProperty("中心点")
    private String center;//中心点

    @ApiModelProperty("网格信息描述")
    private String memo;// 网格信息描述

    @ApiModelProperty("网格员信息")
    private GridmanDataRespVO vo;//网格员id
}
