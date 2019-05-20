package com.welsee.entity;



/**
 * <p>
 * 班主任
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public class Master extends BasePerson {

    private String orgName; //所属学校名字

    private String classId;//班级Id

    private String sectionCode;//学段编码

    private String sectionName;//学段

    private String className;//班级

    private String gradeCode;

    private String gradeName;//年级

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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

    @Override
    public String toString() {
        return "Master{" +
                "orgName='" + orgName + '\'' +
                ", classId='" + classId + '\'' +
                ", id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", personType='" + personType + '\'' +
                ", orgId='" + orgId + '\'' +
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
