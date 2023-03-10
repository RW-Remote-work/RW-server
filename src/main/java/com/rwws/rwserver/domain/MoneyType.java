package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 薪资币种实体
 *
 * @Author ko
 * @Date 2023/3/10 16:29
 * @Version 1.0
 */

@Data
public class MoneyType implements Serializable {

    @TableId
    /** 主键 */
    private Long id;

    /**
     * 职位中文名称
     */
    private String typeChn;

    /**
     * 币种编码 例如CNY USD EUR BTC 限制为大写 非空
     */
    private String typeCode;

    /**
     * 备注
     */
    private String remark;
}
