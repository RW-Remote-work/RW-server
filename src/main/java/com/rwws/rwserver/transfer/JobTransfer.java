package com.rwws.rwserver.transfer;

import com.rwws.rwserver.controller.response.job.ListJobResponse;
import com.rwws.rwserver.domain.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface JobTransfer {
    JobTransfer INSTANCE = Mappers.getMapper(JobTransfer.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "jobClassId", target = "jobClassId")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "regionId", target = "regionId")
    @Mapping(source = "deliverEmail", target = "deliverEmail")
    @Mapping(source = "deliverWechat", target = "deliverWechat")
    @Mapping(source = "deliverTelegram", target = "deliverTelegram")
    @Mapping(source = "deliverWebsite", target = "deliverWebsite")
    @Mapping(source = "salaryType", target = "salaryType")
    @Mapping(source = "moneyTypeId", target = "moneyTypeId")
    @Mapping(source = "salaryMin", target = "salaryMin")
    @Mapping(source = "salaryMax", target = "salaryMax")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "duty", target = "duty")
    @Mapping(source = "requirement", target = "requirement")
    @Mapping(source = "companyInfo", target = "companyInfo")
    @Mapping(source = "label", target = "label")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "publisherId", target = "publisherId")
    @Mapping(source = "publishTime", target = "publishTime")
    @Mapping(source = "latestApproveUserId", target = "latestApproveUserId")
    @Mapping(source = "latestApproveTime", target = "latestApproveTime")
    @Mapping(source = "latestApproveReason", target = "latestApproveReason")
    @Mapping(source = "offlineReasonId", target = "offlineReasonId")
    ListJobResponse.Job toListJobResponseJob(Job job);

}
