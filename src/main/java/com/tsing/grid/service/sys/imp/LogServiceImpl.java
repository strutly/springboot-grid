package com.tsing.grid.service.sys.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.sys.SysLog;
import com.tsing.grid.mapper.SysLogMapper;
import com.tsing.grid.service.sys.LogService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.SysLogPageReqVO;
import com.tsing.grid.vo.resp.PageRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageRespVO<SysLog> pageInfo(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        Example example = new Example(SysLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(vo.getAid())){
            criteria.andEqualTo("userId", vo.getAid());
        }
        if(!StringUtils.isEmpty(vo.getStartTime())){
            criteria.andGreaterThan("createTime",vo.getStartTime());
        }
        if(!StringUtils.isEmpty(vo.getEndTime())){
            criteria.andLessThan("createTime",vo.getEndTime());
        }
        if(!StringUtils.isEmpty(vo.getOperation())) {
            criteria.andLike("operation", "%"+vo.getOperation()+"%");
        }
        if(!StringUtils.isEmpty(vo.getUsername())){
            criteria.andLike("username",vo.getUsername());
        }
        example.setOrderByClause("create_time desc");
        List<SysLog> sysLogs = sysLogMapper.selectByExample(example);
        return PageUtils.getPageVO(sysLogs);
    }


    @Override
    public void insert(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

    @Override
    public void delete(List<Integer> logIds) {
        Example example = new Example(SysLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", logIds);
        sysLogMapper.deleteByExample(example);
    }
}
