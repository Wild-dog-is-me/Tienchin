package org.javaboy.tienchin.assignment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Odin
 * @since 2023-07-23
 */
@TableName("tienchin_assignment")
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分配ID
     */
    private Integer aid;

    /**
     * 1:线索 2:商机
     */
    private Integer type;

    /**
     * 线索或商机ID
     */
    private Integer assginId;

    /**
     * 线索商机所属用户ID
     */
    private Integer userId;

    /**
     * 线索所属用户名
     */
    private String userName;

    /**
     * 线索所属用户部门ID
     */
    private Integer deptId;

    /**
     * 是否是当前最新分配人
     */
    private Boolean lastest;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createBy;

    private LocalDateTime updateBy;

    /**
     * 备注
     */
    private String remark;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAssginId() {
        return assginId;
    }

    public void setAssginId(Integer assginId) {
        this.assginId = assginId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Boolean getLastest() {
        return lastest;
    }

    public void setLastest(Boolean lastest) {
        this.lastest = lastest;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(LocalDateTime updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Assignment{" +
            "aid = " + aid +
            ", type = " + type +
            ", assginId = " + assginId +
            ", userId = " + userId +
            ", userName = " + userName +
            ", deptId = " + deptId +
            ", lastest = " + lastest +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createBy = " + createBy +
            ", updateBy = " + updateBy +
            ", remark = " + remark +
        "}";
    }
}
