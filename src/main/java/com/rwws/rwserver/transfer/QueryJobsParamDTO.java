package com.rwws.rwserver.transfer;

import lombok.Data;

import java.util.Date;

/**
 * 查询参数
 *
 * @author
 * @since 2023-03-10
 */
@Data
public class QueryJobsParamDTO {

    private Long jobClassId;

    private Integer jobType;

    private Long regionId;

    private Date jobPublishTimeStart;

    private Date jobPublishTimeEnd;

    private Long jobPublisherId;

    private String jobCode;

    private Integer jobStatus;

    private String keyword;

    private Integer current;

    private Integer pageSize;
}
