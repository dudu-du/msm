package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全风险分级管控列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class RiskControlList extends Model<RiskControlList> {

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
     * 危险有害因素
     */
    private String harmfulFactors;

    /**
     * 安全风险等级id
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
     * 部门id
     */
    private String departmentFk;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 责任人id
     */
    private String personFk;

    /**
     * 责任人名称
     */
    private String personName;

    /**
     * 关联ID
     */
    private String riskControlFk;

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
    public String getHarmfulFactors() {
        return harmfulFactors;
    }

    public void setHarmfulFactors(String harmfulFactors) {
        this.harmfulFactors = harmfulFactors;
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
    public String getDepartmentFk() {
        return departmentFk;
    }

    public void setDepartmentFk(String departmentFk) {
        this.departmentFk = departmentFk;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
    public String getRiskControlFk() {
        return riskControlFk;
    }

    public void setRiskControlFk(String riskControlFk) {
        this.riskControlFk = riskControlFk;
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
        return "RiskControlList{" +
        "id=" + id +
        ", postFk=" + postFk +
        ", postName=" + postName +
        ", harmfulFactors=" + harmfulFactors +
        ", levelFk=" + levelFk +
        ", levelName=" + levelName +
        ", levelNum=" + levelNum +
        ", measure=" + measure +
        ", departmentFk=" + departmentFk +
        ", departmentName=" + departmentName +
        ", personFk=" + personFk +
        ", personName=" + personName +
        ", riskControlFk=" + riskControlFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
