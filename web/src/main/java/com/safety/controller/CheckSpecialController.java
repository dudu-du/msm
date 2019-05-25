package com.safety.controller;


import com.safety.entity.CheckSpecial;
import com.safety.service.ICheckSpecialService;
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
 * 专项检查 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecial")
public class CheckSpecialController extends BaseController {
    @Autowired
    private ICheckSpecialService iCheckSpecialService;
    /**
     * 添加
     * @param checkSpecial
     * @return
     */
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecial(CheckSpecial checkSpecial){
        String id = UUIDUtil.getUUID();
        checkSpecial.setId(id);
        boolean result = iCheckSpecialService.save(checkSpecial);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecial
     * @return
     */
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecial(CheckSpecial checkSpecial){
        boolean result = iCheckSpecialService.updateById(checkSpecial);
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
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecial(String id){
        boolean result = iCheckSpecialService.removeById(id);
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
    @RequestMapping(value = "/checkSpecial",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialById(String id){
        CheckSpecial checkSpecial = iCheckSpecialService.getById(id);
        if(checkSpecial!=null){
            return renderSuccess("查询成功",checkSpecial);
        }else {
            return renderError("无数据");
        }
    }
}
