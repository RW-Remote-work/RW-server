package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * 薪资币种实体
 *
 * @Author ko
 * @Date 2023/3/10 16:29
 * @Version 1.0
 */

@Data
public class MoneyType {

    @TableId
    /** 主键 */
    private Long id;

    /**
     * 中文名称
     */
    private String chnName;

    /**
     * 币种编码 例如CNY USD EUR BTC 限制为大写 非空
     */
    private String code;

    /**
     * 备注
     */
    @Nullable
    private String remark;
}
