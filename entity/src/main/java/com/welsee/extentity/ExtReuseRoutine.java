package com.welsee.extentity;

/**
 * 常规的验证登陆名重复
 *
 */
public class ExtReuseRoutine {
    private String loginName;       //用户名
    private String realname;        //姓名
    private String telReuse;        //手机是否已使用，0-否，1-是
    private String emailReuse;      //邮箱是否已使用，0-否，1-是
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

    public String getTelReuse() {
        return telReuse;
    }

    public void setTelReuse(String telReuse) {
        this.telReuse = telReuse;
    }

    public String getEmailReuse() {
        return emailReuse;
    }

    public void setEmailReuse(String emailReuse) {
        this.emailReuse = emailReuse;
    }

    public String getIdentityNoReuse() {
        return identityNoReuse;
    }

    public void setIdentityNoReuse(String identityNoReuse) {
        this.identityNoReuse = identityNoReuse;
    }

    @Override
    public String toString() {
        return "ExtReuseRoutine{" +
                "loginName='" + loginName + '\'' +
                ", realname='" + realname + '\'' +
                ", telReuse='" + telReuse + '\'' +
                ", emailReuse='" + emailReuse + '\'' +
                ", identityNoReuse='" + identityNoReuse + '\'' +
                '}';
    }
}
