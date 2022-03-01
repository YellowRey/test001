package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 规格参数组控制层
 */
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationServiceImpl;


    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){
        List<SpecGroup> groups =this.specificationServiceImpl.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);

    }

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam("gid")Long gid){
        List<SpecParam> params = this.specificationServiceImpl.queryParams(gid);
        if(CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
    }

    /**
     * 通过id删除规格参数组
     * @param id 规格参数组的id
     * @return
     */
    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("id")Long id){
        //判断参数是否为空
        if(id == null){
            return ResponseEntity.badRequest().build();
        }
        this.specificationServiceImpl.deleteGroup(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新规格参数组
     * @param specGroup
     * @return
     */
    @PostMapping("group")
    public ResponseEntity<Void> updataGroup(@RequestBody SpecGroup specGroup){
        this.specificationServiceImpl.updataGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
