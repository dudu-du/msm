package com.welsee.extentity;

/**
 * 常规的验证登陆名重复
 *
 */
public class ExtReuseStudent {
    private String loginName;       //用户名
    private String realname;        //姓名
    private String cardNoReuse;        //卡号是否已使用，0-否，1-是
    private String numberReuse;      //学号是否已使用，0-否，1-是
    private String identityNoReuse; //身份证号是否已使用，0-否，1-是

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCardNoReuse() {
        return cardNoReuse;
    }

    public void setCardNoReuse(String cardNoReuse) {
        this.cardNoReuse = cardNoReuse;
    }

    public String getNumberReuse() {
        return numberReuse;
    }

    public void setNumberReuse(String numberReuse) {
        this.numberReuse = numberReuse;
    }

    public String getIdentityNoReuse() {
        return identityNoReuse;
    }

    public void setIdentityNoReuse(String identityNoReuse) {
        this.identityNoReuse = identityNoReuse;
    }

    @Override
    public String toString() {
        return "ExtReuseStudent{" +
                "loginName='" + loginName + '\'' +
                ", realname='" + realname + '\'' +
                ", cardNoReuse='" + cardNoReuse + '\'' +
                ", numberReuse='" + numberReuse + '\'' +
                ", identityNoReuse='" + identityNoReuse + '\'' +
                '}';
    }
}
