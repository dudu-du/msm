package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 岗位安全风险告知卡列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class SafetyNotificationCardList extends Model<SafetyNotificationCardList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 危险有害因素ID
     */
    private String harmfulFactorsFk;

    /**
     * 危险有害因素
     */
    private String harmfulFactors;

    /**
     * 事故类别ID
     */
    private String troubleFk;

    /**
     * 事故类别名称
     */
    private String troubleName;

    /**
     * 管控措施
     */
    private String controlMeasure;

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

    /**
     * 安全告知卡父ID
     */
    private String safetyNotificationCardFk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getHarmfulFactorsFk() {
        return harmfulFactorsFk;
    }

    public void setHarmfulFactorsFk(String harmfulFactorsFk) {
        this.harmfulFactorsFk = harmfulFactorsFk;
    }
    public String getHarmfulFactors() {
        return harmfulFactors;
    }

    public void setHarmfulFactors(String harmfulFactors) {
        this.harmfulFactors = harmfulFactors;
    }
    public String getTroubleFk() {
        return troubleFk;
    }

    public void setTroubleFk(String troubleFk) {
        this.troubleFk = troubleFk;
    }
    public String getTroubleName() {
        return troubleName;
    }

    public void setTroubleName(String troubleName) {
        this.troubleName = troubleName;
    }
    public String getControlMeasure() {
        return controlMeasure;
    }

    public void setControlMeasure(String controlMeasure) {
        this.controlMeasure = controlMeasure;
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

    public String getSafetyNotificationCardFk() {
        return safetyNotificationCardFk;
    }

    public void setSafetyNotificationCardFk(String safetyNotificationCardFk) {
        this.safetyNotificationCardFk = safetyNotificationCardFk;
    }

    @Override
    public String toString() {
        return "SafetyNotificationCardList{" +
        "id=" + id +
        ", harmfulFactorsFk=" + harmfulFactorsFk +
        ", harmfulFactors=" + harmfulFactors +
        ", troubleFk=" + troubleFk +
        ", troubleName=" + troubleName +
        ", controlMeasure=" + controlMeasure +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
