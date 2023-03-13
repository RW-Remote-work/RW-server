package com.rwws.rwserver.controller.request.job;

import lombok.Data;

import java.time.Instant;

@Data
public class PagingJobRequest {

    private Long jobClassId;

    private Integer jobType;

    private Long regionId;

    private Instant jobPublishTimeStart;

    private Instant jobPublishTimeEnd;

    private Long jobPublisherId;

    private String jobCode;

    private Integer jobStatus;

    private String keyword;
}
