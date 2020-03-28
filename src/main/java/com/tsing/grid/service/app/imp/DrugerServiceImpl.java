package com.tsing.grid.service.app.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.app.Druger;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.DrugerMapper;
import com.tsing.grid.service.app.DrugerService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.DrugerAddReqVO;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.resp.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author lj
 * @Date 2020/3/9 10:38
 * @Version 1.0
 */
@Service
public class DrugerServiceImpl implements DrugerService {

    @Autowired
    private DrugerMapper drugerMapper;

    @Override
    public PageRespVO<DrugerPageRespVO> findPage(DrugerPageVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<DrugerPageRespVO> list = drugerMapper.findPage(vo);
        return PageUtils.getPageVO(list);
    }

    @Override
    public List<DrugerPageRespVO> getList(DrugerPageVO vo) {
        return drugerMapper.findPage(vo);
    }

    @Override
    @Cacheable(cacheNames ="drugerDetail",key = "'id'")
    public DrugerDetailResp detail(Integer id) {
        return drugerMapper.detail(id);
    }

    @Override
    public void delete(List<Integer> ids) {
        Example example = new Example(Druger.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        drugerMapper.deleteByExample(example);
    }

    @Override
    public List<ChartDataRespVO> chartByCid() {
        return drugerMapper.chartByCid();
    }

    @Override
    public List<ChartDataRespVO> chartByGrade() {
        return drugerMapper.chartByGrade();
    }

    @Override
    public List<ChartDataRespVO> chartByTime() {
        return drugerMapper.chartByTime();
    }

    private Druger findByIdCard(String cardNo){
        Example example = new Example(Druger.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cardNo",cardNo);
        criteria.andEqualTo("state",0);
        return drugerMapper.selectOneByExample(example);
    }

    @Override
    public void insert(DrugerAddReqVO vo) {
        Druger druger = findByIdCard(vo.getCardNo());
        if(druger!=null){
            throw new BusinessException(BaseExceptionType.SYSTEM_ERROR,"该身份证的吸毒人员已存在!");
        }
        druger = new Druger();
        BeanUtils.copyProperties(vo,druger);
        druger.setCreateTime(new Date());
        druger.setUpdateTime(new Date());
        drugerMapper.insert(druger);
    }
}
