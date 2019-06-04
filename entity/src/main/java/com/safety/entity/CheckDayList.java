package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 日治理记录列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckDayList extends Model<CheckDayList> {

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
     * 日检查分类外键
     */
    private String checkDayCategoryFk;

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
     * 日治理外键
     */
    private String checkDayFk;

    /**
     * 班组ID
     */
    private String checkTeamFk;

    /**
     * 班组名称
     */
    private String checkTeamName;

    /**
     * 位置标记
     * @return
     */
    private Integer index;

    /**
     * 个数标记
     */
    private Integer union;

    /**
     * 填写结果
     */
    private String result;

    /**
     * 未合格项
     */
    private CheckOffgradeList checkOffgradeList;

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
    public String getCheckDayCategoryFk() {
        return checkDayCategoryFk;
    }

    public void setCheckDayCategoryFk(String checkDayCategoryFk) {
        this.checkDayCategoryFk = checkDayCategoryFk;
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

    public String getCheckDayFk() {
        return checkDayFk;
    }

    public void setCheckDayFk(String checkDayFk) {
        this.checkDayFk = checkDayFk;
    }

    public String getCheckTeamFk() {
        return checkTeamFk;
    }

    public void setCheckTeamFk(String checkTeamFk) {
        this.checkTeamFk = checkTeamFk;
    }

    public String getCheckTeamName() {
        return checkTeamName;
    }

    public void setCheckTeamName(String checkTeamName) {
        this.checkTeamName = checkTeamName;
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

    @Override
    public String toString() {
        return "CheckDayList{" +
                "id='" + id + '\'' +
                ", checkTypeFk='" + checkTypeFk + '\'' +
                ", checkTypeName='" + checkTypeName + '\'' +
                ", checkContent='" + checkContent + '\'' +
                ", checkMethod='" + checkMethod + '\'' +
                ", checkDayCategoryFk='" + checkDayCategoryFk + '\'' +
                ", riskIdentificationListId='" + riskIdentificationListId + '\'' +
                ", cause='" + cause + '\'' +
                ", levelName='" + levelName + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", orgFk='" + orgFk + '\'' +
                ", checkDayFk='" + checkDayFk + '\'' +
                ", checkTeamFk='" + checkTeamFk + '\'' +
                ", checkTeamName='" + checkTeamName + '\'' +
                ", index=" + index +
                ", union=" + union +
                ", result='" + result + '\'' +
                '}';
    }
}
