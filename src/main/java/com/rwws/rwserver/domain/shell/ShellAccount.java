package com.rwws.rwserver.domain.shell;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ShellAccount {
    @TableId
    private Long id;

    private Long userId;

    /**
     * 贝壳总数
     */
    private Long total;

    /**
     * 赚取的总数
     */
    private Long earnedAmount;

    /**
     * 购买的总数
     */
    private Long purchasedAmount;
}
