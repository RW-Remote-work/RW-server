package com.rwws.rwserver.module.support.domain;

import com.rwws.rwserver.common.core.domain.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 操作日志查询参数
 */
@Data
public class OperateLogQueryForm extends PageParam {

    @Schema(name = "开始日期")
    private String startDate;

    @Schema(name = "结束日期")
    private String endDate;

    @Schema(name = "用户名称")
    private String userName;

    @Schema(name = "请求结果 false失败 true成功")
    private Boolean successFlag;

}
