package com.tsing.grid.service.app.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.app.Gridman;
import com.tsing.grid.exception.BusinessException;
import com.tsing.grid.exception.code.BaseExceptionType;
import com.tsing.grid.mapper.DrugerMapper;
import com.tsing.grid.mapper.GridmanMapper;
import com.tsing.grid.service.app.DrugerService;
import com.tsing.grid.service.app.GridmanService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.DrugerPageVO;
import com.tsing.grid.vo.req.GridmanPageVO;
import com.tsing.grid.vo.resp.DrugerDetailResp;
import com.tsing.grid.vo.resp.DrugerPageRespVO;
import com.tsing.grid.vo.resp.GridmanRespVO;
import com.tsing.grid.vo.resp.PageRespVO;
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
public class GridmanServiceImpl implements GridmanService {

    @Autowired
    private GridmanMapper gridmanMapper;

    @Override
    public PageRespVO<GridmanRespVO> findPage(GridmanPageVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<GridmanRespVO> list = getList(vo);
        return PageUtils.getPageVO(list);
    }

    @Override
    public List<GridmanRespVO> getList(GridmanPageVO vo) {
        return gridmanMapper.getList(vo);
    }

    @Override
    public GridmanRespVO detail(Integer id) {
        return gridmanMapper.detail(id);
    }
}
