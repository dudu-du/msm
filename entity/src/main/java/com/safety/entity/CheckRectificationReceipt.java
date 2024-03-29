package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 隐患整改回执单
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckRectificationReceipt extends Model<CheckRectificationReceipt> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 受检单位ID
     */
    private String checkOrgFk;

    /**
     * 受检单位名称
     */
    private String checkOrgName;

    /**
     * 填写时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date fillTime;

    /**
     * 检查人员ID
     */
    private String checkPersonFk;

    /**
     * 检查人员姓名
     */
    private String checkPersonName;

    /**
     * 检查日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date checkTime;

    /**
     * 编号
     */
    private String checkCode;

    /**
     * 隐患整改部门ID
     */
    private String rectificationOrgFk;

    /**
     * 隐患整改部门名称
     */
    private String rectificationOrgName;

    /**
     * 整改部门负责人ID
     */
    private String rectificationPersonFk;

    /**
     * 整改部门负责人姓名
     */
    private String rectificationPersonName;

    /**
     * 隐患内容及整改要求
     */
    private String rectificationContent;

    /**
     * 整改期限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date rectificationTime;

    /**
     * 整改措施
     */
    private String rectificationMeasure;

    /**
     * 整改结果
     */
    private String rectificationResult;

    /**
     * 所属机构ID
     */
    private String orgFk;

    /**
     * 所属机构名称
     */
    private String orgName;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 隐患内容及整改要求图片
     */
    private String rectificationContentUrl;

    /**
     * 验收意见图片
     */
    private String rectificationResultUrl ;

    /**
     *填写否的记录ID
     */
    private String recordListFk;

    /**
     *不合格项来源
     */
    private String checkType;

    public String getRectificationContentUrl() {
        return rectificationContentUrl;
    }

    public void setRectificationContentUrl(String rectificationContentUrl) {
        this.rectificationContentUrl = rectificationContentUrl;
    }

    public String getRectificationResultUrl() {
        return rectificationResultUrl;
    }

    public void setRectificationResultUrl(String rectificationResultUrl) {
        this.rectificationResultUrl = rectificationResultUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCheckOrgFk() {
        return checkOrgFk;
    }

    public void setCheckOrgFk(String checkOrgFk) {
        this.checkOrgFk = checkOrgFk;
    }
    public String getCheckOrgName() {
        return checkOrgName;
    }

    public void setCheckOrgName(String checkOrgName) {
        this.checkOrgName = checkOrgName;
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
    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
    public String getRectificationOrgFk() {
        return rectificationOrgFk;
    }

    public void setRectificationOrgFk(String rectificationOrgFk) {
        this.rectificationOrgFk = rectificationOrgFk;
    }
    public String getRectificationOrgName() {
        return rectificationOrgName;
    }

    public void setRectificationOrgName(String rectificationOrgName) {
        this.rectificationOrgName = rectificationOrgName;
    }
    public String getRectificationPersonFk() {
        return rectificationPersonFk;
    }

    public void setRectificationPersonFk(String rectificationPersonFk) {
        this.rectificationPersonFk = rectificationPersonFk;
    }
    public String getRectificationPersonName() {
        return rectificationPersonName;
    }

    public void setRectificationPersonName(String rectificationPersonName) {
        this.rectificationPersonName = rectificationPersonName;
    }
    public String getRectificationContent() {
        return rectificationContent;
    }

    public void setRectificationContent(String rectificationContent) {
        this.rectificationContent = rectificationContent;
    }
    public String getRectificationMeasure() {
        return rectificationMeasure;
    }

    public void setRectificationMeasure(String rectificationMeasure) {
        this.rectificationMeasure = rectificationMeasure;
    }
    public String getRectificationResult() {
        return rectificationResult;
    }

    public void setRectificationResult(String rectificationResult) {
        this.rectificationResult = rectificationResult;
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

    public String getRecordListFk() {
        return recordListFk;
    }

    public void setRecordListFk(String recordListFk) {
        this.recordListFk = recordListFk;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Date getFillTime() {
        return fillTime;
    }

    public void setFillTime(Date fillTime) {
        this.fillTime = fillTime;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getRectificationTime() {
        return rectificationTime;
    }

    public void setRectificationTime(Date rectificationTime) {
        this.rectificationTime = rectificationTime;
    }

    @Override
    public String toString() {
        return "CheckRectificationReceipt{" +
        "id=" + id +
        ", checkOrgFk=" + checkOrgFk +
        ", checkOrgName=" + checkOrgName +
        ", fillTime=" + fillTime +
        ", checkPersonFk=" + checkPersonFk +
        ", checkPersonName=" + checkPersonName +
        ", checkTime=" + checkTime +
        ", checkCode=" + checkCode +
        ", rectificationOrgFk=" + rectificationOrgFk +
        ", rectificationOrgName=" + rectificationOrgName +
        ", rectificationPersonFk=" + rectificationPersonFk +
        ", rectificationPersonName=" + rectificationPersonName +
        ", rectificationContent=" + rectificationContent +
        ", rectificationTime=" + rectificationTime +
        ", rectificationMeasure=" + rectificationMeasure +
        ", rectificationResult=" + rectificationResult +
        ", orgFk=" + orgFk +
        ", orgName=" + orgName +
        ", createPersonFk=" + createPersonFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
