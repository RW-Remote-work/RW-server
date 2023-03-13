package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * 职位分类实体
 *
 * @Author ko
 * @Date 2023/3/10 16:13
 * @Version 1.0
 */


@Data
public class JobClass {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 职位中文名称
     */
    private String chnName;

    /**
     * 职位英文名称
     */
    private String engName;

    /**
     * 备注
     */
    @Nullable
    private String remark;
}
