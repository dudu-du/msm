package com.safety.config;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统验证码
 * </p>
 *
 * @author safety
 * @since 2018-11-10
 */
public class Captcha implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "Captcha{" +
        "uuid=" + uuid +
        ", code=" + code +
        ", expireTime=" + expireTime +
        "}";
    }
}
