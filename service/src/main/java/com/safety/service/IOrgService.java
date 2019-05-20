package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.Org;
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
public interface IOrgService extends IService<Org> {
    /**
     * 获取机构列表(分页)
     *
     * @param page     第几页
     * @param pageSize 一页显示几个
     * @param orgType  机构类型
     * @param parentId 父类机构Id
     * @return PageData
     * @throws Exception
     */
    PageData getOrgList(int page, int pageSize, String orgType, String parentId) throws Exception;

    /**
     * 获取机构列表(不分页)
     *
     * @param orgType  机构类型
     * @param parentId 父类机构Id
     * @return PageData
     * @throws Exception
     */
    List<Org> getOrgList(String orgType, String parentId) throws Exception;

    /**
     * 获取所有机构
     *
     * @throws Exception
     */
    List getAllOrgList(String parentId) throws Exception;

    /**
     * 遍历获取所有部门
     *
     * @throws Exception
     */
    List getDepartmentList(String parentId) throws Exception;

    /**
     * 获取所有部门
     *
     * @throws Exception
     */
    List getDepartmentRow(String parentId) throws Exception;

    /**
     * 获取所有机构
     *
     * @throws Exception
     */
    List getAllOrgListByType(String orgType) throws Exception;

    /**
     * 根据ID获取机构
     *
     * @param orgId
     * @return Org
     * @throws Exception
     */
    Org getOrgById(String orgId) throws Exception;

    /**
     * 根据code获取机构
     *
     * @param code
     * @return Org
     * @throws Exception
     */
    Org getOrgByCode(String code) throws Exception;

    /**
     * 根据学校名称获取学校
     * @param name
     * @return
     * @throws Exception
     */
    Org getSchoolByName(String name) throws Exception;
    /**
     * 添加机构列表
     *
     * @param code        机构编码
     * @param name        机构名称
     * @param remarksType 附属类型：属于什么类型学校 可以为""
     * @param simpleName  简称 可以为""
     * @param orgType     机构类型  1局机构2学校3部门
     * @param parentId    父类机构ID 没有父类传"0"
     * @param domainName  机构域名
     * @return boolean
     * @throws Exception
     */
    boolean addOrg(String orgType, String code, String name, String simpleName, String parentId, String remarksType, Integer sort,String domainName) throws Exception;

    /**
     * 修改机构列表
     *
     * @param orgId       机构ID
     * @param code        机构编码
     * @param name        机构名称
     * @param remarksType 附属类型：属于什么类型学校 可以为""
     * @param simpleName  简称 可以为""
     * @param orgType     机构类型  1局机构2学校3部门
     * @param parentId    父类机构ID 没有父类传"0"
     * @param domainName  机构域名
     * @return boolean
     */
    boolean updateOrg(String orgId, String orgType, String code, String name, String simpleName, String parentId,
                      String remarksType,Integer sort,String domainName) throws Exception;

    /**
     * 删除机构列表
     *
     * @param orgId 机构Id
     * @return JsonResult
     */
    boolean delOrg(String orgId) throws Exception;

    /**
     * 模糊查询获取机构列表
     *
     * @param orgType 机构类型
     * @param str     传递参数
     * @return JsonResult
     */
    List<Org> getOrgListBystr(String orgType, String str) throws Exception;
}
