package com.safety.controller;


import com.safety.entity.CheckDayList;
import com.safety.service.ICheckDayListService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 * 日治理记录列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDayList")
public class CheckDayListController extends BaseController {
    @Autowired
    private ICheckDayListService iCheckDayListService;
    /**
     * 添加
     * @param checkDayList
     * @return
     */
    @RequestMapping(value = "/checkDayList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayList(@RequestBody CheckDayList checkDayList){
        String id = UUIDUtil.getUUID();
        checkDayList.setId(id);
        checkDayList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckDayListService.save(checkDayList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayList
     * @return
     */
    @RequestMapping(value = "/checkDayList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayList(@RequestBody CheckDayList checkDayList){
        boolean result = iCheckDayListService.updateById(checkDayList);
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
    @RequestMapping(value = "/checkDayList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayList(String id){
        boolean result = iCheckDayListService.removeById(id);
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
    @RequestMapping(value = "/checkDayList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayListById(String id){
        CheckDayList checkDayList = iCheckDayListService.getById(id);
        if(checkDayList!=null){
            return renderSuccess("查询成功",checkDayList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkDayListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkDayListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkDayListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
