package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 日治理记录大类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckDayCategory extends Model<CheckDayCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 类别名称(运矿等)
     */
    private String typeName;

    /**
     * 说明内容
     */
    private String content;

    /**
     * 检查人ID
     */
    private String personFk;

    /**
     * 检查人名称
     */
    private String personName;

    /**
     * 检查时间
     */
    private LocalDateTime checkTime;

    /**
     * 日检查ID
     */
    private String checkDayFk;

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
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckDayFk() {
        return checkDayFk;
    }

    public void setCheckDayFk(String checkDayFk) {
        this.checkDayFk = checkDayFk;
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
        return "CheckDayCategory{" +
        "id=" + id +
        ", typeName=" + typeName +
        ", content=" + content +
        ", personFk=" + personFk +
        ", personName=" + personName +
        ", checkTime=" + checkTime +
        ", checkDayFk=" + checkDayFk +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
