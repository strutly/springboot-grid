package com.tsing.grid.vo.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lj
 * @Date 2020/3/24 12:10
 * @Version 1.0
 */
@Data
public class BelongRespVO {

    @ApiModelProperty("网格id")
    private Integer gid;

    @Excel(name = "所属网格")
    @ApiModelProperty("所属网格")
    private String gname;//所属网格

    @ApiModelProperty("社区id")
    private Integer cid;

    @Excel(name = "所属社区")
    @ApiModelProperty("所属社区")
    private String cname;//所属社区
}
