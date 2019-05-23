package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 岗位安全风险告知卡
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class SafetyNotificationCard extends Model<SafetyNotificationCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 工作内容
     */
    private String jobName;

    /**
     * 工作场所
     */
    private String jobPosition;

    /**
     * 应急措施
     */
    private String emergencyMeasure;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 所属机构ID
     */
    private String orgFk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
    public String getEmergencyMeasure() {
        return emergencyMeasure;
    }

    public void setEmergencyMeasure(String emergencyMeasure) {
        this.emergencyMeasure = emergencyMeasure;
    }
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }
    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SafetyNotificationCard{" +
        "id=" + id +
        ", jobName=" + jobName +
        ", jobPosition=" + jobPosition +
        ", emergencyMeasure=" + emergencyMeasure +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
