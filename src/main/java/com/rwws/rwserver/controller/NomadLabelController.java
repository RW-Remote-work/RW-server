package com.rwws.rwserver.controller;

import com.rwws.rwserver.controller.request.AddNomadLabelRequest;
import com.rwws.rwserver.domain.NomadLabel;
import com.rwws.rwserver.service.NomadLabelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游民标签API
 *
 * @Author ko
 * @Date 2023/3/10 20:26
 * @Version 1.0
 */

@RestController
@RequestMapping("/nomadLabels")
public class NomadLabelController {

    private final NomadLabelService nomadLabelService;

    public NomadLabelController(NomadLabelService nomadLabelService) {
        this.nomadLabelService = nomadLabelService;
    }

    /**
     * 获取游民标签列表
     *
     * @param
     * @return
     * @author keyi
     * @Date 2023/2/17 22:41
     */
    @GetMapping("")
    public List<NomadLabel> list() {

        return this.nomadLabelService.list();
    }

    /**
     * 添加国家地区(批量？)
     *
     * @param request 需要添加的游民标签列表
     * @return 是否添加成功
     * @author keyi
     * @Date 2023/2/17 22:45
     */
    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    public void add(@Validated @RequestBody AddNomadLabelRequest request) {
        nomadLabelService.add(request);
    }
}
