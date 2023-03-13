package com.rwws.rwserver.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 游民标签实体
 *
 * @Author ko
 * @Date 2023/3/10 17:25
 * @Version 1.0
 */

@Data
public class NomadLabel {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 游民标签内容
     */
    private String labelContent;

    /**
     * 备注
     */
    private String remark;
}
