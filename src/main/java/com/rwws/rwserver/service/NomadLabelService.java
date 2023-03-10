package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * @param nomadLabel 游民标签
     * @return
     * @author keyi
     * @Date 2023/3/10 20:22
     */
    public void add(NomadLabel nomadLabel) {

        //检查游民标签内容是否为空
        if (nomadLabel.getLabelContent() != null && !"".equals(nomadLabel.getLabelContent())) {
            throw new BadRequestProblem("游民标签内容不能为空");
        }

        //检查表里是否存在相同的数据
        QueryWrapper<NomadLabel> queryWrapper = new QueryWrapper<NomadLabel>().eq("label_content", nomadLabel.getLabelContent());
        NomadLabel one = this.nomadLabelMapper.selectOne(queryWrapper);
        if (one != null) {
            throw new BadRequestProblem("该游民标签已存在，请勿重复添加");
        }
        nomadLabel.setId(null);

        this.nomadLabelMapper.insert(nomadLabel);


    }
}
