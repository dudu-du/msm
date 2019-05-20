package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.Client;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-14
 */
public interface ClientMapper extends BaseMapper<Client> {
    Client getClientByClientId(String clientId);

    Client getClientByClientIdAndSecret(String clientId, String clientSecret);


    /**
     * 获取第三方列表(分页)
     *
     * @param map map
     * @return list
     */
    List<Client> getClientListByPage(Map<String, Object> map);


    /**
     * 获取应用的总数量
     *
     * @return int
     */
    int selectCount();

    /**
     * 通过应用id获取应用信息
     *
     * @param id 应用id
     * @return
     */
    Client selectClientById(@Param("id") String id);

    /**
     * 通过应用名称获取应用信息
     *
     * @param map map
     * @return
     */
    Client selectClientByName(Map<String, Object> map);

}
