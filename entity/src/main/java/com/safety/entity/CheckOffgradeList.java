package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 日治理记录
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckOffgradeList extends Model<CheckOffgradeList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 不合格项内容
     */
    private String content;

    /**
     * 风险等级ID
     */
    private String levelFk;

    /**
     * 风险等级名称
     */
    private String levelName;

    /**
     * 安全风险等级序号（排序使用）
     */
    private String levelNum;

    /**
     * 不合格项来源
     */
    private String checkType;

    /**
     * 不合格项的关联填写单ID
     */
    private String checkFk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 机构ID
     */
    private String orgFk;


    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 不合格项的关联模板项ID
     */
    private String checkListFk;

    /**
     * 是否处理 1处理 0未处理
     */
    private String state;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckFk() {
        return checkFk;
    }

    public void setCheckFk(String checkFk) {
        this.checkFk = checkFk;
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

    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }

    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }

    public String getCheckListFk() {
        return checkListFk;
    }

    public void setCheckListFk(String checkListFk) {
        this.checkListFk = checkListFk;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CheckOffgradeList{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", levelFk='" + levelFk + '\'' +
                ", levelName='" + levelName + '\'' +
                ", levelNum='" + levelNum + '\'' +
                ", checkType='" + checkType + '\'' +
                ", checkFk='" + checkFk + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", orgFk='" + orgFk + '\'' +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", checkListFk='" + checkListFk + '\'' +
                '}';
    }
}
