package com.safety.controller;


import com.safety.entity.CheckSpecialRecordList;
import com.safety.service.ICheckSpecialRecordListService;
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
 * 周排查记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecialRecordList")
public class CheckSpecialRecordListController extends BaseController {
    @Autowired
    private ICheckSpecialRecordListService iCheckSpecialRecordListService;
    /**
     * 添加
     * @param checkSpecialRecordList
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecialRecordList(@RequestBody CheckSpecialRecordList checkSpecialRecordList){
        String id = UUIDUtil.getUUID();
        checkSpecialRecordList.setId(id);
        checkSpecialRecordList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckSpecialRecordListService.save(checkSpecialRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecialRecordList
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecialRecordList(@RequestBody CheckSpecialRecordList checkSpecialRecordList){
        boolean result = iCheckSpecialRecordListService.updateById(checkSpecialRecordList);
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
    @RequestMapping(value = "/checkSpecialRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecialRecordList(String id){
        boolean result = iCheckSpecialRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkSpecialRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialRecordListById(String id){
        CheckSpecialRecordList checkSpecialRecordList = iCheckSpecialRecordListService.getById(id);
        if(checkSpecialRecordList!=null){
            return renderSuccess("查询成功",checkSpecialRecordList);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordListUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordListUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordListUpdate");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecordListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckSpecialRecordListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/specialRecordListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
