package com.tsing.grid;

import com.tsing.grid.mapper.CommunityMapper;
import com.tsing.grid.mapper.DrugerMapper;
import com.tsing.grid.mapper.GridMapper;
import com.tsing.grid.mapper.GridmanMapper;
import com.tsing.grid.service.app.DrugerService;
import com.tsing.grid.vo.req.DrugerAddReqVO;
import com.tsing.grid.vo.resp.GridDataRespVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GridApplicationTests {


    @Autowired
    private DrugerMapper drugerMapper;
    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private GridmanMapper gridmanMapper;
    @Autowired
    private DrugerService drugerService;

    @Autowired
    private GridMapper gridMapper;


    @Test
    void contextLoads() {

        List<GridDataRespVO> gridDataRespVOS = gridMapper.findByName("和");
        System.out.println(gridDataRespVOS);
    }

}
