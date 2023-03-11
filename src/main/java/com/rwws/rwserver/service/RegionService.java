package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.controller.request.AddRegionRequest;
import com.rwws.rwserver.controller.response.ListRegionResponse;
import com.rwws.rwserver.domain.Region;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.mapper.RegionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 国家地区Service
 *
 * @Author ko
 * @Date 2023/3/10 11:51
 * @Version 1.0
 */


@Service
public class RegionService {


    private final RegionMapper regionMapper;

    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    /**
     * 获取国家地区列表
     *
     * @param
     * @return 国家地区列表
     * @author keyi
     * @Date 2023/3/10 11:56
     */
    public ListRegionResponse list() {
        var regions = regionMapper.selectList(null).stream().map(it -> {
            var region = new ListRegionResponse.Region();
            region.setId(it.getId());
            region.setRemark(it.getRemark());
            region.setEngName(it.getEngName());
            region.setChnName(it.getChnName());
            return region;
        }).toList();
        var response = new ListRegionResponse();
        response.setRegions(regions);
        return response;
    }


    /**
     * 添加国家地区
     *
     * @param request 国家地区
     * @return
     * @author keyi
     * @Date 2023/3/10 12:06
     */
    @Transactional
    public void add(AddRegionRequest request) {
        request.getRegion().forEach(this::add);

    }

    private void add(AddRegionRequest.Region requestRegion) {
        //检查表里是否存在相同的数据
        var queryWrapper = new QueryWrapper<Region>().eq("chn_name", requestRegion.getChnName());
        if (regionMapper.exists(queryWrapper)) {
            throw new BadRequestProblem("该地区已存在，请勿重复添加");
        }
        var region = new Region();
        region.setRemark(requestRegion.getRemark());
        region.setEngName(requestRegion.getEngName());
        region.setChnName(requestRegion.getChnName());
        this.regionMapper.insert(region);
    }
}
