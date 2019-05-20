package com.safety.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author safety
 * @since 2018-11-29
 */
public class Person extends Model<Person> {

    protected static final long serialVersionUID = 1L;

    protected String id;

    protected String loginId;

    protected String realname;

    protected String sex;

    protected String personType;

    protected String orgId;

    protected String tel;

    /**
     * 编号
     */
    protected String number;

    protected String email;

    /**
     * 身份证号
     */
    protected String identityNo;

    /**
     * 卡号
     */
    protected String cardNo;

    /**
     * 孩子ID
     */
    protected String studentId;

    /**
     * 班级编号
     */
    protected String classNumber;

    /**
     * 入学年份
     */
    protected String startYear;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    protected LocalDate birthday;

    /**
     * 头像
     */
    protected String pic;

    /**
     * 微信openid
     */
    protected String openid;

    @TableLogic//字段上添加这个注解标识为逻辑删除
    protected Integer del;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected LocalDateTime createdatetime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    protected LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }
    public LocalDateTime getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(LocalDateTime modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", loginId='" + loginId + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", personType='" + personType + '\'' +
                ", orgId='" + orgId + '\'' +
                ", tel='" + tel + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", identityNo='" + identityNo + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", studentId='" + studentId + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", startYear='" + startYear + '\'' +
                ", birthday=" + birthday +
                ", pic='" + pic + '\'' +
                ", del=" + del +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
