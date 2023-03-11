package com.rwws.rwserver.module.job.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.module.job.domain.*;
import com.rwws.rwserver.module.job.service.RwJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    private final RwJobService rwJobService;

    public JobController(RwJobService rwJobService) {
        this.rwJobService = rwJobService;
    }


    @GetMapping(value = "/list")
    public ResponseEntity<Page<QueryJobsResultDTO>> list(QueryJobsParamDTO param) {
        if (param.getCurrent() == null) {
            param.setCurrent(1);
        }
        if (param.getPageSize() == null) {
            param.setPageSize(10);
        }
        Page<QueryJobsResultDTO> aPage = rwJobService.queryJobs(new Page<>(param.getCurrent(), param.getPageSize()), param);
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @PostMapping("/addJob")
    public void addJob(@AuthenticationPrincipal UserPrincipal _principal,
                       @Validated @RequestBody AddRwJobRequest request) {

        log.info(request.toString());
        Job job = JobTransfer.INSTANCE.dtoToBean(request);

        job.setJobPublisherId(_principal.getId());
        job.setJobPublishTime(new Date());

        log.info(job.toString());
        rwJobService.save(job);
    }

}
