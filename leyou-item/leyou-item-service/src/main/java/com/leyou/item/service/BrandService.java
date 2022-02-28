package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

/**
 * 品牌的业务层接口
 */
public interface BrandService {

    /**
     * 根据查询条件分页 并 查询品牌信息
     * @param key      搜索关键词
     * @param page     当前页
     * @param row       每页大小
     * @param sortBy    排序字符
     * @param desc      升序还是降序
     * @return PageResult<Brand>
     */
    PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer row, String sortBy, Boolean desc);
}
