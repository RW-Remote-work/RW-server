package com.rwws.rwserver.controller;

import com.rwws.rwserver.common.util.oConvertUtils;
import com.rwws.rwserver.domain.Region;
import com.rwws.rwserver.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 国家地区接口
 * @Author ko
 * @Date 2023/3/10 11:48
 * @Version 1.0
 */

@RestController
@RequestMapping("/region")
public class RegionController {


    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    /**
     * 获取国家地球列表
     * @author keyi
     * @Date 2023/2/17 22:41
     * @param
     * @return
     */
    @GetMapping("/list")
    public List<Region> list(){

        return this.regionService.list();
    }

    /**
     * 添加国家地区(批量？)
     * @author keyi
     * @Date 2023/2/17 22:45
     * @param list 需要添加的国家（地区）列表
     * @return 是否添加成功
     */
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    public boolean add (@RequestBody(required = false) List<Region> list){

        if(oConvertUtils.isNotEmpty(list)){
            list.forEach(it->{
                this.regionService.add(it);
            });
        }
        return true;
    }


}
