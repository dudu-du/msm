package com.safety.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.Menu;
import com.safety.extentity.ExtPerson;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author safety
 * @since 2018-11-07
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取用户所属角色的菜单列表
     *
     * @param person
     * @return list
     */
    public JSONArray getMenuByUser(ExtPerson person) throws Exception;

    /**
     * 根据条件获取左侧菜单列表
     *
     * @param menu
     * @return list
     */
    public List<Menu> getMenuList(Menu menu) throws Exception;
}
