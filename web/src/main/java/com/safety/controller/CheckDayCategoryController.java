package com.safety.controller;


import com.safety.entity.CheckDayCategory;
import com.safety.service.ICheckDayCategoryService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 日治理记录大类 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDayCategory")
public class CheckDayCategoryController extends BaseController {
    @Autowired
    private ICheckDayCategoryService iCheckDayCategoryService;
    /**
     * 添加
     * @param checkDayCategory
     * @return
     */
    @RequestMapping(value = "/checkDayCategory",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayCategory(CheckDayCategory checkDayCategory){
        String id = UUIDUtil.getUUID();
        checkDayCategory.setId(id);
        boolean result = iCheckDayCategoryService.save(checkDayCategory);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayCategory
     * @return
     */
    @RequestMapping(value = "/checkDayCategory",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayCategory(CheckDayCategory checkDayCategory){
        boolean result = iCheckDayCategoryService.updateById(checkDayCategory);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderError("修改失败");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkDayCategory",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayCategory(String id){
        boolean result = iCheckDayCategoryService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkDayCategory",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayCategoryById(String id){
        CheckDayCategory checkDayCategory = iCheckDayCategoryService.getById(id);
        if(checkDayCategory!=null){
            return renderSuccess("查询成功",checkDayCategory);
        }else {
            return renderError("无数据");
        }
    }
}
