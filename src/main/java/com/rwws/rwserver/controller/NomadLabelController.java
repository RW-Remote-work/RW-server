package com.rwws.rwserver.controller;

import com.rwws.rwserver.common.util.oConvertUtils;
import com.rwws.rwserver.domain.NomadLabel;
import com.rwws.rwserver.service.NomadLabelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游民标签API
 * @Author ko
 * @Date 2023/3/10 20:26
 * @Version 1.0
 */

@RestController
@RequestMapping("/nomadLabel")
public class NomadLabelController {

    private final NomadLabelService nomadLabelService;

    public NomadLabelController(NomadLabelService nomadLabelService) {
        this.nomadLabelService = nomadLabelService;
    }

    /**
     * 获取游民标签列表
     * @author keyi
     * @Date 2023/2/17 22:41
     * @param
     * @return
     */
    @GetMapping("/list")
    public List<NomadLabel> list(){

        return this.nomadLabelService.list();
    }

    /**
     * 添加国家地区(批量？)
     * @author keyi
     * @Date 2023/2/17 22:45
     * @param list 需要添加的游民标签列表
     * @return 是否添加成功
     */
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN','ADMIN','REGULAR_USER')")
    public boolean add (@RequestBody(required = false) List<NomadLabel> list){

        if(oConvertUtils.isNotEmpty(list)){
            list.forEach(it->{
                this.nomadLabelService.add(it);
            });
        }
        return true;
    }
}
