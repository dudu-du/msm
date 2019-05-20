package com.welsee.entity;


/**
 * <p>
 * 学生
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public class Student extends BasePerson {

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 入学年份
     */
    private String startYear;

    private String classNumber;

    private String orgName;//学校名称

    private String sectionCode;

    private String sectionName;//学段

    private String className;//班级编码

    private String classId;//班级ID

    private String gradeCode;

    private String gradeName;//年级

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
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

    @Override
    public String toString() {
        return "Student{" +
                "cardNo='" + cardNo + '\'' +
                ", startYear='" + startYear + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", orgName='" + orgName + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", className='" + className + '\'' +
                ", classId='" + classId + '\'' +
                ", gradeCode='" + gradeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", personType='" + personType + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgType='" + orgType + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", tel='" + tel + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", pic='" + pic + '\'' +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                ", disable=" + disable +
                '}';
    }
}
