package com.rwws.rwserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rwws.rwserver.domain.Job;
import com.rwws.rwserver.transfer.QueryJobsParamDTO;
import com.rwws.rwserver.transfer.QueryJobsResultDTO;
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
