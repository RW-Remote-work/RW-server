package com.rwws.rwserver.module.job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwws.rwserver.module.job.domain.RwJobApprove;
import org.springframework.stereotype.Component;

/**
 * @author changbohuang
 * @description 针对表【rw_job_approve(远程工作审批记录表)】的数据库操作Mapper
 * @createDate 2023-03-10 11:39:07
 * @Entity com.rwws.rwserver.module.job.domain.RwJobApprove
 */
@Component
public interface RwJobApproveMapper extends BaseMapper<RwJobApprove> {

}
