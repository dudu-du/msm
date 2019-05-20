package com.safety.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.safety.config.Captcha;
import com.safety.config.KaptchaConfig;
import com.safety.service.ICaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.code.kaptcha.Producer;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author safety
 * @since 2018-11-10
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {
    private Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isEmpty(uuid)){
            throw new RuntimeException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        Captcha captcha = new Captcha();
        captcha.setUuid(uuid);
        captcha.setCode(code);
        //5分钟后过期

        captcha.setExpireTime(LocalDateTime.now().minusMinutes(1));
        KaptchaConfig.kaptchaMap.put(uuid,captcha);
        logger.info("获取验证码："+uuid+"【"+code+"】");
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        Captcha captcha = (Captcha) KaptchaConfig.kaptchaMap.get(uuid);
        if(captcha == null){
            logger.info("未查询到匹配的验证码【"+uuid+"】");
            return false;
        }

        //删除验证码
        KaptchaConfig.kaptchaMap.remove(uuid);
        if(captcha.getCode().equalsIgnoreCase(code) && captcha.getExpireTime().isBefore(LocalDateTime.now())){
            return true;
        }
        logger.info("验证码匹配失败："+uuid+"【"+code+"】");
        return false;
    }
}
