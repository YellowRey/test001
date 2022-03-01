package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 品牌信息的controller类
 */
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandServiceImpl;


    /**
     * 根据查询条件分页 并 查询品牌信息
     * @param key      搜索关键词
     * @param page     当前页
     * @param row       每页大小
     * @param sortBy    排序字符
     * @param desc      升序还是降序
     * @return PageResult<Brand>
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key" , required = false)String key,
            @RequestParam(value = "page" , defaultValue = "1")Integer page,
            @RequestParam(value = "row" , defaultValue = "5")Integer row,
            @RequestParam(value = "sortBy" , required = false)String sortBy,
            @RequestParam(value = "desc" , required = false)Boolean desc
    ){
        PageResult<Brand> result =this.brandServiceImpl.queryBrandsByPage(key,page,row,sortBy,desc);

        //判断是否为空
        if(CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * @param brand 品牌的对象
     * @param cids  商品分类的id集合
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(
            Brand brand,
            @RequestParam("cids")List<Long> cids
    ) {
        this.brandServiceImpl.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
