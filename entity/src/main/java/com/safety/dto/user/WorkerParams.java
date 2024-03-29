package com.safety.dto.user;

import com.safety.tools.DictConstants;

import java.time.LocalDateTime;
import java.util.List;

public class WorkerParams {
    final String userType = DictConstants.PERSON_WORKER;
    String id;                  //用户ID
    String loginId;             //登录ID
    String loginName;
    String realName;            //用户姓名
    String orgName;             //机构名称
    String orgCode;             //机构CODE
    String orgId;               //机构ID
    List<String> orgIds;        //机构ID集合
    String sex;                 //性别
    String cardNo;              //工种
    LocalDateTime modifyTateTime;//修改时间
    Integer page;               //当前页
    Integer start;              //开始行数
    Integer pageSize;           //每页显示个数
    boolean isPublic = false;           //是否对外接口(需要返回所有数据，包含已删除)

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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
