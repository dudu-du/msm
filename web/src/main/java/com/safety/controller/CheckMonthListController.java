package com.safety.controller;


import com.safety.entity.CheckMonthList;
import com.safety.service.ICheckMonthListService;
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
 * 月治理记录列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkMonthList")
public class CheckMonthListController extends BaseController {
    @Autowired
    private ICheckMonthListService iCheckMonthListService;
    /**
     * 添加
     * @param checkMonthList
     * @return
     */
    @RequestMapping(value = "/checkMonthList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckMonthList(@RequestBody CheckMonthList checkMonthList){
        String id = UUIDUtil.getUUID();
        checkMonthList.setId(id);
        checkMonthList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckMonthListService.save(checkMonthList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkMonthList
     * @return
     */
    @RequestMapping(value = "/checkMonthList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckMonthList(@RequestBody CheckMonthList checkMonthList){
        boolean result = iCheckMonthListService.updateById(checkMonthList);
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
    @RequestMapping(value = "/checkMonthList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckMonthList(String id){
        boolean result = iCheckMonthListService.removeById(id);
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
    @RequestMapping(value = "/checkMonthList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthListById(String id){
        CheckMonthList checkMonthList = iCheckMonthListService.getById(id);
        if(checkMonthList!=null){
            return renderSuccess("查询成功",checkMonthList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkMonthListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkMonthListEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthListEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthListEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkMonthListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
