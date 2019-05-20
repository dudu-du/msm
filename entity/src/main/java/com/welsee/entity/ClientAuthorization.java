package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 局授权和校授权
 * </p>
 *
 * @author welsee
 * @since 2018-12-11
 */
public class ClientAuthorization extends Model<ClientAuthorization> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 第三方应用id
     */
    private String clientId;

    /**
     * 局/校id
     */
    private String orgId;

    /**
     * 是否删除,  0未删除/1已删除
     */
    private Integer del;

    /**
     * 机构类型 1学校  2教育局
     */
    private Integer orgType;

    /**
     * 局域网ip地址，校园电视台用
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 授权截止时间
     */
    private String authorizedDeadline;

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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAuthorizedDeadline() {
        return authorizedDeadline;
    }

    public void setAuthorizedDeadline(String authorizedDeadline) {
        this.authorizedDeadline = authorizedDeadline;
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
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClientAuthorization{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", del=" + del +
                ", orgType=" + orgType +
                ", sort=" + sort +
                ", authorizedDeadline='" + authorizedDeadline + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                '}';
    }
}
