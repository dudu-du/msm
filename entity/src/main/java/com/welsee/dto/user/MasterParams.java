package com.welsee.dto.user;

/**
 * 班主任列表请求参数
 */
public class MasterParams extends TeacherParams{

    private Integer schoolYear;  //当前学年

    private String sectionCode; //学段编码

    private String gradeCode;   //年级编码

    private String classId;  //班级ID

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
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

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }
}
