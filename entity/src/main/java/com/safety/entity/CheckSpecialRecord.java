package com.safety.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 月排查记录填写
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckSpecialRecord extends Model<CheckSpecialRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 专项排查记录ID
     */
    private String checkSpecialId;

    /**
     * 检查内容
     */
    private String checkContent;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 排查开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkStartTime;

    /**
     * 排查结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkEndTime;

    /**
     * 排查人ID
     */
    private String checkPersonFk;

    /**
     * 排查人名称
     */
    private String checkPersonName;

    /**
     *
     */
    @TableField(exist = false)
    private List<CheckSpecialList> checkSpecialList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCheckSpecialId() {
        return checkSpecialId;
    }

    public void setCheckSpecialId(String checkSpecialId) {
        this.checkSpecialId = checkSpecialId;
    }
    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
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

    public Date getCheckStartTime() {
        return checkStartTime;
    }

    public void setCheckStartTime(Date checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public Date getCheckEndTime() {
        return checkEndTime;
    }

    public void setCheckEndTime(Date checkEndTime) {
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

    public List<CheckSpecialList> getCheckSpecialList() {
        return checkSpecialList;
    }

    public void setCheckSpecialList(List<CheckSpecialList> checkSpecialList) {
        this.checkSpecialList = checkSpecialList;
    }

    @Override
    public String toString() {
        return "CheckSpecialRecord{" +
                "id='" + id + '\'' +
                ", checkSpecialId='" + checkSpecialId + '\'' +
                ", checkContent='" + checkContent + '\'' +
                ", createPersonFk='" + createPersonFk + '\'' +
                ", orgFk='" + orgFk + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", checkStartTime=" + checkStartTime +
                ", checkEndTime=" + checkEndTime +
                ", checkPersonFk='" + checkPersonFk + '\'' +
                ", checkPersonName='" + checkPersonName + '\'' +
                '}';
    }
}
