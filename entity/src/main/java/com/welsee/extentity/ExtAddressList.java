package com.welsee.extentity;

import lombok.Data;

@Data
public class ExtAddressList {

    /**
     * 家长id
     */
    private String parentId;

    /**
     * 家长电话
     */
    private String parentTel;

    /**
     * 家长名字
     */
    private String parentName;

    /**
     * 学生学校id
     */
    private String orgId;

    /**
     * 班级号
     */
    private Integer classNumber;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 学生id
     */
    private String childId;

    /**
     * 家长类型
     */
    private String familyRelation;

    /**
     * 学生姓名
     */
    private String childName;

}
