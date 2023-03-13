package com.rwws.rwserver.domain;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * 国家地区实体
 *
 * @Author keyi
 * @Date 2023/3/8 21:38
 * @Version 1.0
 */
@Data
public class Region {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 中文名称
     */
    private String chnName;

    /**
     * 英文名称或拼音
     */
    private String engName;

    /**
     * 备注
     */
    @Nullable
    private String remark;
}
