package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rwws.rwserver.controller.request.AddNomadLabelRequest;
import com.rwws.rwserver.domain.NomadLabel;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.mapper.NomadLabelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 游民标签服务层
 *
 * @Author ko
 * @Date 2023/3/10 20:18
 * @Version 1.0
 */

@Service
public class NomadLabelService {

    private final NomadLabelMapper nomadLabelMapper;

    public NomadLabelService(NomadLabelMapper nomadLabelMapper) {
        this.nomadLabelMapper = nomadLabelMapper;
    }

    /**
     * 获取所有游民标签
     *
     * @param
     * @return
     * @author keyi
     * @Date 2023/3/10 20:20
     */
    public List<NomadLabel> list() {
        return this.nomadLabelMapper.selectList(null);
    }

    /**
     * 新增游民标签
     *
     * @param request 游民标签
     * @return
     * @author keyi
     * @Date 2023/3/10 20:22
     */
    public void add(AddNomadLabelRequest request) {
        request.getNomadLabels().forEach(this::add);
    }

    private void add(AddNomadLabelRequest.NomadLabel label) {
        var queryWrapper = Wrappers.<NomadLabel>lambdaQuery()
                .eq(NomadLabel::getLabelContent, label.getLabelContent());
        if (nomadLabelMapper.exists(queryWrapper)) {
            throw new BadRequestProblem("该游民标签已存在，请勿重复添加");
        }
        var nomadLabel = new NomadLabel();
        nomadLabel.setLabelContent(label.getLabelContent());
        nomadLabel.setRemark(label.getRemark());
        this.nomadLabelMapper.insert(nomadLabel);
    }
}
