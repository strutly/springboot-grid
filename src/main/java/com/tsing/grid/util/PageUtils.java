package com.tsing.grid.util;

import com.github.pagehelper.Page;
import com.tsing.grid.vo.resp.PageRespVO;

import java.util.List;

/**
 * 分页管理组装为layui框架的结构
 */
public class PageUtils {
	private PageUtils() {}

    public static <T> PageRespVO<T> getPageVO(List<T> list) {
        PageRespVO<T> result = new PageRespVO<>();
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            result.setTotalRows(page.getTotal());
            result.setTotalPages(page.getPages());
            result.setPageNum(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setCurPageSize(page.size());
            result.setList(page.getResult());
        }
        return result;
    }
}
