package com.rwws.rwserver.controller.response.job;

import com.rwws.rwserver.controller.response.PagingResponse;
import com.rwws.rwserver.domain.emums.JobSalaryType;
import com.rwws.rwserver.domain.emums.JobStatus;
import com.rwws.rwserver.domain.emums.JobType;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class PagingJobResponse extends PagingResponse {
    private List<Job> jobs = List.of();

    @Data
    public static class Job {
        private Long id;

        private String code;

        private String name;

        private Long jobClassId;

        private String jobClassChn;

        private String jobClassEng;

        private JobType type;

        private Long regionId;

        private String regionNameChn;

        private String regionNameEng;

        private String deliverWebsite;
        private String deliverEmail;
        private String deliverWechat;
        private String deliverTelegram;

        private JobSalaryType salaryType;

        private Long moneyTypeId;

        private String moneyTypeChn;

        private String moneyTypeCode;

        private Long salaryMin;

        private Long salaryMax;

        private String description;

        private String duty;

        private String requirement;

        private String companyInfo;

        private String label;

        private JobStatus status;

        private Long publisherId;

        private String publisherName;

        private Instant publishTime;

        private Long latestApproveUserId;

        private String latestApproveUserName;

        private Instant latestApproveTime;

        private String latestApproveReason;

        private Integer offlineReasonId;
    }
}
