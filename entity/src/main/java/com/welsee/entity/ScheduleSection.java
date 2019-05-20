package com.welsee.entity;

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
 * @since 2019-01-18
 */
public class ScheduleSection extends Model<ScheduleSection> implements Comparable<ScheduleSection>{

    private static final long serialVersionUID = 1L;

    /**
     * 学校ID
     */
    private String schoolId;

    /**
     * 班级编号
     */
    private Integer classNumber;

    /**
     * 课节
     */
    private String section;

    /**
     * 课程详情(@分隔,周一至周日)
     */
    private String sectionCourse;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdatetime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifydatetime;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public String getSectionCourse() {
        return sectionCourse;
    }

    public void setSectionCourse(String sectionCourse) {
        this.sectionCourse = sectionCourse;
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
        return this.schoolId;
    }

    @Override
    public String toString() {
        return "ScheduleSection{" +
                "schoolId='" + schoolId + '\'' +
                ", classNumber=" + classNumber +
                ", section='" + section + '\'' +
                ", sectionCourse='" + sectionCourse + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }

    @Override
    public int compareTo(ScheduleSection o) {
        String section = o.getSection();
        section = section.replaceAll("\\D+","").trim();
        String thisSection = this.getSection();
        thisSection = thisSection.replaceAll("\\D+","").trim();
        if(!section.isEmpty()&&!thisSection.isEmpty()){
            try {
                int sectionInt = Integer.valueOf(section);
                int thisSectionInt = Integer.valueOf(thisSection);
                return thisSectionInt-sectionInt;
            }catch (NumberFormatException e){
            }
        }
        return 0;
    }
}
