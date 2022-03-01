package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

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

    /**
     * 新增品牌
     * @param brand 品牌的对象
     * @param cids  商品分类的id集合
     * @return
     */
    void saveBrand(Brand brand, List<Long> cids);
}
