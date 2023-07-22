package org.javaboy.tienchin.channel.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Odin
 * @since 2023-07-22
 */
@TableName("tienchin_channel")
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 渠道ID
     */
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Integer channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道状态
     */
    private Byte status;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 渠道类型 - 1:线上渠道 ｜ 2:线下渠道
     */
    private Integer type;

    private String createBy;

    private String updateBy;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 删除标志 - 0:存在 ｜ 2:删除
     */
    private Integer delFlag;

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Channel{" +
            "channelId = " + channelId +
            ", channelName = " + channelName +
            ", status = " + status +
            ", remark = " + remark +
            ", type = " + type +
            ", createBy = " + createBy +
            ", updateBy = " + updateBy +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", delFlag = " + delFlag +
        "}";
    }
}
