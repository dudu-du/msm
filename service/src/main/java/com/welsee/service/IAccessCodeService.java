package com.welsee.service;

import com.welsee.entity.AccessCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
public interface IAccessCodeService extends IService<AccessCode> {

    /**
     * 保存CODE
     * @param clientId              应用ID
     * @param uid                   用户ID
     * @param authorizationCode     oauth产生的Code
     * @param expiresin             过期的时间
     */
    int addAccessCode(String clientId, String uid,String expiresin,String authorizationCode) throws Exception;

    /**
     * 验证AUTHORIZATION_CODE
     * @param clientId      应用ID
     * @param authzCode     oauth产生的Code
     * @return
     */
    AccessCode validateAccessCode(String clientId,String authzCode) throws Exception;

    /**
     * 根据id删除code
     * @param codeId      codeID
     * @return
     */
    boolean delAccessCode(String codeId) throws Exception;
}
