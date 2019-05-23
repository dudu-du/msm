package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

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
    private LocalDateTime investigationTime;

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
    private LocalDateTime complateTime;

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
    private LocalDateTime reviewTime;

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
    public LocalDateTime getInvestigationTime() {
        return investigationTime;
    }

    public void setInvestigationTime(LocalDateTime investigationTime) {
        this.investigationTime = investigationTime;
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
    public LocalDateTime getComplateTime() {
        return complateTime;
    }

    public void setComplateTime(LocalDateTime complateTime) {
        this.complateTime = complateTime;
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
    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
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

    @Override
    public String toString() {
        return "CheckDangerLedger{" +
        "id=" + id +
        ", investigationTime=" + investigationTime +
        ", investigationOrgPersonFk=" + investigationOrgPersonFk +
        ", investigationOrgPersonName=" + investigationOrgPersonName +
        ", rectificationPosition=" + rectificationPosition +
        ", rectificationName=" + rectificationName +
        ", rectificationLevel=" + rectificationLevel +
        ", governmentMeasure=" + governmentMeasure +
        ", complateTime=" + complateTime +
        ", controlOrgFk=" + controlOrgFk +
        ", controlOrgName=" + controlOrgName +
        ", controlOrgPersonFk=" + controlOrgPersonFk +
        ", controlOrgPersonName=" + controlOrgPersonName +
        ", reviewTime=" + reviewTime +
        ", reviewPersonFk=" + reviewPersonFk +
        ", reviewPersonName=" + reviewPersonName +
        ", reviewResult=" + reviewResult +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
