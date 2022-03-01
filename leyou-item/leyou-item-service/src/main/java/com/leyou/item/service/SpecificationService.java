package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecificationService {

    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    List<SpecGroup> queryGroupsByCid(Long cid);

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    List<SpecParam> queryParams(Long gid);

    /**
     * 通过id删除规格参数组
     * @param id 规格参数组的id
     * @return
     */
    ResponseEntity<Void> deleteGroup(Long id);


    /**
     * 更新规格参数组
     * @param specGroup
     * @return
     */
    void updataGroup(SpecGroup specGroup);
}
