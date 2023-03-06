package com.rwws.rwserver.module.support.domain;

import com.rwws.rwserver.common.core.domain.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作日志查询参数
 */
@Data
public class OperateLogQueryForm extends PageParam {


    @ApiModelProperty("开始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;


    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("请求结果 false失败 true成功")
    private Boolean successFlag;

}
