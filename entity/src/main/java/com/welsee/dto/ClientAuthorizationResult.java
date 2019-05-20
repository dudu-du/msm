package com.welsee.dto;

/**
 * 校/局授权返回信息
 */
public class ClientAuthorizationResult {
    /**
     * 机构id
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 第三方应用名称
     */
    private String clientNames;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getClientNames() {
        return clientNames;
    }

    public void setClientNames(String clientNames) {
        this.clientNames = clientNames;
    }

    @Override
    public String toString() {
        return "ClientAuthorizationResult{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", clientNames='" + clientNames + '\'' +
                '}';
    }
}
