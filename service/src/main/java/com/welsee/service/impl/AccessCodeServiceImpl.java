package com.welsee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welsee.entity.AccessCode;
import com.welsee.mapper.AccessCodeMapper;
import com.welsee.service.IAccessCodeService;
import com.welsee.tools.UUIDUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
@Service
public class AccessCodeServiceImpl extends ServiceImpl<AccessCodeMapper, AccessCode> implements IAccessCodeService {

    @Override
    public int addAccessCode(String clientId, String uid, String expiresin,String authorizationCode) throws Exception {
        AccessCode accessCode = new AccessCode();
        accessCode.setAccessCode(authorizationCode);
        accessCode.setClientId(clientId);
        accessCode.setId(UUIDUtil.getUUID());
        accessCode.setExpiresin(expiresin);
        accessCode.setCreatedatetime(LocalDateTime.now());
        accessCode.setModifydatetime(LocalDateTime.now());
        accessCode.setUserId(uid);
        int i = baseMapper.insert(accessCode);
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(10);
        baseMapper.delTimeOutCode(localDateTime);
        return i;
    }

    @Override
    public AccessCode validateAccessCode(String clientId, String authzCode) throws Exception {
        return baseMapper.getAccessCode(clientId,authzCode);
    }

    @Override
    public boolean delAccessCode(String codeId) throws Exception {
        int i = baseMapper.deleteById(codeId);
        if(i>0){
            return true;
        }
        return false;
    }
}
