package com.rwws.rwserver.controller.job;

import com.rwws.rwserver.common.util.MybatisPlusUtil;
import com.rwws.rwserver.controller.request.job.AddJobRequest;
import com.rwws.rwserver.controller.request.job.ListJobRequest;
import com.rwws.rwserver.controller.response.job.ListJobResponse;
import com.rwws.rwserver.domain.security.UserPrincipal;
import com.rwws.rwserver.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
@Slf4j
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping(value = "")
    public ListJobResponse listJob(@ParameterObject @PageableDefault Pageable pageable,
                                   @RequestBody(required = false) ListJobRequest request) {
        return jobService.listJob(MybatisPlusUtil.toPage(pageable), request);
    }

    @PostMapping("")
    public void addJob(@AuthenticationPrincipal UserPrincipal _principal,
                       @Validated @RequestBody AddJobRequest request) {
        jobService.addJob(_principal.getId(), request);
    }

}
