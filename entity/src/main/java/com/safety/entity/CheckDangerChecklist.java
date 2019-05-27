package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 隐患排查清单
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckDangerChecklist extends Model<CheckDangerChecklist> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 风险部位
     */
    private String riskPosition;

    /**
     * 风险因素
     */
    private String harmfulFactors;

    /**
     * 风险管控措施
     */
    private String measure;

    /**
     * 措施失控表现
     */
    private String runawayPerformance;

    /**
     * 管控部门id
     */
    private String controlOrgFk;

    /**
     * 管控部门名称
     */
    private String controlOrgName;

    /**
     * 管控责任人id
     */
    private String controlOrgPersonFk;

    /**
     * 管控责任人姓名
     */
    private String controlOrgPersonName;

    /**
     * 排查部门id
     */
    private String investigationOrgFk;

    /**
     * 排查部门名称
     */
    private String investigationOrgName;

    /**
     * 排查责任人ID
     */
    private String investigationOrgPersonFk;

    /**
     * 排查责任人姓名
     */
    private String investigationOrgPersonName;

    /**
     * 排查频次
     */
    private Integer investigationCount;

    /**
     * 备注
     */
    private String remark;

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
     *填写否的记录ID
     */
    private String offgradeListFk;

    /**
     *不合格项来源
     */
    private String checkType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRiskPosition() {
        return riskPosition;
    }

    public void setRiskPosition(String riskPosition) {
        this.riskPosition = riskPosition;
    }
    public String getHarmfulFactors() {
        return harmfulFactors;
    }

    public void setHarmfulFactors(String harmfulFactors) {
        this.harmfulFactors = harmfulFactors;
    }
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
    public String getRunawayPerformance() {
        return runawayPerformance;
    }

    public void setRunawayPerformance(String runawayPerformance) {
        this.runawayPerformance = runawayPerformance;
    }
    public String getControlOrgFk() {
        return controlOrgFk;
    }

    public void setControlOrgFk(String controlOrgFk) {
        this.controlOrgFk = controlOrgFk;
    }
    public String getControlOrgName() {
        return controlOrgName;
    }

    public void setControlOrgName(String controlOrgName) {
        this.controlOrgName = controlOrgName;
    }
    public String getControlOrgPersonFk() {
        return controlOrgPersonFk;
    }

    public void setControlOrgPersonFk(String controlOrgPersonFk) {
        this.controlOrgPersonFk = controlOrgPersonFk;
    }
    public String getControlOrgPersonName() {
        return controlOrgPersonName;
    }

    public void setControlOrgPersonName(String controlOrgPersonName) {
        this.controlOrgPersonName = controlOrgPersonName;
    }
    public String getInvestigationOrgFk() {
        return investigationOrgFk;
    }

    public void setInvestigationOrgFk(String investigationOrgFk) {
        this.investigationOrgFk = investigationOrgFk;
    }
    public String getInvestigationOrgName() {
        return investigationOrgName;
    }

    public void setInvestigationOrgName(String investigationOrgName) {
        this.investigationOrgName = investigationOrgName;
    }
    public String getInvestigationOrgPersonFk() {
        return investigationOrgPersonFk;
    }

    public void setInvestigationOrgPersonFk(String investigationOrgPersonFk) {
        this.investigationOrgPersonFk = investigationOrgPersonFk;
    }
    public String getInvestigationOrgPersonName() {
        return investigationOrgPersonName;
    }

    public void setInvestigationOrgPersonName(String investigationOrgPersonName) {
        this.investigationOrgPersonName = investigationOrgPersonName;
    }
    public Integer getInvestigationCount() {
        return investigationCount;
    }

    public void setInvestigationCount(Integer investigationCount) {
        this.investigationCount = investigationCount;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getOffgradeListFk() {
        return offgradeListFk;
    }

    public void setOffgradeListFk(String offgradeListFk) {
        this.offgradeListFk = offgradeListFk;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    @Override
    public String toString() {
        return "CheckDangerChecklist{" +
                "id='" + id + '\'' +
                ", riskPosition='" + riskPosition + '\'' +
                ", harmfulFactors='" + harmfulFactors + '\'' +
                ", measure='" + measure + '\'' +
                ", runawayPerformance='" + runawayPerformance + '\'' +
                ", controlOrgFk='" + controlOrgFk + '\'' +
                ", controlOrgName='" + controlOrgName + '\'' +
                ", controlOrgPersonFk='" + controlOrgPersonFk + '\'' +
                ", controlOrgPersonName='" + controlOrgPersonName + '\'' +
                ", investigationOrgFk='" + investigationOrgFk + '\'' +
                ", investigationOrgName='" + investigationOrgName + '\'' +
                ", investigationOrgPersonFk='" + investigationOrgPersonFk + '\'' +
                ", investigationOrgPersonName='" + investigationOrgPersonName + '\'' +
                ", investigationCount=" + investigationCount +
                ", remark='" + remark + '\'' +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", orgFk='" + orgFk + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", offgradeListFk='" + offgradeListFk + '\'' +
                ", checkType='" + checkType + '\'' +
                '}';
    }
}
