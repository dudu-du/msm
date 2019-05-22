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
 * @since 2019-05-22
 */
public class RiskControlList extends Model<RiskControlList> {

    private static final long serialVersionUID = 1L;

    private byte[] id;

    /**
     * 有害因素
     */
    private String harmfulFactors;

    /**
     * 有效措施
     */
    private String measure;

    /**
     * 部门id
     */
    private String departmentFk;

    /**
     * 风险类型id
     */
    private Long typeFk;

    /**
     * 风险等级id
     */
    private Long levelFk;

    /**
     * 责任人id
     */
    private String personFk;

    private String fk;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
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
    public String getDepartmentFk() {
        return departmentFk;
    }

    public void setDepartmentFk(String departmentFk) {
        this.departmentFk = departmentFk;
    }
    public Long getTypeFk() {
        return typeFk;
    }

    public void setTypeFk(Long typeFk) {
        this.typeFk = typeFk;
    }
    public Long getLevelFk() {
        return levelFk;
    }

    public void setLevelFk(Long levelFk) {
        this.levelFk = levelFk;
    }
    public String getPersonFk() {
        return personFk;
    }

    public void setPersonFk(String personFk) {
        this.personFk = personFk;
    }
    public String getFk() {
        return fk;
    }

    public void setFk(String fk) {
        this.fk = fk;
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
        return "RiskControlList{" +
        "id=" + id +
        ", harmfulFactors=" + harmfulFactors +
        ", measure=" + measure +
        ", departmentFk=" + departmentFk +
        ", typeFk=" + typeFk +
        ", levelFk=" + levelFk +
        ", personFk=" + personFk +
        ", fk=" + fk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
