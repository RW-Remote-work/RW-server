package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.emums.JobApproveStatus;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

/**
 * 远程工作审批记录表
 *
 * @TableName job_approve
 */
@Data
public class JobApprove extends AbstractAuditingEntity {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 岗位表ID
     */
    private Long jobId;

    /**
     * 审批人ID
     */
    private Long approveUserId;

    /**
     * 审批时间
     */
    private Instant approveTime;

    /**
     * 审批结果（1:通过，2:拒绝）
     */
    private JobApproveStatus approveStatus;

    /**
     * 审批理由
     */
    @Nullable
    private String approveReason;

}
