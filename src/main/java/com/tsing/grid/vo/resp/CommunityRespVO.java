package com.tsing.grid.vo.resp;

import com.tsing.grid.vo.resp.map.DrugerMapRespVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author lj
 * @Date 2020/3/9 17:41
 * @Version 1.0
 */
@Data
public class CommunityRespVO {


    private Integer id;

    private String name;// 社区名称

    private String boundary;//边界点

    private String center;//中心点

    private String memo;// 社区信息描述

    private List<GridRespVO> grids;

    private List<DrugerMapRespVo> drugers;

}
