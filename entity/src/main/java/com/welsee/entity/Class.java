package com.welsee.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author welsee
 * @since 2018-11-21
 */
public class Class extends Model<Class> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 机构id(用于获取学校名称)
     */
    private String orgId;

    /**
     * 入学年份
     */
    private Integer startYear;

    /**
     * 字典code(用于获取学段)
     */
    private String dictCode;

    /**
     * 班级昵称
     */
    private String classNickname;

    /**
     * 班级号(1201801)最前面的1代表小学,2代表初中,3代表高中
     */
    private Integer classNumber;

    /**
     * 班主任
     */
    private String teacher;

    /**
     * 是否删除,  0未删除/1已删除
     */
    private Integer del;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 唯一标识，用来表示班级唯一编码
     */
    private String classToken;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdatetime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifydatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getClassNickname() {
        return classNickname;
    }

    public void setClassNickname(String classNickname) {
        this.classNickname = classNickname;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassToken() {
        return classToken;
    }

    public void setClassToken(String classToken) {
        this.classToken = classToken;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", startYear=" + startYear +
                ", dictCode='" + dictCode + '\'' +
                ", classNickname='" + classNickname + '\'' +
                ", classNumber=" + classNumber +
                ", teacher='" + teacher + '\'' +
                ", del=" + del +
                ", sort=" + sort +
                ", classToken='" + classToken + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
