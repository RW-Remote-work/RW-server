package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddRegionRequest;
import com.rwws.rwserver.controller.response.ListRegionResponse;
import com.rwws.rwserver.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 国家地区接口
 *
 * @Author ko
 * @Date 2023/3/10 11:48
 * @Version 1.0
 */

@RestController
@RequestMapping("/regions")
public class RegionController {


    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    /**
     * 获取国家地球列表
     *
     * @param
     * @return
     * @author keyi
     * @Date 2023/2/17 22:41
     */
    @GetMapping("")
    public ListRegionResponse listRegion() {
        return regionService.list();
    }

    /**
     * 添加国家地区(批量？)
     *
     * @param request 需要添加的国家（地区）列表
     * @return 是否添加成功
     * @author keyi
     * @Date 2023/2/17 22:45
     */
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    public void addRegion(@Validated @RequestBody AddRegionRequest request) {
        regionService.add(request);
    }
}
