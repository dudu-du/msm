package com.welsee.extentity;

/**
 * 孩子的家长实体
 */
public class ExtOpenidPatriarch {
    protected String realName;              //姓名
    protected String relationName;          //亲属关系
    protected String tel;                   //电话

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
