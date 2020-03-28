package com.tsing.grid.service.app.imp;

import com.github.pagehelper.PageHelper;
import com.tsing.grid.entity.app.Grid;
import com.tsing.grid.mapper.GridMapper;
import com.tsing.grid.service.app.GridService;
import com.tsing.grid.util.PageUtils;
import com.tsing.grid.vo.req.GridPageReqVO;
import com.tsing.grid.vo.resp.GridDataRespVO;
import com.tsing.grid.vo.resp.GridPageRespVO;
import com.tsing.grid.vo.resp.GridRespVO;
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
public class GridServiceImpl implements GridService {

    @Autowired
    private GridMapper gridMapper;


    @Override
    public PageRespVO<GridPageRespVO> findPage(GridPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<GridPageRespVO> grids = gridMapper.getList(vo);
        return PageUtils.getPageVO(grids);
    }


    @Override
    public List<GridRespVO> selectByCid(Integer cid) {
        return gridMapper.selectByCid(cid);
    }

    @Override
    public List<GridDataRespVO> findByName(String name) {
        return gridMapper.findByName(name);
    }
}
