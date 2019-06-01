package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 隐患治理信息台账
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckDangerLedger extends Model<CheckDangerLedger> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 排查时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date investigationTime;

    /**
     * 排查人ID
     */
    private String investigationOrgPersonFk;

    /**
     * 排查人姓名
     */
    private String investigationOrgPersonName;

    /**
     * 隐患部位
     */
    private String rectificationPosition;

    /**
     * 隐患部位图片路径
     */
    private String rectificationPositionUrl;

    /**
     * 隐患名称
     */
    private String rectificationName;

    /**
     * 隐患等级
     */
    private String rectificationLevel;

    /**
     * 治理措施
     */
    private String governmentMeasure;

    /**
     * 完成时间
     */
    private String complateTime;

    /**
     * 责任部门ID
     */
    private String controlOrgFk;

    /**
     * 责任部门名称
     */
    private String controlOrgName;

    /**
     * 责任人ID
     */
    private String controlOrgPersonFk;

    /**
     * 责任人姓名
     */
    private String controlOrgPersonName;

    /**
     * 复查时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reviewTime;

    /**
     * 复查人id
     */
    private String reviewPersonFk;

    /**
     * 复查人姓名
     */
    private String reviewPersonName;

    /**
     * 复查结果
     */
    private String reviewResult;

    /**
     * 复查结果图片路径
     */
    private String reviewResultUrl;

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
    public String getInvestigationOrgPersonFk() {
        return investigationOrgPersonFk;
    }

    public Date getInvestigationTime() {
        return investigationTime;
    }

    public void setInvestigationTime(Date investigationTime) {
        this.investigationTime = investigationTime;
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
    public String getRectificationPosition() {
        return rectificationPosition;
    }

    public void setRectificationPosition(String rectificationPosition) {
        this.rectificationPosition = rectificationPosition;
    }
    public String getRectificationName() {
        return rectificationName;
    }

    public void setRectificationName(String rectificationName) {
        this.rectificationName = rectificationName;
    }
    public String getRectificationLevel() {
        return rectificationLevel;
    }

    public void setRectificationLevel(String rectificationLevel) {
        this.rectificationLevel = rectificationLevel;
    }
    public String getGovernmentMeasure() {
        return governmentMeasure;
    }

    public void setGovernmentMeasure(String governmentMeasure) {
        this.governmentMeasure = governmentMeasure;
    }
    public String getControlOrgFk() {
        return controlOrgFk;
    }

    public String getComplateTime() {
        return complateTime;
    }

    public void setComplateTime(String complateTime) {
        this.complateTime = complateTime;
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

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewPersonFk() {
        return reviewPersonFk;
    }

    public void setReviewPersonFk(String reviewPersonFk) {
        this.reviewPersonFk = reviewPersonFk;
    }
    public String getReviewPersonName() {
        return reviewPersonName;
    }

    public void setReviewPersonName(String reviewPersonName) {
        this.reviewPersonName = reviewPersonName;
    }
    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
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

    public String getRectificationPositionUrl() {
        return rectificationPositionUrl;
    }

    public void setRectificationPositionUrl(String rectificationPositionUrl) {
        this.rectificationPositionUrl = rectificationPositionUrl;
    }

    public String getReviewResultUrl() {
        return reviewResultUrl;
    }

    public void setReviewResultUrl(String reviewResultUrl) {
        this.reviewResultUrl = reviewResultUrl;
    }

    @Override
    public String toString() {
        return "CheckDangerLedger{" +
                "id='" + id + '\'' +
                ", investigationTime=" + investigationTime +
                ", investigationOrgPersonFk='" + investigationOrgPersonFk + '\'' +
                ", investigationOrgPersonName='" + investigationOrgPersonName + '\'' +
                ", rectificationPosition='" + rectificationPosition + '\'' +
                ", rectificationName='" + rectificationName + '\'' +
                ", rectificationLevel='" + rectificationLevel + '\'' +
                ", governmentMeasure='" + governmentMeasure + '\'' +
                ", complateTime=" + complateTime +
                ", controlOrgFk='" + controlOrgFk + '\'' +
                ", controlOrgName='" + controlOrgName + '\'' +
                ", controlOrgPersonFk='" + controlOrgPersonFk + '\'' +
                ", controlOrgPersonName='" + controlOrgPersonName + '\'' +
                ", reviewTime=" + reviewTime +
                ", reviewPersonFk='" + reviewPersonFk + '\'' +
                ", reviewPersonName='" + reviewPersonName + '\'' +
                ", reviewResult='" + reviewResult + '\'' +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", orgFk='" + orgFk + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", offgradeListFk='" + offgradeListFk + '\'' +
                ", checkType='" + checkType + '\'' +
                '}';
    }
}
