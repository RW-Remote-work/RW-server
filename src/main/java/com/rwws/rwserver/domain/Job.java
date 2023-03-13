package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.emums.JobSalaryType;
import com.rwws.rwserver.domain.emums.JobStatus;
import com.rwws.rwserver.domain.emums.JobType;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

/**
 * 远程工作表
 *
 * @TableName job
 */
@Data
public class Job extends AbstractAuditingEntity {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 岗位编号
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 职位分类ID
     */
    private Long jobClassId;

    /**
     * 工作类型（1:全职远程/2:兼职远程）
     */
    private JobType type;

    /**
     * 国家/地区ID
     */
    private Long regionId;

    /**
     * 投递邮箱
     */
    private String deliverEmail;

    /**
     * 投递微信号
     */
    @Nullable
    private String deliverWechat;

    /**
     * 投递telegram
     */
    @Nullable
    private String deliverTelegram;

    /**
     * 投递网址
     */
    @Nullable
    private String deliverWebsite;

    /**
     * 薪资表示类型（1:月薪，2:年薪）
     */
    private JobSalaryType salaryType;

    /**
     * 薪资币种
     */
    private Long moneyTypeId;

    /**
     * min薪资
     */
    private Long salaryMin;

    /**
     * max薪资
     */
    private Long salaryMax;

    /**
     * 岗位描述
     */
    private String description;

    /**
     * 岗位职责
     */
    private String duty;

    /**
     * 岗位要求
     */
    private String requirement;

    /**
     * 公司/团队介绍
     */
    private String companyInfo;

    /**
     * 岗位标签
     */
    private String label;

    /**
     * 状态（1:待审核，2:在线中、3:已下线/失效、4:不通过）
     */
    private JobStatus status;

    /**
     * 职位发布人ID
     */
    private Long publisherId;

    /**
     * 职位发布时间
     */
    private Instant publishTime;

    /**
     * 最新审批人ID
     */
    @Nullable
    private Long latestApproveUserId;

    /**
     * 最新审批时间
     */
    @Nullable
    private Instant latestApproveTime;

    /**
     * 最新审批理由
     */
    @Nullable
    private String latestApproveReason;

    /**
     * 下线理由
     */
    @Nullable
    private Long offlineReasonId;
}
