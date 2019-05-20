package com.welsee.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.welsee.entity.Dict;
import com.welsee.exception.ProgramException;
import com.welsee.mapper.DictMapper;
import com.welsee.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welsee.tools.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 分页查询指定父字典的所有子字典
     *
     * @param parentCode  父字典code
     * @param page        第几页
     * @param pageSize    一页显示几个
     * @return PageDate
     */
    @Override
//    @Cacheable("cache2")
    public PageData getDictListPageByParentCode(String parentCode,String name, Integer page, Integer pageSize) throws Exception {
        if (parentCode == null || page == null || pageSize == null) {
            throw new ProgramException("获取字典参数不合法");
        }
        //获取指定子字典总数量
        Map<String,Object> condition = new HashMap<>();
        condition.put("parentCode",parentCode);
        condition.put("name",name);
        int count = baseMapper.getDictListCountByParentCode(condition);

        //获取指定子字典列表
        int start = PageData.calcFirstItemIndexOfPage(page, pageSize, count);
        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pageSize", pageSize);
        map.put("parentCode", parentCode);
        map.put("name",name);
        List<Dict> list = baseMapper.getDictListPageByParentCode(map);

        PageData pageData = new PageData(page, count, pageSize, list);
        return pageData;
    }

//    @Override
//    public List<Dict> getDictListByParentCode(String parentCode) throws Exception {
//        if (parentCode == null) {
//            throw new ProgramException("查询字典参数不合法");
//        }
//        Dict dict = new Dict();
//        dict.setParentId(parentCode);
//        List<Dict> list = baseMapper.getDictList(dict);
//        return list;
//    }

    /**
     * 查询指定父字典的所有子字典
     *
     * @param parentCode  父字典code
     * @return
     */
    @Override
    public List<Dict> getDictListByParentCode(String parentCode) throws Exception {
        if (parentCode == null) {
            throw new ProgramException("获取字典参数不合法");
        }
        return baseMapper.getDictListByParentCode(parentCode);
    }

    /**
     * 更新字典信息
     * @param id
     * @param code
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public boolean updateDictById(String id,String code,String name) throws Exception {
        if (id == null || code == null || name == null) {
            throw new ProgramException("更新字典参数不合法");
        }
        boolean isSuccess = false;
        Dict dict = new Dict();
        dict.setId(id);
        dict.setName(name);
        dict.setCode(code);
        isSuccess = baseMapper.updateDict(dict);
        return isSuccess;
    }

    /**
     * 查询字典实体列表
     *
     * @param dict
     * @return Dict
     */
    @Override
    public Dict getDict(Dict dict) throws Exception {
        if (dict.getId() == null && dict.getCode() == null && dict.getName() == null ) {
            throw new ProgramException("获取字典参数不合法");
        }
        //获取指定子字典总数量
        List<Dict> dictList = baseMapper.getDict(dict);

        if(dictList == null || dictList.isEmpty()){
            return null;
        }
        return dictList.get(0);
    }

    /**
     * 查询字典实体
     * @param code 多个逗号分割
     * @return
     * @throws Exception
     */
    public List<Dict> getDicts(String code)throws Exception{
        if (code == null) {
            throw new ProgramException("查询字典参数不合法");
        }
        return baseMapper.getDictByCode(code);
    }
    /**
     * 批量删除字典
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public boolean delDictBatchByIds(String ids,String codes) throws Exception {
        if (ids == null) {
            throw new ProgramException("删除班级参数不合法");
        }
        String[] splitCode = codes.split(",");
        for(String c :splitCode){
            Map<String,Object> condition = new HashMap<>();
            condition.put("parentCode",c);
            if(baseMapper.getDictListCountByParentCode(condition) > 0){
                throw new ProgramException("存住子项");
            }
        }
        List<String> list = new ArrayList<>();
        String[] split = ids.split(",");
        for (String id : split) {
            if(!baseMapper.getPermissionListByRole(id).isEmpty()){
                throw new ProgramException("角色绑定了权限");
            }
            list.add(id);
        }
        int del = baseMapper.deleteBatchIds(list);
        return del > 0;
    }

    /**
     * 查询某角色下的所有权限
     *
     * @param roleId  角色id
     * @return
     */
    @Override
    public List<Dict> getPermissionListByRole(String roleId) throws Exception {
        if (roleId == null) {
            throw new ProgramException("获取角色参数不合法");
        }
        return baseMapper.getPermissionListByRole(roleId);
    }

    /**
     * 查询某角色下的所有权限
     *
     * @param roleId  角色id
     * @return
     */
    @Override
    public List<Dict> getAllPermissionLisByRole(String roleId) throws Exception {
        if (roleId == null) {
            throw new ProgramException("获取角色参数不合法");
        }
        return baseMapper.getAllPermissionListByRole(roleId);
    }

    /**
     * 删除角色所属权限
     *
     * @param roleId  角色id
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public boolean deletePermissionByRole(String roleId) throws Exception{
        if (roleId == null || "".equals(roleId)) {
            throw new ProgramException("参数不合法");
        }
        baseMapper.deletePermissionByRole(roleId);
        return true;
    }

    /**
     * 插入角色权限对应关系
     *
     * @param roleId  角色id
     * @param permission 权限，以,分隔
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public boolean addPermissionByRole(String roleId,String permission) throws Exception{
        if (roleId == null || permission == null || "".equals(roleId)) {
            throw new ProgramException("参数不合法");
        }
        deletePermissionByRole(roleId);
        if(!StringUtils.isEmpty(permission)) {
            String[] per = permission.split(",");
            List list = java.util.Arrays.asList(per);
            Map<String, Object> conditon = new HashMap<>();
            conditon.put("role", roleId);
            conditon.put("permissions", list);
            baseMapper.addPermissionByRole(conditon);
        }
        return true;
    }

    @Override
    public List<Dict> getSectionDictByOrgId(String orgId) throws Exception {
        if (orgId == null) {
            throw new ProgramException("获取学校学段参数不合法");
        }
        List<Dict> list = baseMapper.getSectionDictByOrgId(orgId);
        log.info("=================" + list);
        return list;
    }


    @Override
    public List<Dict> getGradeDictBySectionCode(String sectionCode) throws Exception {
        if (sectionCode == null) {
            throw new ProgramException("获取年级参数不合法");
        }
        List<Dict> list = baseMapper.getGradeDictBySectionCode(sectionCode);
        log.info("获取年级列表===============" + list);

        return list;
    }
}
