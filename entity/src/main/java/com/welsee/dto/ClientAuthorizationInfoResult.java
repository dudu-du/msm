package com.welsee.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 校/局授权返回信息
 */
public class ClientAuthorizationInfoResult implements Serializable {

    private String id;

    private String clientId;

    private String clientSecret;

    /**
     * 应用名称
     */
    private String clientName;

    /**
     * 应用首页
     */
    private String homePage;

    /**
     * 应用图标
     */
    private String logo;

    /**
     * 描述
     */
    private String content;

    /**
     * 是否删除,  0未删除/1已删除
     */
    private Integer del;

    /**
     * 授权截至时间
     */
    private String authorizedDeadline;


    /**
     * 是否选中
     */
    private String LAY_CHECKED;

    /**
     * 创建时间
     */
    private LocalDateTime createdatetime;

    /**
     * 更新时间
     */
    private LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getAuthorizedDeadline() {
        return authorizedDeadline;
    }

    public void setAuthorizedDeadline(String authorizedDeadline) {
        this.authorizedDeadline = authorizedDeadline;
    }

    public String getLAY_CHECKED() {
        return LAY_CHECKED;
    }

    public void setLAY_CHECKED(String LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
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

    @Override
    public String toString() {
        return "ClientAuthorizationInfoResult{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", clientName='" + clientName + '\'' +
                ", homePage='" + homePage + '\'' +
                ", logo='" + logo + '\'' +
                ", content='" + content + '\'' +
                ", del=" + del +
                ", authorizedDeadline='" + authorizedDeadline + '\'' +
                ", LAY_CHECKED='" + LAY_CHECKED + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
