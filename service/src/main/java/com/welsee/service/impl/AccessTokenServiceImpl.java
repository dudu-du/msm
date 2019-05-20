package com.welsee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welsee.entity.AccessToken;
import com.welsee.exception.ProgramException;
import com.welsee.mapper.AccessTokenMapper;
import com.welsee.service.IAccessTokenService;
import com.welsee.tools.DictConstants;
import com.welsee.tools.UUIDUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
@Service
public class AccessTokenServiceImpl extends ServiceImpl<AccessTokenMapper, AccessToken> implements IAccessTokenService {

    @Override
    public int addToken(String clientId, String uid, String tokenType, String accessToken, String expiresin) throws Exception {
        if(clientId==null||"".equals(clientId) ||tokenType==null||"".equals(tokenType) ||accessToken==null||"".equals(accessToken)){
            throw new ProgramException("参数错误！");
        }
        AccessToken token = new AccessToken();
        token.setAccessToken(accessToken);
        token.setClientId(clientId);
        token.setExpiresin(expiresin);
        token.setId(UUIDUtil.getUUID());
        token.setUserId(uid);
        token.setTokenType(tokenType);
        token.setCreatedatetime(LocalDateTime.now());
        token.setModifydatetime(LocalDateTime.now());
        int i = baseMapper.insert(token);
        //删除超时token
        delTimeOutToken();
        return i;
    }

    public void delTimeOutToken(){
        Calendar rightNow = Calendar.getInstance();
        String newexpiresin = Long.toString(rightNow.getTimeInMillis());
        baseMapper.delTimeOutToken(newexpiresin);
    }

    @Override
    public boolean validateOAuth2Token(String accessToken, String tokenType) throws Exception {
        if(accessToken==null||"".equals(accessToken)){
            throw new ProgramException("参数错误！");
        }

        delTimeOutToken();

        boolean flag = false;
        AccessToken token = new AccessToken();
        if(DictConstants.TOKEN_ALL.equals(tokenType)){
            token = baseMapper.getAccessToken(accessToken);
        }else {
            token = baseMapper.getAccessTokenByToken(accessToken, tokenType);
        }
        if(token!=null){
           flag = true;
        }else{
            throw new ProgramException("token验证失败");
        }
        return flag;
    }

    @Override
    public AccessToken getToken(String accessToken, String tokenType) throws Exception {
        if(accessToken==null||"".equals(accessToken)|| tokenType==null||"".equals(tokenType)){
            throw new ProgramException("参数错误！");
        }
        AccessToken token = baseMapper.getAccessTokenByToken(accessToken,tokenType);
        if(token==null){
            throw new ProgramException("token验证失败");
        }
        return token;
    }

    @Override
    public Boolean delToken(String userId) throws Exception {
        if(userId==null||"".equals(userId)){
            throw new ProgramException("参数错误！");
        }
        Map map = new HashMap();
        map.put("user_id",userId);
        int i = baseMapper.deleteByMap(map);
        return i>0;
    }
}
