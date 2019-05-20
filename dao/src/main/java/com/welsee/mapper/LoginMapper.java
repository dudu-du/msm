package com.welsee.mapper;

import com.welsee.entity.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
public interface LoginMapper extends BaseMapper<Login> {
    Login getLoginInfoByloginName(String loginName);

    /**
     * 启用用户
     * @param userId
     * @return
     */
    boolean enableUserById(@Param("id") Serializable userId, @Param("modifydatetime") LocalDateTime localDateTime);

    /**
     * 批量保存/修改
     * @param loginList
     * @return
     */
    boolean saveOrUpdateEntityBatch(List<Login> loginList);
    /**
     * 删除登陆账号
     * @param id    登陆ID，多个','号分隔
     * @return
     */
    boolean removeEntityById(@Param("id") Serializable id  , @Param("modifydatetime") LocalDateTime localDateTime);

    /**
     * 重置密码(支持批量)
     * @param id    主键，多个','号分隔
     * @return
     */
    boolean resetPasswords(@Param("id") Serializable id,@Param("password") Serializable password ,
                           @Param("modifydatetime") LocalDateTime localDateTime);

    List<Login> getLoginByLoginName(@Param("loginName1") String loginName1,
                                    @Param("loginName2") String loginName2,
                                    @Param("loginName3") String loginName3,
                                    @Param("loginName4") String loginName4);

    /**
     * 获取用户ID
     * @param loginName
     * @param password
     * @return
     */
    Map getUser(@Param("loginName")String loginName, @Param("password") String password);
}
