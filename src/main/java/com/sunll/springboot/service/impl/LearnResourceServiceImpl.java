package com.sunll.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunll.springboot.entity.LearnResource;
import com.sunll.springboot.mapper.LearnResourceMapper;
import com.sunll.springboot.service.LearnResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator
 * on 2018/2/11
 */
@Service
@Transactional(readOnly = true)
public class LearnResourceServiceImpl implements LearnResourceService {

    @Autowired
    private LearnResourceMapper learnResourceMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(LearnResource record) {
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false,
            isolation = Isolation.DEFAULT)
    public int insertSelective(LearnResource record) {
        /*try {
            int num = learnResourceMapper.insertSelective(record);
            *//*if (num == 1) {
                throw new RuntimeException("事务异常测试");
            }*//*
            LearnResource learnResource1 = new LearnResource();
            learnResource1.setAuthor("sunll1");
            learnResource1.setId((long) 11);
            learnResource1.setTitle("springboot1");
            learnResource1.setUrl("www.baidu.com1");
            learnResourceMapper.insertSelective(learnResource1);
        }catch (Exception e){
            e.printStackTrace();
            //如果在try catch中使用事务则在catch中需要加上这句代码否则AOP捕获不到，事务不起作用
            //或者这catch中抛出异常
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }*/

        int num = learnResourceMapper.insertSelective(record);
        /*if (num == 1) {
            throw new RuntimeException("事务异常测试");
        }*/
        LearnResource learnResource1 = new LearnResource();
        learnResource1.setAuthor("sunll1");
        learnResource1.setId((long) 11);
        learnResource1.setTitle("springboot1");
        learnResource1.setUrl("www.baidu.com1");
        learnResourceMapper.insertSelective(learnResource1);
        return 1;
    }

    @Override
    public LearnResource selectByPrimaryKey(Long id) {
        return learnResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<LearnResource> selectBy(int pageNum, int pageSize) {
        //1、设置分页信息，包括当前页数和每页显示的总计数
        PageHelper.startPage(pageNum, pageSize);
        //2、执行查询
        List<LearnResource> list = learnResourceMapper.selectBy();
        //3、获取分页查询后的数据
        return new PageInfo<>(list);
    }

    @Override
    public int updateByPrimaryKeySelective(LearnResource record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(LearnResource record) {
        return 0;
    }
}
