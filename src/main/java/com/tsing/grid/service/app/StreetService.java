package com.tsing.grid.service.app;

import com.tsing.grid.vo.PolygonVO;

public interface StreetService {
    PolygonVO getLast();
    PolygonVO edit(PolygonVO vo);
}
