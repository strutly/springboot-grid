package com.tsing.grid.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author lj
 * @Date 2020/3/9 11:18
 * @Version 1.0
 */
@Data
public class PolygonVO {

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;//名称

    @ApiModelProperty(value = "边界点")
    @NotBlank(message = "边界点不能为空")
    private String boundary;//边界点

    @ApiModelProperty(value = "中心点")
    @NotBlank(message = "中心点不能为空")
    private String center;//中心点

    @ApiModelProperty(value = "信息描述")
    private String memo;// 街道信息描述
}
