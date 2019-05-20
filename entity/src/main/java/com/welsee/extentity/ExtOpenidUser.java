package com.welsee.extentity;

import com.welsee.entity.OpenidUser;

public class ExtOpenidUser extends OpenidUser {
    /**
     * 机构类型
     */
    protected String orgId;

    /**
     * 班级编号
     */
    protected Integer classNumber;
    /**
     * 用户类型
     */
    protected String userType;

    protected String childId;

    /**
     * 亲属关系
     */
    protected String familyRelation;
    protected String tel;       //手机号
    protected String realName;  //姓名
    protected String pic;       //头像

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }
}
