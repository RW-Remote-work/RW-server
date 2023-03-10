package com.rwws.rwserver.service;

import com.rwws.rwserver.domain.JobClass;
import com.rwws.rwserver.mapper.JobClassMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位分类服务层
 * @Author ko
 * @Date 2023/3/10 16:50
 * @Version 1.0
 */

@Service
public class JobClassService {

    private final JobClassMapper jobClassMapper;

    public JobClassService(JobClassMapper jobClassMapper) {
        this.jobClassMapper = jobClassMapper;
    }

    //查询列表
    public List<JobClass> list(){
        return null;
    }
    //增加
    public void add(JobClass jobClass){

    }


}
