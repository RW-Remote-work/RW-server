package com.rwws.rwserver.module.support.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rwws.rwserver.module.support.domain.OperateLog;
import com.rwws.rwserver.module.support.domain.OperateLogQueryForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  操作日志
 *
 */
@Mapper
@Component
public interface OperateLogMapper extends BaseMapper<OperateLog> {

    /**
     * 分页查询
     * @param page
     * @param queryForm
     * @return UserOperateLogEntity
     */
    List<OperateLog> queryByPage(Page page, @Param("query") OperateLogQueryForm queryForm);

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
