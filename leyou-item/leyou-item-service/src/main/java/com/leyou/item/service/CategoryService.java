package com.leyou.item.service;

import com.leyou.item.pojo.Category;


import java.util.List;

/**
 * item-service接口
 */

public interface CategoryService {

    /**
     * 根据父结点id查询出子结点
     * @param pid
     * @return  List<Category>
     */
    public List<Category> queryCategoriesByPid(Long pid);


}
