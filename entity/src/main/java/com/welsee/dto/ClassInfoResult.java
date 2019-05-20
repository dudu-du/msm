package com.welsee.dto;

/**
 * 班级返回信息
 */
public class ClassInfoResult {

    /**
     * 班级id
     */
    private String classId;
    /**
     * 学校id
     */
    private String orgId;

    /**
     * 学校编码
     */
    private String orgCode;

    /**
     * 学校名称
     */
    private String orgName;

    /**
     * 入学年份
     */
    private Integer startYear;

    /**
     * 学段code
     */
    private String sectionCode;

    /**
     * 学段名称
     */
    private String sectionName;

    /**
     * 班级号
     */
    private Integer classNumber;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级code
     */
    private String gradeCode;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 班级标志
     */
    private String classToken;

    /**
     * 班主任名称
     */
    private String teacherName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getClassToken() {
        return classToken;
    }

    public void setClassToken(String classToken) {
        this.classToken = classToken;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "ClassInfoResult{" +
                "classId='" + classId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgName='" + orgName + '\'' +
                ", startYear=" + startYear +
                ", sectionCode='" + sectionCode + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", classNumber=" + classNumber +
                ", className='" + className + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", classToken='" + classToken + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
