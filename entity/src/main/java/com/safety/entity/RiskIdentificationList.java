package com.safety.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全风险辨识清单列表
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class RiskIdentificationList extends Model<RiskIdentificationList> {

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
     * 可能性
     */
    private String possibility;

    /**
     * 严重性
     */
    private String seriousness;

    /**
     * 措施
     */
    private String measure;

    /**
     * 事故发生的可能性(L)
     */
    private Float l;

    /**
     * 暴露于危险环境的频繁程度(E)
     */
    private BigDecimal e;

    /**
     * 发生事故产生的后果（C）
     */
    private Float c;

    private String fk;

    private Long levelFk;

    private Long typeFk;

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
    public String getPossibility() {
        return possibility;
    }

    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }
    public String getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(String seriousness) {
        this.seriousness = seriousness;
    }
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public Float getL() {
        return l;
    }

    public void setL(Float l) {
        this.l = l;
    }
    public BigDecimal getE() {
        return e;
    }

    public void setE(BigDecimal e) {
        this.e = e;
    }
    public Float getC() {
        return c;
    }

    public void setC(Float c) {
        this.c = c;
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
        return "RiskIdentificationList{" +
        "id=" + id +
        ", cause=" + cause +
        ", consequence=" + consequence +
        ", incidence=" + incidence +
        ", possibility=" + possibility +
        ", seriousness=" + seriousness +
        ", measure=" + measure +
        ", l=" + l +
        ", e=" + e +
        ", c=" + c +
        ", fk=" + fk +
        ", levelFk=" + levelFk +
        ", typeFk=" + typeFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
