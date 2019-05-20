package com.welsee.extentity;

import com.welsee.entity.Login;
import com.welsee.entity.Person;
import com.welsee.tools.DictConstants;
import com.welsee.tools.EncryptionUtil;

import java.time.LocalDateTime;
import java.util.List;

public class ExtPerson extends Person {
    private List<String> roleList;
    private List<String> permissionList;
    private String loginName;
    private String password;

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Login convert(boolean isUpdate){
        Login login = new Login();
        login.setId(this.loginId);
        login.setRealname(this.realname);
        login.setLoginName1(this.loginName);
        if(DictConstants.PERSON_STUDENT.equals(this.getPersonType())){
            login.setLoginName2(this.cardNo);
            login.setLoginName3(this.number);
            login.setLoginName4(this.identityNo);
        }else{
            login.setLoginName2(this.tel);
            login.setLoginName3(this.email);
        }
        login.setTel(this.tel);
        login.setEmail(this.email);
        if((this.password!=null && !"".equals(this.password))){
            login.setPassword(EncryptionUtil.encrypt(this.password));
        }
        if(!isUpdate){
            login.setCreatedatetime(LocalDateTime.now());
            login.setDel((this.del==null)
                    ?(DictConstants.PERSON_TEACHER.equals(this.personType)?0:1)
                    :this.del);//默认为0,默认启用(只有教师默认启用)
        }
        login.setModifydatetime(LocalDateTime.now());
        return login;
    }
}
