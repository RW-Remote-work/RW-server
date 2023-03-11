package com.rwws.rwserver.module.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rwws.rwserver.module.job.domain.Job;
import com.rwws.rwserver.module.job.domain.QueryJobsParamDTO;
import com.rwws.rwserver.module.job.domain.QueryJobsResultDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author changbohuang
 * @description 针对表【rw_job(远程工作表)】的数据库操作Mapper
 * @createDate 2023-03-10 11:39:07
 * @Entity com.rwws.rwserver.module.job.domain.RwJob
 */
@Component
public interface JobMapper extends BaseMapper<Job> {

    Page<QueryJobsResultDTO> queryJobs(Page<QueryJobsResultDTO> pageParam, @Param("query") QueryJobsParamDTO params);
}
