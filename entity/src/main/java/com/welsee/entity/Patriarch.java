package com.welsee.entity;


/**
 * <p>
 * 家长
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public class Patriarch extends BasePerson {

    /**
     * 孩子ID 多个用，分割
     */
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Patriarch{" +
                "studentId='" + studentId + '\'' +
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
