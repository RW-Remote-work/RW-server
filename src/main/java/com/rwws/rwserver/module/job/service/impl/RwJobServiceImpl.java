package com.rwws.rwserver.module.job.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rwws.rwserver.module.job.domain.Job;
import com.rwws.rwserver.module.job.domain.QueryJobsParamDTO;
import com.rwws.rwserver.module.job.domain.QueryJobsResultDTO;
import com.rwws.rwserver.module.job.mapper.JobMapper;
import com.rwws.rwserver.module.job.service.RwJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changbohuang
 * @description 针对表【rw_job(远程工作表)】的数据库操作Service实现
 * @createDate 2023-03-10 11:39:07
 */
@Service
@Slf4j
public class RwJobServiceImpl extends ServiceImpl<JobMapper, Job>
        implements RwJobService {

    @Override
    public Page<QueryJobsResultDTO> queryJobs(Page<QueryJobsResultDTO> pageParam, QueryJobsParamDTO params) {
        return baseMapper.queryJobs(pageParam, params);
    }
}
