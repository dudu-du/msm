package com.safety.controller;

import com.safety.entity.Dict;
import com.safety.entity.Org;
import com.safety.exception.ProgramException;
import com.safety.service.IDictService;
import com.safety.service.IOrgService;
import com.safety.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/View")
public class OrgController extends BaseController {

    @Autowired
    private IOrgService orgService;

    @Autowired
    private IDictService dictService;

    /**
     * 机构列表页面
     *
     * @return view
     */
    @RequestMapping(value = "/orgList", method = RequestMethod.GET)
    public BaseModelAndView showClientList() {
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("org/mechanismAdmin");
        return modelAndView;
    }

    /**
     * 获取所有机构（除部门）列表
     *
     * @param parentId 父类机构ID 没有父类传"0"
     * @return 返回设备树
     */
    @RequestMapping(value = "/allOrgList", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getAllOrgList(String parentId) {
        try {
            //todo 字典表验证
            List orgList = orgService.getAllOrgList(parentId);
            log.info("获取机构列表：" + orgList);
            return renderSuccess("机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 获取所有部门列表（树结构）
     *
     * @param parentId 父类机构ID
     * @return JsonResult
     */
    @RequestMapping(value = "/allDepartmentList", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDepartmentList(String parentId) {
        try {
            //todo 字典表验证
            List orgList = orgService.getDepartmentList(parentId);
            log.info("获取机构列表：" + orgList);
            return renderSuccess("机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 获取所有部门列表（排结构）
     *
     * @param parentId 父类机构ID
     * @return JsonResult
     */
    @RequestMapping(value = "/allDepartmentRow", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDepartmentRow(String parentId) {
        try {
            //todo 字典表验证
            List orgList = orgService.getDepartmentRow(parentId);
            log.info("获取机构列表：" + orgList);
            return renderSuccess("机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }


    /**
     * 根据类型获取所有机构列表
     *
     * @param orgType 机构类型0校/1局
     * @return
     */
    @RequestMapping(value = "/allTypeOrgList", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getAllOrgListByType(String orgType) {
        try {
            //todo 字典表验证
            List orgList = orgService.getAllOrgListByType(orgType);
            log.info("获取机构列表：" + orgList);
            return renderSuccess("机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 分页获取下属机构列表
     *
     * @param orgType  机构类型
     * @param parentId 父类机构ID 没有父类传"0"
     * @return JsonResult
     */
    @RequestMapping(value = "/listOrgPage", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getOrgListByPage(Integer page, Integer pageSize, String orgType, String parentId) {
        try {
            //todo 字典表验证
            if (page == null || pageSize == null) {
                return renderError("参数错误");
            }
            PageData orgList = orgService.getOrgList(page, pageSize, orgType, parentId);
            log.info("分页获取机构列表：" + orgList);
            return renderSuccess("获取机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 获取下属一级机构列表（不分页）
     *
     * @param orgType  机构类型
     * @param parentId 父类机构ID 没有父类传"0"
     * @return JsonResult
     */
    @RequestMapping(value = "/listOrg", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getOrgList(String orgType, String parentId) {
        try {
            //todo 字典表验证
            List orgList = orgService.getOrgList(orgType, parentId);
            log.info("分页获取机构列表：" + orgList);
            return renderSuccess("获取机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 模糊查询获取机构列表
     *
     * @param orgType 机构类型
     * @param str     传递参数
     * @return JsonResult
     */
    @RequestMapping(value = "/strOrg", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getOrgListBystr(String orgType, String str) {
        try {
            //todo 字典表验证
            List<Org> orgList = orgService.getOrgListBystr(orgType, str);
            log.info("获取机构列表：" + orgList);
            return renderSuccess("获取机构列表成功", orgList);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构列表失败." + e.getMessage());
            return renderError("获取机构列表失败");
        }
    }

    /**
     * 根据ID获取机构
     *
     * @param orgId 机构ID
     * @return JsonResult
     */
    @RequestMapping(value = "/org", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getOrgById(String orgId) {
        try {
            //todo 字典表验证
            log.info("获取机构ID为：" + orgId);
            Org org = orgService.getOrgById(orgId);
            log.info("获取机构列表：" + org);
            return renderSuccess("获取机构成功", org);
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("获取机构失败." + e.getMessage());
            return renderError("获取机构失败");
        }
    }

    /**
     * 添加机构列表
     *
     * @param code        机构编码
     * @param name        机构名称
     * @param remarksType 附属类型：属于什么类型学校 可以为""
     * @param simpleName  简称 可以为""
     * @param orgType     机构类型
     * @param parentId    父类机构ID 没有父类传"0"
     * @param header    负责人
     * @param worker    成员
     * @return JsonResult
     */
    @RequestMapping(value = "/org", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addOrg(String orgType, String code, String name, String simpleName, String parentId,
                             String remarksType, Integer sort, String domainName,String header,String worker) {
        try {
            //todo 字典表验证
            boolean result = orgService.addOrg(orgType, code, name, simpleName, parentId, remarksType, sort, domainName,header,worker);
            log.info("添加机构");
            if (result) {
                Org org = orgService.getOrgByCode(code);
                return renderSuccess("添加应用成功", org);
            } else {
                return renderError("添加应用失败");
            }
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("添加机构失败." + e.getMessage());
            return renderError("添加机构失败");
        }
    }

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
     * @param header    负责人
     * @param worker    成员
     * @return JsonResult
     */
    @RequestMapping(value = "/org", method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateOrg(String orgId, String orgType, String code, String name,
                                String simpleName, String parentId, String remarksType,
                                Integer sort, String domainName,String header,String worker) {
        try {
            //todo 字典表验证
            boolean result = orgService.updateOrg(orgId, orgType, code, name, simpleName,
                    parentId, remarksType, sort, domainName,header,worker);
            log.info("修改机构");
            if (result) {
                return renderSuccess("修改机构成功");
            } else {
                return renderError("修改机构失败");
            }
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("修改机构失败." + e.getMessage());
            return renderError("修改机构失败");
        }
    }

    /**
     * 删除机构列表
     *
     * @param orgId 机构Id
     * @return JsonResult
     */
    @RequestMapping(value = "/org", method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult delOrg(String orgId) {
        try {
            log.info("删除机构,orgIds为:" + orgId);
            boolean result = orgService.delOrg(orgId);
            if (result) {
                return renderSuccess("删除机构成功");
            } else {
                return renderError("删除机构失败");
            }
        } catch (ProgramException e) {
            return renderError(e.getMessage());
        } catch (Exception e) {
            log.error("删除机构失败." + e.getMessage());
            return renderError("删除机构失败");
        }
    }


    /**
     * 获取某个学校下面的所有学段
     *
     * @param orgId 机构id(学校id)
     * @return JsonResult
     */
    @RequestMapping(value = "section", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getSectionDictByOrgId(String orgId) {
        List<Dict> list;
        try {
            //如果orgId为0的话则为超级管理员,则获取所的学段
            if ("0".equals(orgId)) {
                list = dictService.getDictListByParentCode(DictConstants.XUEDUAN);
                log.info("获取列表为===============" + list);
            } else {
                list = dictService.getSectionDictByOrgId(orgId);
            }
        } catch (ProgramException p) {
            log.error("获取学校学段失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取学期学段失败." + e.getMessage());
            return renderError("获取学校学段失败");
        }
        return renderSuccess("获取学学校学段成功", list);
    }


    /**
     * 据学段获取学段下的年级
     *
     * @param sectionCode 学段code
     * @return JsonResult
     */
    @RequestMapping(value = "grade", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getGradeDictBySectionCode(String sectionCode) {
        try {
            List<Dict> list = dictService.getGradeDictBySectionCode(sectionCode);
            return renderSuccess("获取年级成功", list);
        } catch (ProgramException p) {
            log.error("获取年级失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取年级失败." + e.getMessage());
            return renderError("获取年级失败");
        }
    }


}
