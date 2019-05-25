package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全风险辨识清单列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class RiskNoticeList extends Model<RiskNoticeList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 部位ID
     */
    private String locationFk;

    /**
     * 部位名称
     */
    private String locationName;

    /**
     * 危险有害因素ID
     */
    private String harmfulFactorsFk;

    /**
     * 危险有害因素
     */
    private String harmfulFactors;

    /**
     * 事故类型ID
     */
    private String troubleFk;

    /**
     * 事故类型名称
     */
    private String troubleName;

    /**
     * 原因
     */
    private String cause;

    /**
     * 后果
     */
    private String consequence;

    /**
     * 影响范围
     */
    private String incidence;

    /**
     * 安全风险等级ID
     */
    private String levelFk;

    /**
     * 安全风险等级名称
     */
    private String levelName;

    /**
     * 安全风险等级序号（排序使用）
     */
    private String levelNum;

    /**
     * 现有措施有效性
     */
    private String measure;

    /**
     * 应急措施
     */
    private String emergencyMeasure;

    /**
     * 责任人ID
     */
    private String personFk;

    /**
     * 责任人名称
     */
    private String personName;

    /**
     * 有效期
     */
    private String expiryDate;

    /**
     * 报告电话
     */
    private String reportPhone;

    /**
     * 关联ID
     */
    private String riskNoticeFk;

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
    /**
     * 位置标记
     * @return
     */
    private Integer index;

    /**
     * 个数标记
     */
    private Integer union;
    
    public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getUnion() {
		return union;
	}

	public void setUnion(Integer union) {
		this.union = union;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLocationFk() {
        return locationFk;
    }

    public void setLocationFk(String locationFk) {
        this.locationFk = locationFk;
    }
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
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
    public String getLevelFk() {
        return levelFk;
    }

    public void setLevelFk(String levelFk) {
        this.levelFk = levelFk;
    }
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public String getEmergencyMeasure() {
        return emergencyMeasure;
    }

    public void setEmergencyMeasure(String emergencyMeasure) {
        this.emergencyMeasure = emergencyMeasure;
    }
    public String getPersonFk() {
        return personFk;
    }

    public void setPersonFk(String personFk) {
        this.personFk = personFk;
    }
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getReportPhone() {
        return reportPhone;
    }

    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }
    public String getRiskNoticeFk() {
        return riskNoticeFk;
    }

    public void setRiskNoticeFk(String riskNoticeFk) {
        this.riskNoticeFk = riskNoticeFk;
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
        return "RiskNoticeList{" +
        "id=" + id +
        ", locationFk=" + locationFk +
        ", locationName=" + locationName +
        ", harmfulFactorsFk=" + harmfulFactorsFk +
        ", harmfulFactors=" + harmfulFactors +
        ", troubleFk=" + troubleFk +
        ", troubleName=" + troubleName +
        ", cause=" + cause +
        ", consequence=" + consequence +
        ", incidence=" + incidence +
        ", levelFk=" + levelFk +
        ", levelName=" + levelName +
        ", levelNum=" + levelNum +
        ", measure=" + measure +
        ", emergencyMeasure=" + emergencyMeasure +
        ", personFk=" + personFk +
        ", personName=" + personName +
        ", expiryDate=" + expiryDate +
        ", reportPhone=" + reportPhone +
        ", riskNoticeFk=" + riskNoticeFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
