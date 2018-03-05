package com.sunll.springboot.mapper;

import com.sunll.springboot.entity.LearnResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LearnResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LearnResource record);

    int insertSelective(LearnResource record);

    LearnResource selectByPrimaryKey(Long id);

    List<LearnResource> selectBy();

    int updateByPrimaryKeySelective(LearnResource record);

    int updateByPrimaryKey(LearnResource record);
}