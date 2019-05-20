package com.welsee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.welsee.entity.BasePerson;

/**
 * <p>
 *  1、用户公共服务类
 *  2、这里可以写一些用户相关公共方法接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public interface IBasePersonService<T extends BasePerson> extends IService<T> {
    /**
     * 根据登陆名获取登陆用户信息
     * @param loginId
     * @return
     */
    T selectByLoginId(String loginId);

}
