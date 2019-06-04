package com.safety.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 周治理记录列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckWeekList extends Model<CheckWeekList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 检查类别ID
     */
    private String checkTypeFk;

    /**
     * 检查类别名称
     */
    private String checkTypeName;

    /**
     * 检查内容
     */
    private String checkContent;

    /**
     * 检查方法
     */
    private String checkMethod;

    /**
     * 周检查外键
     */
    private String checkWeekFk;

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

    /**
     * 位置标记
     * @return
     */
    @TableField(exist = false)
    private Integer index;

    /**
     * 个数标记
     */
    @TableField(exist = false)
    private Integer union;

    /**
     * 填写结果
     */
    @TableField(exist = false)
    private String result;

    /**
     * 未合格项
     */
    @TableField(exist = false)
    private CheckOffgradeList checkOffgradeList;

    /**
     * 风险清单
     */
    @TableField(exist = false)
    private CheckDangerChecklist checkDangerChecklist;

    /**
     * 风险台账
     */
    @TableField(exist = false)
    private CheckDangerLedger checkDangerLedger;

    /**
     * 回执单
     */
    @TableField(exist = false)
    private CheckRectificationReceipt checkRectificationReceipt;

    public CheckOffgradeList getCheckOffgradeList() {
        return checkOffgradeList;
    }

    public void setCheckOffgradeList(CheckOffgradeList checkOffgradeList) {
        this.checkOffgradeList = checkOffgradeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCheckTypeFk() {
        return checkTypeFk;
    }

    public void setCheckTypeFk(String checkTypeFk) {
        this.checkTypeFk = checkTypeFk;
    }
    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }
    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }
    public String getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }
    public String getCheckWeekFk() {
        return checkWeekFk;
    }

    public void setCheckWeekFk(String checkWeekFk) {
        this.checkWeekFk = checkWeekFk;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CheckDangerChecklist getCheckDangerChecklist() {
        return checkDangerChecklist;
    }

    public void setCheckDangerChecklist(CheckDangerChecklist checkDangerChecklist) {
        this.checkDangerChecklist = checkDangerChecklist;
    }

    public CheckDangerLedger getCheckDangerLedger() {
        return checkDangerLedger;
    }

    public void setCheckDangerLedger(CheckDangerLedger checkDangerLedger) {
        this.checkDangerLedger = checkDangerLedger;
    }

    public CheckRectificationReceipt getCheckRectificationReceipt() {
        return checkRectificationReceipt;
    }

    public void setCheckRectificationReceipt(CheckRectificationReceipt checkRectificationReceipt) {
        this.checkRectificationReceipt = checkRectificationReceipt;
    }

    @Override
    public String toString() {
        return "CheckWeekList{" +
                "id='" + id + '\'' +
                ", checkTypeFk='" + checkTypeFk + '\'' +
                ", checkTypeName='" + checkTypeName + '\'' +
                ", checkContent='" + checkContent + '\'' +
                ", checkMethod='" + checkMethod + '\'' +
                ", checkWeekFk='" + checkWeekFk + '\'' +
                ", riskIdentificationListId='" + riskIdentificationListId + '\'' +
                ", cause='" + cause + '\'' +
                ", levelName='" + levelName + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", orgFk='" + orgFk + '\'' +
                ", index=" + index +
                ", union=" + union +
                ", result='" + result + '\'' +
                '}';
    }
}
