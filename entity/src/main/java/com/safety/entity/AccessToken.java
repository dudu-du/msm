package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author safety
 * @since 2018-11-06
 */
public class AccessToken extends Model<AccessToken> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String clientId;

    private String userId;

    private String accessToken;

    private String expiresin;

    private LocalDateTime createdatetime;

    private LocalDateTime modifydatetime;

    private String tokenType;

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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(String expiresin) {
        this.expiresin = expiresin;
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
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
        "id=" + id +
        ", clientId=" + clientId +
        ", userId=" + userId +
        ", accessToken=" + accessToken +
        ", expiresin=" + expiresin +
        ", createdatetime=" + createdatetime +
        ", modifydatetime=" + modifydatetime +
        ", tokenType=" + tokenType +
        "}";
    }
}
