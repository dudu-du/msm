package com.safety.controller;


import com.safety.entity.CheckDay;
import com.safety.service.ICheckDayService;
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
 * 日治理记录 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDay")
public class CheckDayController extends BaseController {
    @Autowired
    private ICheckDayService iCheckDayService;
    /**
     * 添加
     * @param checkDay
     * @return
     */
    @RequestMapping(value = "/checkDay",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDay(CheckDay checkDay){
        String id = UUIDUtil.getUUID();
        checkDay.setId(id);
        boolean result = iCheckDayService.save(checkDay);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDay
     * @return
     */
    @RequestMapping(value = "/checkDay",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDay(CheckDay checkDay){
        boolean result = iCheckDayService.updateById(checkDay);
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
    @RequestMapping(value = "/checkDay",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDay(String id){
        boolean result = iCheckDayService.removeById(id);
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
    @RequestMapping(value = "/checkDay",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayById(String id){
        CheckDay checkDay = iCheckDayService.getById(id);
        if(checkDay!=null){
            return renderSuccess("查询成功",checkDay);
        }else {
            return renderError("无数据");
        }
    }
}
