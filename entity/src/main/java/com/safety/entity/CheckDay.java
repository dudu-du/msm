package com.safety.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 日治理记录
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckDay extends Model<CheckDay> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 排查开始时间
     */
    private LocalDateTime checkStartTime;

    /**
     * 排查结束时间
     */
    private LocalDateTime checkEndTime;

    /**
     * 排查人ID
     */
    private String checkPersonFk;

    /**
     * 排查人名称
     */
    private String checkPersonName;

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
     * 机构名称
     */
    private String orgName;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 子表列表
     */
    @TableField(exist = false)
    private List<CheckDayList> checkDayList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(LocalDateTime checkStartTime) {
        this.checkStartTime = checkStartTime;
    }
    public LocalDateTime getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(LocalDateTime checkEndTime) {
        this.checkEndTime = checkEndTime;
    }
    public String getCheckPersonFk() {
        return checkPersonFk;
    }

    public void setCheckPersonFk(String checkPersonFk) {
        this.checkPersonFk = checkPersonFk;
    }
    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
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
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public List<CheckDayList> getCheckDayList() {
        return checkDayList;
    }

    public void setCheckDayList(List<CheckDayList> checkDayList) {
        this.checkDayList = checkDayList;
    }

    @Override
    public String toString() {
        return "CheckDay{" +
        "id=" + id +
        ", checkStartTime=" + checkStartTime +
        ", checkEndTime=" + checkEndTime +
        ", checkPersonFk=" + checkPersonFk +
        ", checkPersonName=" + checkPersonName +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", orgFk=" + orgFk +
        ", orgName=" + orgName +
        ", createPersonFk=" + createPersonFk +
        "}";
    }
}
