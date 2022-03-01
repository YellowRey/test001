package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.interfaces.RSAKey;
import java.util.List;

/**
 * item-service的controller类
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryServiceImpl;

    /**
     * 根据父结点id查询出子结点
     * @param pid
     * @return List<Category>
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam("pid")Long pid){
        //判断参数合法性
        if(pid == null || pid.longValue() < 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //查询后的结果
        List<Category> categories = this.categoryServiceImpl.queryCategoriesByPid(pid);
        //判断结合是否为空
        if(CollectionUtils.isEmpty(categories)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //响应给浏览器
        return ResponseEntity.ok(categories);
    }

    /**
     * 通过品牌id查询商品分类
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid")long bid){
        List<Category> list = this.categoryServiceImpl.queryByBrandId(bid);
        //判断集合手是否为空
        if(list == null || list.size() < 1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

}
