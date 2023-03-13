package com.rwws.rwserver.domain.shell;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 被发放贝壳的用户
 */
@Data
public class DistributedShellUser {
    @TableId
    private Long id;

    private Long userId;

    private Long shellDistributionRecordId;
}
