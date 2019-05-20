package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public class ClassCircleComment extends Model<ClassCircleComment> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 评论的班级圈id
     */
    private String classCircleId;

    /**
     * 评论的用户id
     */
    private String openId;

    /**
     * 老师/家长id
     */
    private String userId;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 排序
     */
    private Integer sort;

    private Integer del;

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

    public String getClassCircleId() {
        return classCircleId;
    }

    public void setClassCircleId(String classCircleId) {
        this.classCircleId = classCircleId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
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
        return "ClassCircleComment{" +
                "id=" + id +
                ", classCircleId=" + classCircleId +
                ", openId=" + openId +
                ", userId=" + userId +
                ", content=" + content +
                ", sort=" + sort +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                "}";
    }
}
