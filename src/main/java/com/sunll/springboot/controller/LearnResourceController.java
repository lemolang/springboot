package com.sunll.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.sunll.springboot.common.RedisUtils;
import com.sunll.springboot.entity.LearnResource;
import com.sunll.springboot.service.LearnResourceService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

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
    @Autowired
    private RedisUtils redisUtils;

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

    @PostMapping("/3")
    public int updateByPrimaryKeySelective(@RequestBody @Valid LearnResource record) {
        return learnResourceService.updateByPrimaryKeySelective(record);
    }

    @GetMapping("/setRedis")
    public boolean setRedis( String key,String value){
        return redisUtils.set(key,value);
    }

    @GetMapping("/getRedis")
    public String getRedis( String key){
        return (String) redisUtils.get(key);
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

}
