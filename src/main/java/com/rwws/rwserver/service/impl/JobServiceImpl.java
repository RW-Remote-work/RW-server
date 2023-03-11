package com.rwws.rwserver.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rwws.rwserver.domain.Job;
import com.rwws.rwserver.mapper.JobMapper;
import com.rwws.rwserver.service.RwJobService;
import com.rwws.rwserver.transfer.QueryJobsParamDTO;
import com.rwws.rwserver.transfer.QueryJobsResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changbohuang
 * @description 针对表【rw_job(远程工作表)】的数据库操作Service实现
 * @createDate 2023-03-10 11:39:07
 */
@Service
@Slf4j
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
        implements RwJobService {

    @Override
    public Page<QueryJobsResultDTO> queryJobs(Page<QueryJobsResultDTO> pageParam, QueryJobsParamDTO params) {
        return baseMapper.queryJobs(pageParam, params);
    }
}
