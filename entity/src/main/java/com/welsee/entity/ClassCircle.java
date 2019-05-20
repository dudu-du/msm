package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 班级圈表
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public class ClassCircle extends Model<ClassCircle> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 发布者id
     */
    private String openId;

    /**
     * 班级id
     */
    private Integer classNumber;

    private String orgId;

    /**
     * 发布内容
     */
    private String content;

    /**
     * 班级圈图片
     */
    private String picture;

    /**
     * 老师0   家长1
     */
    private String userType;

    /**
     * 老师id
     */
    private String userId;

    /**
     * 排序
     */
    private Integer sort;

    private String del;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdatetime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }

    public LocalDateTime getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(LocalDateTime modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClassCircle{" +
                "id=" + id +
                ", openId=" + openId +
                ", classNumber=" + classNumber +
                ", orgId=" + orgId +
                ", content=" + content +
                ", picture=" + picture +
                ", userType=" + userType +
                ", userId=" + userId +
                ", sort=" + sort +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                "}";
    }
}
