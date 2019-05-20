package com.welsee.extentity;

import com.welsee.entity.Class;

//  这里命名一般是clazz

public class ExtClass extends Class {
    /**
     * 根据学期推算出的年级
     */
    private String grade;

    /**
     * 推算出班级名称
     */
    private String className;

    /**
     * 学段
     */
    private String section;

    /**
     * 设备名称
     */
    private String deviceName;


    public String getGrade() {
        return grade;
    }


    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getClassName() {
        return className;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
