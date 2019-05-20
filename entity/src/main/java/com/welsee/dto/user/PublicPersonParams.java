package com.welsee.dto.user;


import java.io.Serializable;

/**
 * 人员开放接口请求参数
 */
public class PublicPersonParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;               //token
    private String orgCode;             //机构编码
    private String userType;            //用户类型
    private String dateTime;     //开始时间
    private Integer page;               //当前页
    private Integer start;              //开始行数
    private Integer pageSize;           //每页显示个数
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    @Override
    public String toString() {
        return "PublicPersonParams{" +
                "token='" + token + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", userType='" + userType + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", page=" + page +
                ", start=" + start +
                ", pageSize=" + pageSize +
                '}';
    }
}
