package com.rwws.rwserver.transfer;

import com.rwws.rwserver.controller.request.AddRwJobRequest;
import com.rwws.rwserver.domain.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobTransfer {
    JobTransfer INSTANCE = Mappers.getMapper(JobTransfer.class);

    //    @Mapping(target = "jobName", source = "jobName")
    Job dtoToBean(AddRwJobRequest dto);

}
