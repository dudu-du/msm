package com.welsee.extentity;


public class ExtResUserSubjectClass {

    private String id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 班级号
     */
    private String classNumbers;

    /**
     * 学段code
     */
    private String sectionCode;


    /**
     * 学级code
     */
    private String gradeCode;

    /**
     * 学科名称
     */
    private String subjectName;

    /**
     * 学科id
     */
    private String subjectId;

    /**
     * 学段名称
     */
    private String sectionName;

    /**
     * 班级名称,多个用","分隔
     */
    private String classNames;

    /**
     * 根据学期推算出的年级
     */
    private String grade;

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(String classNames) {
        this.classNames = classNames;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClassNumbers() {
        return classNumbers;
    }

    public void setClassNumbers(String classNumbers) {
        this.classNumbers = classNumbers;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExtResUserSubjectClass{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", userId='" + userId + '\'' +
                ", classNumbers='" + classNumbers + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", classNames='" + classNames + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
