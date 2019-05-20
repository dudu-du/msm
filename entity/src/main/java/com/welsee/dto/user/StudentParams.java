package com.welsee.dto.user;

import com.welsee.tools.DictConstants;

import java.time.LocalDateTime;
import java.util.List;

public class StudentParams{
    final String userType = DictConstants.PERSON_STUDENT;
    String id;                  //用户ID
    String loginId;             //登录ID
    String realName;            //用户姓名
    String orgName;             //学校名称
    String orgId;               //学校ID
    String orgCode;             //学校编码
    List<String> orgIds;        //学校ID集合
    String sex;                 //性别
    Integer page;               //当前页
    Integer start;              //开始行数
    Integer pageSize;           //每页显示个数
    String classNumber;         //班级编号
    Integer schoolYear;         //当前学年
    LocalDateTime modifyTateTime;//修改时间
    boolean isPublic;             //是否对外接口（对外接口需要返回所有，包含已删除的）
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<String> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<String> orgIds) {
        this.orgIds = orgIds;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserType() {
        return userType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public LocalDateTime getModifyTateTime() {
        return modifyTateTime;
    }

    public void setModifyTateTime(LocalDateTime modifyTateTime) {
        this.modifyTateTime = modifyTateTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
