package com.safety.controller;


import com.safety.entity.CheckMonthRecordList;
import com.safety.service.ICheckMonthRecordListService;
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
 * 月排查记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkMonthRecordList")
public class CheckMonthRecordListController extends BaseController {
    @Autowired
    private ICheckMonthRecordListService iCheckMonthRecordListService;
    /**
     * 添加
     * @param checkMonthRecordList
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckMonthRecordList(@RequestBody CheckMonthRecordList checkMonthRecordList){
        String id = UUIDUtil.getUUID();
        checkMonthRecordList.setId(id);
        checkMonthRecordList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckMonthRecordListService.save(checkMonthRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkMonthRecordList
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckMonthRecordList(@RequestBody CheckMonthRecordList checkMonthRecordList){
        boolean result = iCheckMonthRecordListService.updateById(checkMonthRecordList);
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
    @RequestMapping(value = "/checkMonthRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckMonthRecordList(String id){
        boolean result = iCheckMonthRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkMonthRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthRecordListById(String id){
        CheckMonthRecordList checkMonthRecordList = iCheckMonthRecordListService.getById(id);
        if(checkMonthRecordList!=null){
            return renderSuccess("查询成功",checkMonthRecordList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
