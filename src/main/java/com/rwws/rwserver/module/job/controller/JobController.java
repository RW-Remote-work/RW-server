package com.rwws.rwserver.module.job.controller;

import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.module.job.domain.AddRwJobRequest;
import com.rwws.rwserver.module.job.domain.RwJob;
import com.rwws.rwserver.module.job.domain.RwJobTransfer;
import com.rwws.rwserver.module.job.service.RwJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    private final RwJobService rwJobService;

    public JobController(RwJobService rwJobService) {
        this.rwJobService = rwJobService;
    }


    @PostMapping("/addJob")
    public void addJob(@AuthenticationPrincipal UserPrincipal _principal,
                         @Validated @RequestBody AddRwJobRequest request) {

        log.info(request.toString());
        RwJob rwJob = RwJobTransfer.INSTANCE.dtoToBean(request);

        rwJob.setJobPublisherId(_principal.getId());
        rwJob.setJobPublishTime(new Date());

        log.info(rwJob.toString());
        rwJobService.save(rwJob);
    }

}
