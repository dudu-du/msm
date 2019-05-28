package com.safety.controller;


import com.safety.entity.CheckWeekList;
import com.safety.service.ICheckWeekListService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 周治理记录列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkWeekList")
public class CheckWeekListController extends BaseController {
    @Autowired
    private ICheckWeekListService iCheckWeekListService;
    /**
     * 添加
     * @param checkWeekList
     * @return
     */
    @RequestMapping(value = "/checkWeekList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckWeekList(@RequestBody CheckWeekList checkWeekList){
        String id = UUIDUtil.getUUID();
        checkWeekList.setId(id);
        boolean result = iCheckWeekListService.save(checkWeekList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkWeekList
     * @return
     */
    @RequestMapping(value = "/checkWeekList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckWeekList(@RequestBody CheckWeekList checkWeekList){
        boolean result = iCheckWeekListService.updateById(checkWeekList);
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
    @RequestMapping(value = "/checkWeekList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckWeekList(String id){
        boolean result = iCheckWeekListService.removeById(id);
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
    @RequestMapping(value = "/checkWeekList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekListById(String id){
        CheckWeekList checkWeekList = iCheckWeekListService.getById(id);
        if(checkWeekList!=null){
            return renderSuccess("查询成功",checkWeekList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkWeekListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkWeekListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkWeekListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
