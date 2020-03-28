package com.tsing.grid.service.app.imp;

import com.tsing.grid.entity.app.Street;
import com.tsing.grid.mapper.StreetMapper;
import com.tsing.grid.service.app.StreetService;
import com.tsing.grid.vo.PolygonVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author lj
 * @Date 2020/3/9 10:38
 * @Version 1.0
 */
@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    private Street findLast(){
        Integer id = streetMapper.maxId();
        if(null==id){
            return null;
        }
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public PolygonVO getLast() {
        Street street = findLast();
        if(null==street){
            return null;
        }
        PolygonVO streetRespVo = new PolygonVO();
        BeanUtils.copyProperties(street,streetRespVo);
        return streetRespVo;
    }

    @Override
    public PolygonVO edit(PolygonVO vo) {
        Street street = findLast();
        if(null == street){
            street = new Street();
            street.setId(1);
            street.setCreateTime(new Date());
            BeanUtils.copyProperties(vo,street);
            street.setUpdateTime(new Date());
            streetMapper.insert(street);
        }
        BeanUtils.copyProperties(vo,street);
        street.setUpdateTime(new Date());
        streetMapper.updateByPrimaryKeySelective(street);
        return vo;
    }
}
