package com.rwws.rwserver.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rwws.rwserver.domain.Region;
import com.rwws.rwserver.exception.BadRequestProblem;
import com.rwws.rwserver.mapper.HobbyMapper;
import com.rwws.rwserver.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 国家地区Service
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
     * @author keyi
     * @Date 2023/3/10 11:56
     * @param
     * @return 国家地区列表
     */
    public List<Region> list(){
        return this.regionMapper.selectList(null);
    }

    /**
     * 添加国家地区
     * @author keyi
     * @Date 2023/3/10 12:06
     * @param  region 国家地区
     * @return
     */

    @Transactional
    public void add(Region region){

        //检查国家地区中文名是否为空
        if(region.getChnName()==null||"".equals(region.getChnName())){
            throw new BadRequestProblem("地区中文名不能为空");
        }

        //检查表里是否存在相同的数据
        QueryWrapper<Region> queryWrapper = new QueryWrapper<Region>().eq("chn_name",region.getChnName());
        Region one = this.regionMapper.selectOne(queryWrapper);
        if(one!=null){
            throw new BadRequestProblem("该地区已存在，请勿重复添加");
        }
        region.setId(null);
        this.regionMapper.insert(region);
    }


}
