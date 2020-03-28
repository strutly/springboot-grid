package com.tsing.grid.aop.timer;

import com.alibaba.fastjson.JSON;
import com.tsing.grid.entity.app.Statistics;
import com.tsing.grid.service.app.DrugerService;
import com.tsing.grid.service.app.StatisticsService;
import com.tsing.grid.vo.resp.ChartDataRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * @Author lj
 * @Date 2020/3/21 14:23
 * @Version 1.0
 * 定时任务
 */
@Component
@EnableScheduling
public class Tasks {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private DrugerService drugerService;

    /**
     * 定时统计
     * 3,6,9,12月的1,2,3日1点1分1秒进行一次统计(一个月三次只是为了防止未进行统计)
     * https://qqe2.com/cron 在线生成cron
     */
    @Scheduled(cron = "1 1 1 1,2,3 3,6,9,12 ? ")
    public void statistics(){
        //更新定时信息
        String quarter = "";

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;

        if(month>=3 && month<=5) {
            quarter = (year-1) +"冬季";
        }else if(month>=6 && month<=8) {
            quarter = year +"春季";
        }else if(month>=9&&month<=11) {
            quarter = year +"夏季";
        }else {
            quarter = year +"秋季";
        }
        //吸毒人员社区统计
        if(statisticsService.findByQuarterAndTypeAndCategory(quarter,1,1)) {
            List<ChartDataRespVO> chartDataDTOs = drugerService.chartByCid();
            Statistics dStatistics = new Statistics();
            dStatistics.setType(1);
            dStatistics.setCategory(1);
            dStatistics.setQuarter(quarter);
            dStatistics.setDate(JSON.toJSONString(chartDataDTOs));
            statisticsService.insert(dStatistics);
        };
        //吸毒人员按等级统计
        if(statisticsService.findByQuarterAndTypeAndCategory(quarter,2,1)) {
            List<ChartDataRespVO> chartDataDTOs2 = drugerService.chartByGrade();
            Statistics dStatistics2 = new Statistics();
            dStatistics2.setType(2);
            dStatistics2.setCategory(1);
            dStatistics2.setQuarter(quarter);
            dStatistics2.setDate(JSON.toJSONString(chartDataDTOs2));
            statisticsService.insert(dStatistics2);
        }
        //吸毒人员按年限统计
        if(statisticsService.findByQuarterAndTypeAndCategory(quarter,3,1)) {
            List<ChartDataRespVO> chartDataDTOs3 = drugerService.chartByTime();
            Statistics dStatistics3 = new Statistics();
            dStatistics3.setType(3);
            dStatistics3.setCategory(1);
            dStatistics3.setQuarter(quarter);
            dStatistics3.setDate(JSON.toJSONString(chartDataDTOs3));
            statisticsService.insert(dStatistics3);
        }


    }


}
