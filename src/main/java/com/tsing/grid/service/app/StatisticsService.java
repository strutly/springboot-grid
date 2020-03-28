package com.tsing.grid.service.app;

import com.tsing.grid.entity.app.Statistics;

import java.util.List;

public interface StatisticsService {

    void insert(Statistics appStatistics);

    /**
     * 根据类型和季节查询是否存在
     * @param quarter 季节
     * @param type 类型
     * @param category
     * @return
     */
    boolean findByQuarterAndTypeAndCategory(String quarter,Integer type,Integer category);

}