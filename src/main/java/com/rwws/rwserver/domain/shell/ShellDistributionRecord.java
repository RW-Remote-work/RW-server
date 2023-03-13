package com.rwws.rwserver.domain.shell;

import com.baomidou.mybatisplus.annotation.TableId;
import com.rwws.rwserver.domain.emums.DistributeShellScope;
import lombok.Data;

import java.time.Instant;

/**
 * 贝壳发放记录
 */
@Data
public class ShellDistributionRecord {
    @TableId
    private Long id;

    /**
     * 发放时间
     */
    private Instant time;

    /**
     * 发放数量
     */
    private Long quantity;

    /**
     * 原因
     */
    private String reason;

    private DistributeShellScope scope;

}
