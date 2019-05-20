package com.safety.extentity;

public class ExtPersonDepartment extends ExtPerson {

    private String departmentName;  //部门名称
    private String departmentId;    //部门ID
    private Integer isDepartment;    //是否为部门 0-否，1-是
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getIsDepartment() {
        return isDepartment;
    }

    public void setIsDepartment(Integer isDepartment) {
        this.isDepartment = isDepartment;
    }

    @Override
    public String toString() {
        return "ExtPersonDepartment{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", isDepartment='" + isDepartment + '\'' +
                ", id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", personType='" + personType + '\'' +
                ", orgId='" + orgId + '\'' +
                ", tel='" + tel + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", startYear='" + startYear + '\'' +
                ", pic='" + pic + '\'' +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
