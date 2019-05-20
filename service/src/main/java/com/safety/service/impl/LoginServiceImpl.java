package com.safety.service.impl;

import com.safety.entity.Login;
import com.safety.exception.ProgramException;
import com.safety.mapper.LoginMapper;
import com.safety.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.MD5;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author safety
 * @since 2018-11-06
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Override
    public Login selectByLoginName(String userName) throws ProgramException {
        if(userName == null || "".equals(userName)){
            throw new ProgramException("参数不合法");
        }
        Login login = baseMapper.getLoginInfoByloginName(userName);
        return login;
    }

    @Override
    public boolean saveOrUpdateEntityBatch(List<Login> loginList) {
        return baseMapper.saveOrUpdateEntityBatch(loginList);
    }

    @Override
    public boolean enableUserById(Serializable userId) {
        return baseMapper.enableUserById(userId,LocalDateTime.now());
    }

    @Override
    public boolean removeByIdStr(String id){

        return baseMapper.removeEntityById(id,LocalDateTime.now());
    }

    @Override
    public boolean resetPasswords(String id ,String password) throws ProgramException {
        return baseMapper.resetPasswords(id, password,LocalDateTime.now());
    }

    @Override
    public List<Login> getLoginByLoginName(String loginName1, String loginName2, String loginName3, String loginName4) {
        return baseMapper.getLoginByLoginName(loginName1,loginName2,loginName3,loginName4);
    }

    @Override
    public Map getUser(String loginName, String password) throws ProgramException {
        if(loginName==null || "".equals(loginName) || password==null || "".equals(password)){
            throw new ProgramException("参数不合法");
        }
        password = MD5.GetMD5Code(password);
        Map user = baseMapper.getUser(loginName, password);
        if(user != null && user.size()>0){
            return user;
        }else{
            throw new ProgramException("用户不存在");
        }
    }

}
