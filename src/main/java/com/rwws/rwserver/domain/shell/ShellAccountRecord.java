package com.rwws.rwserver.domain.shell;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.emums.ShellBalanceChangeType;
import com.rwws.rwserver.domain.emums.ShellSource;
import lombok.Data;

/**
 * 贝壳账户变动记录
 */
@Data
public class ShellAccountRecord {
    @TableId
    private Long id;

    /**
     * 关联的账户ID
     */
    private Long shellAccountId;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 变动类型 加或减
     */
    private ShellBalanceChangeType type;

    /**
     * 贝壳来源 赚取 购买 平台发放
     */
    private ShellSource source;

}
