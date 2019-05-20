package com.safety.service;

import com.safety.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.tools.PageData;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author safety
 * @since 2018-11-07
 */
public interface IDictService extends IService<Dict> {
    public PageData getDictListPageByParentCode(String parentCode,String name, Integer page, Integer pageSize) throws Exception;

    public List<Dict> getDictListByParentCode(String parentCode) throws Exception;

    public boolean updateDictById(String id, String code, String name) throws Exception;

    public Dict getDict(Dict dict) throws Exception;

    public List<Dict> getDicts(String code)throws Exception;

    public boolean delDictBatchByIds(String ids, String codes) throws Exception;

    public List<Dict> getPermissionListByRole(String roleId) throws Exception;

    public List<Dict> getAllPermissionLisByRole(String roleId) throws Exception;

    public boolean deletePermissionByRole(String roleId) throws Exception;

    public boolean addPermissionByRole(String roleId, String permission) throws Exception;

    /**
     * 获取某个学校下面的所有学段
     *
     * @param orgId 机构id(学校id)
     * @return list
     */
    List<Dict> getSectionDictByOrgId(String orgId) throws Exception;


    /**
     * 根据学段获取学段下的年级
     *
     * @param sectionCode 学段code
     * @return list
     */
    List<Dict> getGradeDictBySectionCode(String sectionCode) throws Exception;

}
