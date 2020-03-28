package com.tsing.grid.vo.resp.map;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author lj
 * @Date 2020/3/19 16:00
 * @Version 1.0
 */
@Data
public class DrugerMapRespVo {

    private Integer id;//id

    private String name;//姓名

    private BigDecimal latitude;//纬度

    private BigDecimal longitude;//经度

    private Short status;//状态
}
