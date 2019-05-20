package com.safety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.AccessToken;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2018-11-06
 */
public interface AccessTokenMapper extends BaseMapper<AccessToken> {
    //token验证
    AccessToken getAccessTokenByToken(@Param("accessToken") String accessToken,@Param("tokenType") String tokenType);

    //token验证
    AccessToken getAccessToken(@Param("accessToken") String accessToken);

    void delTimeOutToken(@Param("expiresin") String expiresin);
}
