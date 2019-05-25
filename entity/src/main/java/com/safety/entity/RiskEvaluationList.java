package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全风险动态评估列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class RiskEvaluationList extends Model<RiskEvaluationList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 岗位单元ID
     */
    private String postFk;

    /**
     * 岗位单元名称
     */
    private String postName;

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
     * 可能性
     */
    private String possibility;

    /**
     * 严重性
     */
    private String seriousness;

    /**
     * 现有措施有效性
     */
    private String measure;

    /**
     * 事故发生的可能性(L)
     */
    private Float numL;

    /**
     * 暴露于危险环境的频繁程度(E)
     */
    private Float numE;

    /**
     * 发生事故产生的后果（C）
     */
    private Float numC;

    /**
     * D值
     */
    private Float numD;

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
     * 关联ID
     */
    private String riskEvaluationFk;

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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPostFk() {
        return postFk;
    }

    public void setPostFk(String postFk) {
        this.postFk = postFk;
    }
    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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
    public Float getNumL() {
        return numL;
    }

    public void setNumL(Float numL) {
        this.numL = numL;
    }
    public Float getNumE() {
        return numE;
    }

    public void setNumE(Float numE) {
        this.numE = numE;
    }
    public Float getNumC() {
        return numC;
    }

    public void setNumC(Float numC) {
        this.numC = numC;
    }
    public Float getNumD() {
        return numD;
    }

    public void setNumD(Float numD) {
        this.numD = numD;
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
    public String getRiskEvaluationFk() {
        return riskEvaluationFk;
    }

    public void setRiskEvaluationFk(String riskEvaluationFk) {
        this.riskEvaluationFk = riskEvaluationFk;
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

	@Override
    public String toString() {
        return "RiskEvaluationList{" +
        "id=" + id +
        ", postFk=" + postFk +
        ", postName=" + postName +
        ", harmfulFactorsFk=" + harmfulFactorsFk +
        ", harmfulFactors=" + harmfulFactors +
        ", troubleFk=" + troubleFk +
        ", troubleName=" + troubleName +
        ", cause=" + cause +
        ", consequence=" + consequence +
        ", incidence=" + incidence +
        ", possibility=" + possibility +
        ", seriousness=" + seriousness +
        ", measure=" + measure +
        ", numL=" + numL +
        ", numE=" + numE +
        ", numC=" + numC +
        ", numD=" + numD +
        ", levelFk=" + levelFk +
        ", levelName=" + levelName +
        ", levelNum=" + levelNum +
        ", riskEvaluationFk=" + riskEvaluationFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
