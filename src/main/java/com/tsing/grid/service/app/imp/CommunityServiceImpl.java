package com.tsing.grid.service.app.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.app.Community;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.CommunityMapper;
import com.tsing.grid.service.app.CommunityService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.PolygonVO;
import com.tsing.grid.vo.req.CommunityPageReqVO;
import com.tsing.grid.vo.resp.CommunityRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author lj
 * @Date 2020/3/9 10:38
 * @Version 1.0
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public void insert(PolygonVO vo) {
        Community community = communityMapper.findByName(vo.getName());
        if(null!=community){
            throw new BusinessException(BaseExceptionType.SYSTEM_ERROR,"该社区已存在,请修改后再保存");
        }
        community = new Community();
        BeanUtils.copyProperties(vo,community);
        communityMapper.insert(community);
    }

    @Override
    public CommunityRespVO detail(Integer id) {
        CommunityRespVO community = communityMapper.findAndGrids(id);
        return community;
    }

    @Override
    public List<Community> getAll() {
        return communityMapper.selectAll();
    }

    @Override
    public PageRespVO<Community> pageInfo(CommunityPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());

        Example example = new Example(Community.class);
        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(vo.getName())){
            criteria.andLike("name", "%"+vo.getName()+"%");
        }

        if(!StringUtils.isEmpty(vo.getStartTime())){
            criteria.andGreaterThan("createTime",vo.getStartTime());
        }

        if(!StringUtils.isEmpty(vo.getEndTime())){
            criteria.andLessThan("createTime",vo.getEndTime());
        }

        List<Community> list = communityMapper.selectByExample(example);

        return PageUtils.getPageVO(list);
    }

    @Override
    public List<CommunityRespVO> statistics() {
        return communityMapper.statistics();
    }


}
