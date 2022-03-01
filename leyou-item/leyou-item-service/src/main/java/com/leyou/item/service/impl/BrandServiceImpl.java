package com.leyou.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 品牌的业务层实现类
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer row, String sortBy, Boolean desc) {
        //创建example对象完成
        Example example = new Example(Brand.class);
        //根据name模糊查询，或者根据首字母查询
        //创建Criteria对象
        Example.Criteria criteria = example.createCriteria();
        //判断key是否为空
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%" + key + "%").orEqualTo("letter",key);
        }

        //添加分页条件
        PageHelper.startPage(page, row);
        //添加排序条件
        if(StringUtils.isNotBlank(sortBy)){         //三元表达式
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        //执行
        List<Brand> brands = this.brandMapper.selectByExample(example);
        //包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        //包装成分页结果集并返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌brand
        this.brandMapper.insertSelective(brand);

        //在新增中间表
        cids.forEach(cid ->{
            //这里使用了自定义的更新方法，更新中间表
            this.brandMapper.insertCategoryAndBrand(cid,brand.getId());
        });
    }
}
