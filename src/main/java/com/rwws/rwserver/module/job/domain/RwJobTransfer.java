package com.rwws.rwserver.module.job.domain;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RwJobTransfer {
    RwJobTransfer INSTANCE = Mappers.getMapper(RwJobTransfer.class);

    //    @Mapping(target = "jobName", source = "jobName")
    RwJob dtoToBean(AddRwJobRequest dto);

}
