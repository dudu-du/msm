package com.safety.service;

import java.awt.image.BufferedImage;

/**
 * <p>
 * 系统验证码 服务类
 * </p>
 *
 * @author safety
 * @since 2018-11-10
 */
public interface ICaptchaService {
    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     * @param uuid  uuid
     * @param code  验证码
     * @return  true：成功  false：失败
     */
    boolean validate(String uuid, String code);
}
