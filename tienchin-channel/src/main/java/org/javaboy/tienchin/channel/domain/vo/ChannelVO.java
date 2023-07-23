package org.javaboy.tienchin.channel.domain.vo;

import lombok.Data;
import org.javaboy.tienchin.common.core.domain.BaseEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author: Odin
 * @Date: 2023/7/22 19:31
 * @Description:
 */

@Data
public class ChannelVO extends BaseEntity {
    private Integer channelId;

    /**
     * 渠道名称
     */
    @NotBlank(message = "{channel.name.notnull}")
    private String channelName;

    /**
     * 渠道状态
     */
    @Max(value = 1, message = "{channel.status.invalid}")
    @Min(value = 0, message = "{channel.status.invalid}")
    @NotNull(message = "{channel.status.notnull}")
    private Byte status;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 渠道类型：1 线上渠道 2 线下渠道
     */

    @Max(value = 2, message = "{channel.type.invalid}")
    @Min(value = 1, message = "{channel.type.invalid}")
    @NotNull(message = "{channel.type.notnull}")
    private Integer type;


    private Integer delFlag;
}
