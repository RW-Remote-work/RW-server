package com.rwws.rwserver.service.job;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.job.AddJobRequest;
import com.rwws.rwserver.controller.request.job.ListJobRequest;
import com.rwws.rwserver.controller.response.job.ListJobResponse;
import com.rwws.rwserver.domain.Job;
import com.rwws.rwserver.mapper.job.JobMapper;
import com.rwws.rwserver.transfer.JobTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changbohuang
 * @description 针对表【rw_job(远程工作表)】的数据库操作Service实现
 * @createDate 2023-03-10 11:39:07
 */
@Service
@Slf4j
public class JobService {
    private final JobMapper jobMapper;
    private final JobTransfer jobTransfer;

    public JobService(JobMapper jobMapper,
                      JobTransfer jobTransfer) {
        this.jobMapper = jobMapper;
        this.jobTransfer = jobTransfer;
    }

    public ListJobResponse listJob(IPage<Job> page, ListJobRequest request) {
        var result = jobMapper.selectPage(
                page,
                Wrappers.<Job>lambdaQuery().eq(Job::getJobClassId, request.getJobClassId())
                        .eq(Job::getJobType, request.getJobType())
                        .eq(Job::getRegionId, request.getRegionId())
                        .between(Job::getJobPublishTime, request.getJobPublishTimeStart(), request.getJobPublishTimeEnd())
                        .eq(Job::getJobPublisherId, request.getJobPublisherId())
                        .eq(Job::getJobCode, request.getJobCode())
                        .eq(Job::getJobStatus, request.getJobStatus())
                        .like(Job::getJobName, request.getKeyword())
        ).convert(jobTransfer::toListJobResponseJob);
        var response = new ListJobResponse();
        response.setSize(result.getSize());
        response.setTotal(result.getTotal());
        response.setCurrent(result.getCurrent());
        response.setJobs(response.getJobs());
        return response;
    }

    public void addJob(Long userId, AddJobRequest request) {
        //todo
    }

}
