package com.welsee.controller;

import com.welsee.entity.Dict;
import com.welsee.exception.ProgramException;
import com.welsee.service.IDictService;
import com.welsee.shiro.AuthRealm;
import com.welsee.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 字典管理控制器
 *
 * @author welsee
 * @since 2018-11-12
 */
@Controller
@Slf4j
@RequestMapping(value = "View")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    /**
     * 角色列表展示页面
     *
     * @return view
     */
    @RequestMapping(value = "listRole", method = RequestMethod.GET)
    @RequiresRoles("ROLE_SUPERADMIN")
    public BaseModelAndView showRoleList() {
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.setViewName("dict/role");
        return bmv;
    }

    /**
     * 权限列表展示页面
     *
     * @return view
     */
    @RequestMapping(value = "listPermission", method = RequestMethod.GET)
    @RequiresRoles("ROLE_SUPERADMIN")
    public BaseModelAndView showPermissionList() {
        BaseModelAndView bmv = new BaseModelAndView();
        bmv.setViewName("dict/permission");
        return bmv;
    }

    /**
     * 获取指定父字典下的分页子字典
     *
     * @return JsonResult
     */
    @RequestMapping(value = "getDictListPage", method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult getDictListPage(@RequestParam int page,
                                      @RequestParam int pageSize,
                                      @RequestParam String parentCode,
                                      @RequestParam String name) {
        try {
            PageData dictList = dictService.getDictListPageByParentCode(parentCode,name, page, pageSize);
            return renderSuccess("获取字典列表成功", dictList);
        } catch (ProgramException p) {
            log.error("获取字典列表失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取字典列表失败." + e.getMessage());
            return renderError("获取字典列表失败");
        }
    }

    /**
     * 获取指定父字典下的所有子字典
     *
     * @return JsonResult
     */
    @RequestMapping(value = "getDictList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult getDictList(@RequestParam String parentCode) {
        try {
            List<Dict> dictList = dictService.getDictListByParentCode(parentCode);
            return renderSuccess("获取字典列表成功", dictList);
        } catch (ProgramException p) {
            log.error("获取字典列表失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取字典列表失败." + e.getMessage());
            return renderError("获取字典列表失败");
        }
    }

    /**
     * 更新指定字典
     * @param id
     * @param code
     * @param name
     * @return
     */
    @RequestMapping(value = "updateSaveDict", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult updateSaveDict(@RequestParam String id,
                                     @RequestParam String code,
                                     @RequestParam String name,
                                     @RequestParam String parentCode,
                                     @RequestParam int sort) {
        try {
            boolean isSuccess = false;
            String actionContext;
            Dict queryCode = new Dict();
            queryCode.setParentId(parentCode);
            queryCode.setCode(code);
            Dict codeDict = dictService.getDict(queryCode);

            Dict queryName = new Dict();
            queryName.setParentId(parentCode);
            queryName.setName(name);
            Dict nameDict = dictService.getDict(queryName);

            if("-1".equalsIgnoreCase(id)){
                actionContext = "保存";
                Dict queryParent = new Dict();
                queryParent.setCode(parentCode);
                Dict parentDict = dictService.getDict(queryParent);

                if(codeDict == null && nameDict == null && parentDict != null){
                    Dict dict = new Dict();
                    dict.setId(UUIDUtil.getUUID());
                    dict.setCode(code);
                    dict.setName(name);
                    dict.setParentId(parentCode);
                    dict.setType(parentDict.getType());
                    dict.setSort(sort);
                    dict.setCreatedatetime(LocalDateTime.now());
                    dict.setModifydatetime(LocalDateTime.now());
                    isSuccess = dictService.save(dict);
                }
            }
            else {
                actionContext = "更新";
                if(codeDict.getId().equals(id)){
                    codeDict = null;
                }
                if(nameDict.getId().equals(id)){
                    nameDict = null;
                }
                if(codeDict == null && nameDict == null) {
                    isSuccess = dictService.updateDictById(id, code, name);
                }
            }
            if (isSuccess) {
                return renderSuccess(actionContext + "成功");
            } else {
                return renderError(actionContext + "失败");
            }
        } catch (ProgramException p) {
            log.error(p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return renderError(e.getMessage());
        }
    }
    /**
     * 删除字典(支持批量删除)
     *
     * @return JsonResult
     */
    @RequestMapping(value = "dict", method = RequestMethod.DELETE)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult delDictByIds(String ids,String codes) {

        try {
            boolean isSuccess = dictService.delDictBatchByIds(ids,codes);
            if (isSuccess) {
                removeCache();
                return renderSuccess("删除成功");
            } else {
                return renderError("删除失败");
            }
        } catch (ProgramException p) {
            log.error(p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("删除失败." + e.getMessage());
            return renderError(e.getMessage());
        }
    }

    /**
     * 获取所有权限并根据角色做所属标记
     *
     * @return JsonResult
     */
    @RequestMapping(value = "getAllPermissionList", method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult getAllPermissionList(@RequestParam String roleId) {
        try {
            List<Dict> dictList = dictService.getAllPermissionLisByRole(roleId);
            return renderSuccess("获取权限列表成功", dictList);
        } catch (ProgramException p) {
            log.error("获取权限列表失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取权限列表失败." + e.getMessage());
            return renderError("获取权限列表失败");
        }
    }

    /**
     * 为角色分配权限
     *
     * @return JsonResult
     */
    @RequestMapping(value = "addPermissionByRole", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("ROLE_SUPERADMIN")
    public JsonResult addPermissionByRole(@RequestParam String roleId,@RequestParam String permissions) {
        try {
            dictService.addPermissionByRole(roleId,permissions);
            removeCache();
            return renderSuccess("权限添加成功");
        } catch (ProgramException p) {
            log.error("权限添加失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("权限添加失败." + e.getMessage());
            return renderError("权限添加失败");
        }
    }

    private void removeCache(){
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        //清除权限 相关的缓存
        shiroRealm.clearAllCache();
    }
}
