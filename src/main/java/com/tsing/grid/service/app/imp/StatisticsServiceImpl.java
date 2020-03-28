package com.tsing.grid.service.app.imp;

import com.tsing.grid.entity.app.Statistics;
import com.tsing.grid.mapper.StatisticsMapper;
import com.tsing.grid.service.app.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/9 10:38
 * @Version 1.0
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public void insert(Statistics statistics) {
        statistics.setCreate_time(new Date());
        statisticsMapper.insert(statistics);
    }

    @Override
    public boolean findByQuarterAndTypeAndCategory(String quarter, Integer type, Integer category) {
        Example example = new Example(Statistics.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("quarter",quarter);
        criteria.andEqualTo("type",type);
        criteria.andEqualTo("category",category);
        Statistics statistics = statisticsMapper.selectOneByExample(example);
        if(statistics==null){
            return true;
        }
        return false;
    }
}
