package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 远程工作审批记录表
 *
 * @TableName rw_job_approve
 */
@TableName(value = "job_approve")
@Data
public class JobApprove extends AbstractAuditingEntity implements Serializable {
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
    private Date approveTime;

    /**
     * 审批结果（1:通过，2:拒绝）
     */
    private Integer approveStatus;

    /**
     * 审批理由
     */
    private String approveReason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
