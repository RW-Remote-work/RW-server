package com.rwws.rwserver.module.job.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rwws.rwserver.domain.AbstractAuditingEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 远程工作表
 * @TableName rw_job
 */
@TableName(value ="rw_job")
@Data
public class RwJob extends AbstractAuditingEntity implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 岗位编号
     */
    private String jobCode;

    /**
     * 岗位名称
     */
    private String jobName;

    /**
     * 职位分类ID
     */
    private Long jobClassId;

    /**
     * 工作类型（1:全职远程/2:兼职远程）
     */
    private Integer jobType;

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
    private String deliverWechat;

    /**
     * 投递telegram
     */
    private String deliverTelegram;

    /**
     * 投递网址
     */
    private String deliverWebsite;

    /**
     * 薪资表示类型（1:月薪，2:年薪）
     */
    private Integer salaryType;

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
    private String jobDescription;

    /**
     * 岗位职责
     */
    private String jobDuty;

    /**
     * 岗位要求
     */
    private String jobRequirement;

    /**
     * 公司/团队介绍
     */
    private String companyInfo;

    /**
     * 岗位标签
     */
    private String jobLabel;

    /**
     * 状态（1:待审核，2:在线中、3:已下线/失效、4:不通过）
     */
    private Integer jobStatus;

    /**
     * 职位发布人ID
     */
    private Long jobPublisherId;

    /**
     * 职位发布时间
     */
    private Date jobPublishTime;

    /**
     * 最新审批人ID
     */
    private Long latestApproveUserId;

    /**
     * 最新审批时间
     */
    private Date latestApproveTime;

    /**
     * 最新审批理由
     */
    private String latestApproveReason;

    /**
     * 下线理由
     */
    private Integer offlineReasonId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}