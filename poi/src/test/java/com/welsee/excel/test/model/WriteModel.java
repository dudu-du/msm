package com.welsee.excel.test.model;

import com.welsee.excel.annotation.ExcelProperty;
import com.welsee.excel.annotation.Select;

public class WriteModel extends BaseWriteModel {



    @ExcelProperty(value = {"手机号"},index = 2)
    private String tel;

    @ExcelProperty(value = {"邮箱"},index = 3)
    private String email;

    @ExcelProperty(value = {"身份证号"},index = 4)
    private String identityNo;

    @Select
    @ExcelProperty(value = {"性别"},index = 5)
    private String sex;

    @ExcelProperty(value = {"所属学校*"},index = 6)
    @Select//增加此注解该字段会增加下拉选项验证
    //有一个前提是：创建sheet时需要dropDownSource参数key值为此字段名称否则不会映射成功
    private String orgName;


    @Override
    public String getRealName() {
        return super.getRealName();
    }

    @Override
    public void setRealName(String realName) {
        super.setRealName(realName);
    }

    @Override
    public String getLoginName() {
        return super.getLoginName();
    }

    @Override
    public void setLoginName(String loginName) {
        super.setLoginName(loginName);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "WriteModel2{" +
                "tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", sex='" + sex + '\'' +
                ", orgName='" + orgName + '\'' +
                ", realName='" + realName + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
