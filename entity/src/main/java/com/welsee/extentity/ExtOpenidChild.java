package com.welsee.extentity;

/**
 * 我的孩子实体
 */
public class ExtOpenidChild{
    protected String openid;                //openid
    protected String userType;              //用户类型
    protected String relationName;          //亲属关系
    protected String childId;               //孩子ID
    protected String childName;             //孩子姓名
    protected String childClassName;        //孩子的班级
    protected String childSex;              //孩子性别
    protected String childBirthday;         //孩子生日
    protected String childPic;              //孩子的头像
    protected String classNumber;
    protected String orgId;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex;
    }

    public String getChildBirthday() {
        return childBirthday;
    }

    public void setChildBirthday(String childBirthday) {
        this.childBirthday = childBirthday;
    }

    public String getChildPic() {
        return childPic;
    }

    public void setChildPic(String childPic) {
        this.childPic = childPic;
    }

    public String getChildClassName() {
        return childClassName;
    }

    public void setChildClassName(String childClassName) {
        this.childClassName = childClassName;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
