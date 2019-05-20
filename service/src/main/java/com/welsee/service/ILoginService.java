package com.welsee.service;

import com.welsee.entity.Login;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welsee.exception.ProgramException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
public interface ILoginService extends IService<Login> {

    /**
     * 根据登陆名获取登陆用户信息
     * @param userName
     * @return
     */
    Login selectByLoginName(String userName) throws ProgramException;

    boolean saveOrUpdateEntityBatch(List<Login> loginList);
    /**
     * 用户启用
     * @param userId
     */
    boolean enableUserById(Serializable userId);

    /**
     * 根据登陆Id删除
     * @param id    登陆ID，多个','号分隔
     * @return
     * @throws ProgramException
     */
    boolean removeByIdStr(String id) throws ProgramException;

    /**
     * 重置密码(支持批量)
     * @param id    登陆ID，多个','号分隔
     * @return
     * @throws ProgramException
     */
    boolean resetPasswords(String id,String password) throws ProgramException;

    /**
     * 根据登陆名获取用户
     * @param loginName1    登陆方式1
     * @param loginName2    登陆方式2
     * @param loginName3    登陆方式3
     * @param loginName4    登陆方式4
     * @return
     */
    List<Login> getLoginByLoginName(String loginName1,String loginName2,String loginName3,String loginName4);

    /**
     * 获取用户ID
     * @param loginName 登陆名
     * @param password  密码
     * @return
     */
    Map getUser(String loginName, String password) throws ProgramException;
}
