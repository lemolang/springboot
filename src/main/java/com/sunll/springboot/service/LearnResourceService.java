package com.sunll.springboot.service;

import com.github.pagehelper.PageInfo;
import com.sunll.springboot.entity.LearnResource;

/**
 * Created by Administrator
 * on 2018/2/11
 */
public interface LearnResourceService {

    int deleteByPrimaryKey(Long id);

    int insert(LearnResource record);

    int insertSelective(LearnResource record);

    LearnResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LearnResource record);

    int updateByPrimaryKey(LearnResource record);

    PageInfo<LearnResource> selectBy(int pageNum,int pageSize);
}
