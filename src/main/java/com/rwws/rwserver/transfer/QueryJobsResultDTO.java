package com.rwws.rwserver.transfer;

import lombok.Data;

import java.util.Date;

/**
 * mapper.job.JobMapper.queryJobs的查询结果集
 *
 * @author huang
 * @since 2023-03-10
 */
@Data
public class QueryJobsResultDTO {

    private Long id;

    private String jobCode;

    private String jobName;

    private Long jobClassId;

    private String jobClassChn;

    private String jobClassEng;

    private Integer jobType;

    private Long regionId;

    private String regionNameChn;

    private String regionNameEng;

    private String deliverWebsite;

    private Integer salaryType;

    private Long moneyTypeId;

    private String moneyTypeChn;

    private String moneyTypeCode;

    private Long salaryMin;

    private Long salaryMax;

    private String jobDescription;

    private String jobDuty;

    private String jobRequirement;

    private String companyInfo;

    private String jobLabel;

    private Integer jobStatus;

    private Long jobPublisherId;

    private String jobPublisherName;

    private Date jobPublishTime;

    private Long latestApproveUserId;

    private String latestApproveUserName;

    private Date latestApproveTime;

    private String latestApproveReason;

    private Integer offlineReasonId;

}
