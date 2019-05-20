package com.welsee.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welsee.entity.Menu;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtPerson;
import com.welsee.mapper.MenuMapper;
import com.welsee.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Override
//    @Cacheable("menu")
    public JSONArray getMenuByUser(ExtPerson person) throws Exception {
        if (person == null) {
            throw new ProgramException("获取用户参数不合法");
        }
        List<String> roleList = person.getRoleList();
        List<Menu> list = baseMapper.getMenuByUser(person.getId());
        log.info("获取菜单列表===============" + list);
        JSONArray menuTree = getMenuTree(0,list);

        return menuTree;
    }

    private JSONArray getMenuTree(int parentId, List<Menu> list)  throws Exception{
        JSONArray array = new JSONArray();
        Iterator<Menu> iterator = list.iterator();
        while (iterator.hasNext()) {
            Menu entity = iterator.next();
            if (entity.getParentId() == 0 && parentId == 0) {
                JSONObject object = new JSONObject();
                object.put("icon", entity.getIcon());
                object.put("menuname", entity.getTitle());
                object.put("url", entity.getUrl());
                object.put("isThirdWeb", entity.getIsThirdWeb());
                object.put("menus", getMenuTree(entity.getId(), list));
                array.add(object);
            } else if (entity.getParentId() != 0 && entity.getParentId() == parentId) {
                JSONObject object = new JSONObject();
                object.put("menuname", entity.getTitle());
                object.put("url", entity.getUrl());
                object.put("icon", entity.getIcon());
                object.put("isThirdWeb", entity.getIsThirdWeb());
                if (entity.getIsLeaf() != 1) {
                    object.put("menus", getMenuTree(entity.getId(), list));
                }
                array.add(object);
            }
        }
        return array;
    }

    @Override
    public List<Menu> getMenuList(Menu menu) throws Exception{
        if (menu == null) {
            throw new ProgramException("获取菜单参数不合法");
        }
        return baseMapper.getMenu(menu);
    }
}
