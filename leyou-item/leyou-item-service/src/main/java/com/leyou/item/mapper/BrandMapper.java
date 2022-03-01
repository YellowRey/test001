package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;


public interface BrandMapper extends Mapper<Brand> {
    /**
     * 新增商品分类和品牌中间表的数据
     * @param cid   分类的id
     * @param bid   商品的id
     * @return
     */
    @Insert("insert into tb_category_brand(category_id,brand_id) values (#{cid},#{bid})")
    public int insertCategoryAndBrand(
            @Param("cid")Long cid,
            @Param("bid")Long bid
            );

}
