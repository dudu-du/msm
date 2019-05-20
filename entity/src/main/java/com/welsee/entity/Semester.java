package com.welsee.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author welsee
 * @since 2018-11-19
 */
public class Semester extends Model<Semester> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 学年
     */
    private Integer schoolYear;

    /**
     * 学期编号
     */
    private String semesterNumber;

    /**
     * 字典code(用于获取第一学期还是第二学期)
     */
    private String dictCode;

    /**
     * 学期时间范围
     */
    private String semesterRange;

    /**
     * 学期全称
     */
    private String semesterName;

    /**
     * 是否删除,  0未删除/1已删除
     */
    private Integer del;

    /**
     * 是否当前学期 0不是  1是
     */
    private Integer isCurrentSemester;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdatetime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }
    public String getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(String semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
    public String getSemesterRange() {
        return semesterRange;
    }

    public void setSemesterRange(String semesterRange) {
        this.semesterRange = semesterRange;
    }
    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public Integer getIsCurrentSemester() {
        return isCurrentSemester;
    }

    public void setIsCurrentSemester(Integer isCurrentSemester) {
        this.isCurrentSemester = isCurrentSemester;
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
        return "Semester{" +
                "id=" + id +
                ", schoolYear=" + schoolYear +
                ", semesterNumber=" + semesterNumber +
                ", dictCode=" + dictCode +
                ", semesterRange=" + semesterRange +
                ", semesterName=" + semesterName +
                ", del=" + del +
                ", isCurrentSemester=" + isCurrentSemester +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                "}";
    }
}
