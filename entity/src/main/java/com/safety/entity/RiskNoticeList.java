package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全风险公告栏列表
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class RiskNoticeList extends Model<RiskNoticeList> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 原因
     */
    private String cause;

    /**
     * 后果，结果
     */
    private String consequence;

    /**
     * 影响范围
     */
    private String incidence;

    /**
     * 措施
     */
    private String measure;

    /**
     * 应急措施
     */
    private String emergency;

    private String fk;

    private Long levelFk;

    private Long typeFk;

    /**
     * 责任人
     */
    private String personFk;

    private String phone;

    /**
     * 有效期
     */
    private String expiry;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }
    public String getIncidence() {
        return incidence;
    }

    public void setIncidence(String incidence) {
        this.incidence = incidence;
    }
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }
    public String getFk() {
        return fk;
    }

    public void setFk(String fk) {
        this.fk = fk;
    }
    public Long getLevelFk() {
        return levelFk;
    }

    public void setLevelFk(Long levelFk) {
        this.levelFk = levelFk;
    }
    public Long getTypeFk() {
        return typeFk;
    }

    public void setTypeFk(Long typeFk) {
        this.typeFk = typeFk;
    }
    public String getPersonFk() {
        return personFk;
    }

    public void setPersonFk(String personFk) {
        this.personFk = personFk;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
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
        return "RiskNoticeList{" +
        "id=" + id +
        ", cause=" + cause +
        ", consequence=" + consequence +
        ", incidence=" + incidence +
        ", measure=" + measure +
        ", emergency=" + emergency +
        ", fk=" + fk +
        ", levelFk=" + levelFk +
        ", typeFk=" + typeFk +
        ", personFk=" + personFk +
        ", phone=" + phone +
        ", expiry=" + expiry +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
