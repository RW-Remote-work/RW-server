package com.rwws.rwserver.controller;

import com.rwws.rwserver.common.util.oConvertUtils;
import com.rwws.rwserver.domain.JobClass;
import com.rwws.rwserver.domain.Region;
import com.rwws.rwserver.service.JobClassService;
import com.rwws.rwserver.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职位分类接口
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
     * @author keyi
     * @Date 2023/2/17 22:41
     * @param
     * @return
     */
    @GetMapping("/list")
    public List<JobClass> list(){

        return this.jobClassService.list();
    }


    /**
     * 添加职位分类(批量)
     * @author keyi
     * @Date 2023/2/17 22:45
     * @param list 需要添加的国家（地区）列表
     * @return 是否添加成功
     */
    @PostMapping("/add")
    /*@PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")*/
    public boolean add (@RequestBody(required = false) List<JobClass> list){

        if(oConvertUtils.isNotEmpty(list)){
            list.forEach(it->{
                this.jobClassService.add(it);
            });
        }
        return true;
    }

}
