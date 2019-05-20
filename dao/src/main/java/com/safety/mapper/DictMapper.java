package com.safety.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.safety.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2018-11-07
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 根据条件获取字典列表
     *
     * @param dict
     * @return list
     */
    List<Dict> getDictList(Dict dict);

    /**
     * 按分页获取某个父字典的所有子字典
     *
     * @param map
     * @return list
     */
    List<Dict> getDictListPageByParentCode(Map<String, Object> map);

    /**
     * 获取某个父字典的所有子字典
     *
     * @param parentCode
     * @return list
     */
    List<Dict> getDictListByParentCode(@Param("parentCode") String parentCode);

    /**
     * 获取某个父字典的所有子字典数量
     *
     * @param map 父字典code
     * @return list
     */
    int getDictListCountByParentCode(Map<String, Object> map);

    /**
     * 更新指定字典内容
     *
     * @param dict
     * @return list
     */
    boolean updateDict(Dict dict);

    /**
     * 获取字典列表
     * @param dict
     * @return
     */
    List<Dict> getDict(Dict dict);

    /**
     * 获取字典列表
     * @param code
     * @return
     */
    List<Dict> getDictByCode(@Param("code") String code);

    /**
     * 获取某个学校下面的所有学段
     *
     * @param orgId 机构id(学校id)
     * @return list
     */
    List<Dict> getSectionDictByOrgId(String orgId);

    /**
     * 根据学段获取学段下的年级
     *
     * @param sectionCode 学段code
     * @return list
     */
    List<Dict> getGradeDictBySectionCode(String sectionCode);


    /**
     * 获取某个角色下的所有权限
     *
     * @param roleId
     * @return list
     */
    List<Dict> getPermissionListByRole(@Param("roleId") String roleId);

    /**
     * 获取所有权限并根据角色做所属标记
     *
     * @param roleId
     * @return list
     */
    List<Dict> getAllPermissionListByRole(@Param("roleId") String roleId);

    /**
     * 删除某角色的所有权限
     *
     * @param roleId
     * @return list
     */
    void deletePermissionByRole(@Param("roleId") String roleId);

    /**
     * 增加角色权限关系
     *
     * @param map
     * @return
     */
    void addPermissionByRole(Map<String,Object> map);
}
