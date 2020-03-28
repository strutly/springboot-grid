package com.tsing.grid.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lj
 * @Date 2020/3/10 16:02
 * @Version 1.0
 */
@Data
public class DrugerPageVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号码")
    private String cardNo;

    @ApiModelProperty(value = "状态")
    private Short status;

    @ApiModelProperty(value = "社区id")
    private Integer cid;

    @ApiModelProperty(value = "网格名称")
    private String gridName;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "网格id")
    private Integer gid;

}
