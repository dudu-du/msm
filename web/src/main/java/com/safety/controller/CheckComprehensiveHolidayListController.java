package com.safety.controller;


import com.safety.entity.CheckComprehensiveHolidayList;
import com.safety.service.ICheckComprehensiveHolidayListService;
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
 * 综合检查(节假日、复产前)列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkComprehensiveHolidayList")
public class CheckComprehensiveHolidayListController extends BaseController {

    @Autowired
    private ICheckComprehensiveHolidayListService iCheckComprehensiveHolidayListService;
    /**
     * 添加
     * @param checkComprehensiveHolidayList
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHolidayList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckComprehensiveHolidayList(@RequestBody CheckComprehensiveHolidayList checkComprehensiveHolidayList){
        String id = UUIDUtil.getUUID();
        checkComprehensiveHolidayList.setId(id);
        checkComprehensiveHolidayList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckComprehensiveHolidayListService.save(checkComprehensiveHolidayList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkComprehensiveHolidayList
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHolidayList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckComprehensiveHolidayList(@RequestBody CheckComprehensiveHolidayList checkComprehensiveHolidayList){
        boolean result = iCheckComprehensiveHolidayListService.updateById(checkComprehensiveHolidayList);
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
    @RequestMapping(value = "/checkComprehensiveHolidayList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckComprehensiveHolidayList(String id){
        boolean result = iCheckComprehensiveHolidayListService.removeById(id);
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
    @RequestMapping(value = "/checkComprehensiveHolidayList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveHolidayListById(String id){
        CheckComprehensiveHolidayList checkComprehensiveHolidayList = iCheckComprehensiveHolidayListService.getById(id);
        if(checkComprehensiveHolidayList!=null){
            return renderSuccess("查询成功",checkComprehensiveHolidayList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHolidayListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckComprehensiveHolidayListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/comprehensiveHolidayListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHolidayListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckComprehensiveHolidayListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/comprehensiveHolidayListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHolidayListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckComprehensiveHolidayListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/comprehensiveHolidayListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
