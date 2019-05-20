package com.welsee.extentity;

import com.welsee.entity.Subject;

public class ExtSubject extends Subject {

    private String sectionName; //学段名称

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
