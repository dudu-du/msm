package com.safety.controller;


import com.safety.entity.CheckWeekRecordList;
import com.safety.service.ICheckWeekRecordListService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 周排查记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkWeekRecordList")
public class CheckWeekRecordListController extends BaseController {
    @Autowired
    private ICheckWeekRecordListService iCheckWeekRecordListService;
    /**
     * 添加
     * @param checkWeekRecordList
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckWeekRecordList(@RequestBody CheckWeekRecordList checkWeekRecordList){
        String id = UUIDUtil.getUUID();
        checkWeekRecordList.setId(id);
        boolean result = iCheckWeekRecordListService.save(checkWeekRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkWeekRecordList
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckWeekRecordList(@RequestBody CheckWeekRecordList checkWeekRecordList){
        boolean result = iCheckWeekRecordListService.updateById(checkWeekRecordList);
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
    @RequestMapping(value = "/checkWeekRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckWeekRecordList(String id){
        boolean result = iCheckWeekRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkWeekRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekRecordListById(String id){
        CheckWeekRecordList checkWeekRecordList = iCheckWeekRecordListService.getById(id);
        if(checkWeekRecordList!=null){
            return renderSuccess("查询成功",checkWeekRecordList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
