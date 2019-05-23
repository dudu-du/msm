package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 综合检查(节假日、复产前)列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckComprehensiveHolidayList extends Model<CheckComprehensiveHolidayList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 检查内容
     */
    private String content;

    /**
     * 安全风险识别列表ID
     */
    private String riskIdentificationListId;

    /**
     * 安全风险识别原因
     */
    private String cause;

    /**
     * 安全风险识别风险等级名称
     */
    private String levelName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 所属机构ID
     */
    private String orgFk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getRiskIdentificationListId() {
        return riskIdentificationListId;
    }

    public void setRiskIdentificationListId(String riskIdentificationListId) {
        this.riskIdentificationListId = riskIdentificationListId;
    }
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CheckComprehensiveHolidayList{" +
        "id=" + id +
        ", content=" + content +
        ", riskIdentificationListId=" + riskIdentificationListId +
        ", cause=" + cause +
        ", levelName=" + levelName +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
