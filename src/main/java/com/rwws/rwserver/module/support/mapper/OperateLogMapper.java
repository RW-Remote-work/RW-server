package com.rwws.rwserver.module.support.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rwws.rwserver.module.support.domain.OperateLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 操作日志
 */
@Mapper
@Component
public interface OperateLogMapper extends BaseMapper<OperateLogEntity> {

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    void deleteById(@Param("id") Long id);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    void deleteByIds(@Param("idList") List<Long> idList);
}
