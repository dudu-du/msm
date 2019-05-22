package com.safety.entity;



/**
 * <p>
 * 局管理员
 * </p>
 *
 * @author
 * @since 2018-11-07
 */
public class OfficeAdmin extends BasePerson {

    private String orgName; //所属机构

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "OfficeAdmin{" +
                "orgName='" + orgName + '\'' +
                ", id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", personType='" + personType + '\'' +
                ", orgId='" + orgId + '\'' +
                ", tel='" + tel + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", pic='" + pic + '\'' +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                ", disable=" + disable +
                '}';
    }
}
