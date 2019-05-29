package com.safety.controller;


import com.safety.entity.CheckWeek;
import com.safety.service.ICheckWeekService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 周治理记录 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkWeek")
public class CheckWeekController extends BaseController {
    @Autowired
    private ICheckWeekService iCheckWeekService;
    /**
     * 添加
     * @param checkWeek
     * @return
     */
    @RequestMapping(value = "/checkWeek",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckWeek(@RequestBody CheckWeek checkWeek){
        boolean result = iCheckWeekService.addCheckWeek(checkWeek);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkWeek
     * @return
     */
    @RequestMapping(value = "/checkWeek",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckWeek(@RequestBody CheckWeek checkWeek){
        boolean result = iCheckWeekService.updateById(checkWeek);
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
    @RequestMapping(value = "/checkWeek",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckWeek(String id){
        boolean result = iCheckWeekService.removeById(id);
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
    @RequestMapping(value = "/checkWeekById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekById(String id){
        CheckWeek checkWeek = iCheckWeekService.getById(id);
        if(checkWeek!=null){
            return renderSuccess("查询成功",checkWeek);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 根据日期和机构名称查询
     * @param orgId
     * @param year
     * @return
     */
    @RequestMapping(value = "/checkWeek",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekByParam(String orgId, String year){
        CheckWeek checkWeek = iCheckWeekService.getByParam(orgId,year);
        if(checkWeek!=null){
            return renderSuccess("查询成功",checkWeek);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkWeekAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkWeekEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkWeekPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
