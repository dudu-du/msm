package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.AccessToken;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author safety
 * @since 2018-11-06
 */
public interface IAccessTokenService extends IService<AccessToken> {

    /**
     *
     * @param clientId      应用ID
     * @param uid           用户ID
     * @param tokenType     token类型
     * @param accessToken   token值
     * @param expiresin     超时时间
     */
    int addToken(String clientId, String uid, String tokenType, String accessToken, String expiresin) throws Exception;
    /**
     *
     * 判断系统token是否有效
     * @param accessToken   token值
     * @param tokenType token的类型
     */
    boolean validateOAuth2Token(String accessToken, String tokenType) throws Exception;

    /**
     * 获取token信息
     * @param accessToken
     * @param tokenType token的类型
     * @return
     * @throws Exception
     */
    AccessToken getToken(String accessToken, String tokenType) throws Exception;

    /**
     * 删除与用户相关token信息（退出时用）
     * @param userId
     * @return
     * @throws Exception
     */
    Boolean delToken(String userId) throws Exception;
}
