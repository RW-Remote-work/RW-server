package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.controller.request.job.AddJobClassRequest;
import com.rwws.rwserver.domain.JobClass;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.mapper.JobClassMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 职位分类服务层
 *
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
    public List<JobClass> list() {
        return null;
    }

    //增加
    public void add(AddJobClassRequest request) {
        request.getJobClasses().forEach(this::add);
    }

    private void add(AddJobClassRequest.JobClass requestJobClass) {
        //检查表里是否存在相同的数据
        var queryWrapper = new QueryWrapper<JobClass>().eq("job_chn", requestJobClass.getJobChn());
        if (jobClassMapper.exists(queryWrapper)) {
            throw new BadRequestProblem("该职业分类已存在，请勿重复添加");
        }
        var jobClass = new JobClass();
        jobClass.setJobEng(requestJobClass.getJobEng());
        jobClass.setJobChn(requestJobClass.getJobChn());
        jobClass.setRemark(requestJobClass.getRemark());
        this.jobClassMapper.insert(jobClass);
    }

}
