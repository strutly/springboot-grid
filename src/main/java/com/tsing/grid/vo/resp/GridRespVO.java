package com.tsing.grid.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/9 17:43
 * @Version 1.0
 */
@Data
public class GridRespVO {

    @ApiModelProperty(value = "网格名称")
    private String name;// 网格名称

    @ApiModelProperty(value = "边界点")
    private String boundary;//边界点

    @ApiModelProperty(value = "中心点")
    private String center;//中心点
}
