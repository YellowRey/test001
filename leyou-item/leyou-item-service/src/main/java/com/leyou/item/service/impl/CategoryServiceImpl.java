package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 根据父结点id查询出子结点
     * @param pid
     * @return  List<Category>
     */
    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        //select这个返回值为list。需要传入pojo类对象
        return this.categoryMapper.select(record);

    }
    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @Override
    public List<Category> queryByBrandId(long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }


}
