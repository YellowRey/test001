package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    /**
     * 根据分类id查询分组
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        //根据cid查出所有的分组，需要用select
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.groupMapper.select(specGroup);
    }

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    @Override
    public List<SpecParam> queryParams(Long gid) {
        SpecParam param = new SpecParam();
        param .setGroupId(gid);
        return this.specParamMapper.select(param);
    }


    /**
     * 通过id删除规格参数组
     * @param id
     * @return 失败则返回空
     */
    @Override
    public ResponseEntity<Void> deleteGroup(Long id) {
        SpecGroup specGroup= new SpecGroup();
        specGroup.setId(id);
        this.groupMapper.delete(specGroup);
        return null;
    }

    /**
     * 更新规格参数组
     * @param specGroup
     * @return
     */
    @Override
    public void updataGroup(SpecGroup specGroup) {
        SpecGroup sg = new SpecGroup();
        sg.setCid(specGroup.getCid());
        sg.setName(specGroup.getName());
        this.groupMapper.insertSelective(sg);
    }


}
