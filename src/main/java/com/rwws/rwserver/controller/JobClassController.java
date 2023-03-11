package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddJobClassRequest;
import com.rwws.rwserver.domain.JobClass;
import com.rwws.rwserver.service.JobClassService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位分类接口
 *
 * @Author ko
 * @Date 2023/3/10 16:47
 * @Version 1.0
 */

@RestController
@RequestMapping("/jobclass")
public class JobClassController {

    private final JobClassService jobClassService;

    public JobClassController(JobClassService jobClassService) {
        this.jobClassService = jobClassService;
    }

    /**
     * 获取职位分类列表
     *
     * @param
     * @return
     * @author keyi
     * @Date 2023/2/17 22:41
     */
    @GetMapping("/list")
    public List<JobClass> list() {
        return this.jobClassService.list();
    }


    /**
     * 添加职位分类(批量)
     *
     * @param list 需要添加的国家（地区）列表
     * @return 是否添加成功
     * @author keyi
     * @Date 2023/2/17 22:45
     */
    @PostMapping("/add")
    /*@PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")*/
    public void add(@Validated @RequestBody AddJobClassRequest request) {
        jobClassService.add(request);
    }

}
