package com.safety.controller;


import com.safety.entity.CheckDayRecordList;
import com.safety.service.ICheckDayRecordListService;
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
 * 日治理记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDayRecordList")
public class CheckDayRecordListController extends BaseController {
    @Autowired
    private ICheckDayRecordListService iCheckDayRecordListService;
    /**
     * 添加
     * @param checkDayRecordList
     * @return
     */
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayRecordList(@RequestBody CheckDayRecordList checkDayRecordList){
        String id = UUIDUtil.getUUID();
        checkDayRecordList.setId(id);
        checkDayRecordList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckDayRecordListService.save(checkDayRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayRecordList
     * @return
     */
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayRecordList(@RequestBody CheckDayRecordList checkDayRecordList){
        boolean result = iCheckDayRecordListService.updateById(checkDayRecordList);
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
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayRecordList(String id){
        boolean result = iCheckDayRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayRecordListById(String id){
        CheckDayRecordList checkDayRecordList = iCheckDayRecordListService.getById(id);
        if(checkDayRecordList!=null){
            return renderSuccess("查询成功",checkDayRecordList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkDayRecordListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkDayRecordListEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordListEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordListEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkDayRecordListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
