package com.rwws.rwserver.module.job.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobTransfer {
    JobTransfer INSTANCE = Mappers.getMapper(JobTransfer.class);

    //    @Mapping(target = "jobName", source = "jobName")
    Job dtoToBean(AddRwJobRequest dto);

}
