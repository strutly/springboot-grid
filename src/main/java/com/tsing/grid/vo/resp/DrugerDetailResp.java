package com.tsing.grid.vo.resp;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.tsing.grid.entity.app.Gridman;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/10 23:27
 * @Version 1.0
 */
@Data
public class DrugerDetailResp {

    private Integer ggid;//

    private String cname;//社区名称

    private String gname;//网格名称

    private String name;//姓名

    private Short sex=1;//1男，2女

    private Date birthday;//出生日期

    private String cardNo;//身份证号码

    private String phone;//联系方式

    private String pic;//头像

    private Short marry=1;//1已婚，2未婚

    private String education;//文化程度

    private String nation;//民族

    private String workerName;//所属社工

    private String workerPhone;//社工电话

    private Short liveStatus=1;//居住情况1独居 2与家人居住 3与朋友/同事合租

    private String workStatus;//就业情况

    private String address;//居住地址

    private String domicile;//户籍

    private Date intoDate;//搬入时间

    private Date outDate;//搬出时间

    private Date firstTime;//初次发现时间

    private String type;//毒品类型

    private String controlArea;//当前管控地区

    private String controlStatus;//管控现状

    private Short exhortStatus=1;//1责令社区戒毒  2责令社区康复   3均无

    private Short grade=1;//1极端风险   2高风险   3中风险   4低风险

    private String memo;//备注

    private BigDecimal latitude;//纬度

    private BigDecimal longitude;//经度

    private Short status=0;//在管辖范围内，-1已经搬走

    private Short state=0;//未删除 -1 删除

    private Date createTime;//新增时间

    private Date updateTime;//更新时间

    private GridmanRespVO vo;
}
