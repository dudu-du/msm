package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.dto.ClientAuthorizationInfoResult;
import com.welsee.dto.ClientAuthorizationResult;
import com.welsee.entity.ClientAuthorization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 局授权和校授权 Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-12-11
 */
public interface ClientAuthorizationMapper extends BaseMapper<ClientAuthorization> {

    /**
     * 新建授权
     *
     * @param clientAuthorizationList 授权对象集合
     * @return
     */
    boolean addClientAuthorization(List<ClientAuthorization> clientAuthorizationList);


    /**
     * 删除学校的授权信息
     *
     * @param map
     * @return
     */
    boolean delClientAuthorizationByOrgId(Map<String, Object> map);

    /**
     * 获取校/局机构的授权列表总数
     *
     * @param orgType 0校机构/1局机构
     * @return
     */
    int selectCountByOrgType(Integer orgType);

    /**
     * 获取校/局机构的授权列表信息
     *
     * @param map
     * @return
     */
    List<ClientAuthorizationResult> getClientAuthorizationByOrgType(Map<String, Object> map);

    /**
     * 获取机构下的授权应用列表信息(不分页,授权过的第三方应用)
     *
     * @param orgId 机构id
     * @return
     */
    List<ClientAuthorizationInfoResult> getClientAuthorizationByOrgId(String orgId);


    /**
     * 获取应用列表(不分页)
     *
     * @return PageData
     */
    List<ClientAuthorizationInfoResult> getClientList();

    /**
     * 获取第三方授权应用列表信息(不分页,授权和没授权的第三方应用)
     *
     * @param orgId 机构id
     * @return
     */
    List<ClientAuthorizationInfoResult> getClientListByOrgId(String orgId);


    /**
     * 获取第三应用授权过的信息
     *
     * @param clientId
     * @return
     */
    List<ClientAuthorization> selectListByClientId(String clientId);

    /**
     * 更新平台局域网访问地址
     * @param map
     * @return
     */
    Integer updateUrl(HashMap<String,String> map);
}

