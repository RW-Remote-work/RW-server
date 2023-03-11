package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rwws.rwserver.domain.Job;
import com.rwws.rwserver.transfer.QueryJobsParamDTO;
import com.rwws.rwserver.transfer.QueryJobsResultDTO;

/**
 * @author changbohuang
 * @description 针对表【rw_job(远程工作表)】的数据库操作Service
 * @createDate 2023-03-10 11:39:07
 */
public interface RwJobService extends IService<Job> {


    Page<QueryJobsResultDTO> queryJobs(Page<QueryJobsResultDTO> pageParam, QueryJobsParamDTO params);
}
