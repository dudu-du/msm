package com.welsee.extentity;

import com.welsee.entity.UserSubjectClass;

public class ExtUserSubjectClass extends UserSubjectClass {
    private String userName;    //用户名

    private String subjectName; //科目名称

    private String teacherTel; //老师电话

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

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

}
