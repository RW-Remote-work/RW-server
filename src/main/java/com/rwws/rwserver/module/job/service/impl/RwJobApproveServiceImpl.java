package com.rwws.rwserver.module.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rwws.rwserver.module.job.domain.RwJobApprove;
import com.rwws.rwserver.module.job.mapper.RwJobApproveMapper;
import com.rwws.rwserver.module.job.service.RwJobApproveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changbohuang
 * @description 针对表【rw_job_approve(远程工作审批记录表)】的数据库操作Service实现
 * @createDate 2023-03-10 11:39:07
 */
@Service
@Slf4j
public class RwJobApproveServiceImpl extends ServiceImpl<RwJobApproveMapper, RwJobApprove>
        implements RwJobApproveService {

}
