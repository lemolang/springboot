package com.sunll.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.sunll.springboot.entity.LearnResource;
import com.sunll.springboot.service.LearnResourceService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by sunll
 * on 2018/2/11
 */
@RestController
@RequestMapping("/learnResource")
@Validated
public class LearnResourceController {

    @Autowired
    private LearnResourceService learnResourceService;

    @GetMapping("/{id}")
    public LearnResource selectById(@PathVariable("id")
                                        @Range(min = 1, max = 9, message = "年级只能从1-9") Long id){
        return learnResourceService.selectByPrimaryKey(id);
    }

    @PostMapping("/2")
    public boolean insert(@RequestBody @Valid LearnResource learnResource){
        int num = learnResourceService.insertSelective(learnResource);
        return num>0;
    }

    @GetMapping("/list")
    public PageInfo<LearnResource> select(int pageNum,int pageSize){
        return learnResourceService.selectBy(pageNum,pageSize);
    }

}
