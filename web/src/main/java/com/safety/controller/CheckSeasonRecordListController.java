package com.safety.controller;


import com.safety.entity.CheckSeasonRecordList;
import com.safety.service.ICheckSeasonRecordListService;
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
 * 综合季度排查记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSeasonRecordList")
public class CheckSeasonRecordListController extends BaseController {
    @Autowired
    private ICheckSeasonRecordListService iCheckSeasonRecordListService;
    /**
     * 添加
     * @param checkSeasonRecordList
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSeasonRecordList(@RequestBody CheckSeasonRecordList checkSeasonRecordList){
        String id = UUIDUtil.getUUID();
        checkSeasonRecordList.setId(id);
        checkSeasonRecordList.setCreateTime(LocalDateTime.now());
        boolean result = iCheckSeasonRecordListService.save(checkSeasonRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSeasonRecordList
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSeasonRecordList(@RequestBody CheckSeasonRecordList checkSeasonRecordList){
        boolean result = iCheckSeasonRecordListService.updateById(checkSeasonRecordList);
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
    @RequestMapping(value = "/checkSeasonRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSeasonRecordList(String id){
        boolean result = iCheckSeasonRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkSeasonRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSeasonRecordListById(String id){
        CheckSeasonRecordList checkSeasonRecordList = iCheckSeasonRecordListService.getById(id);
        if(checkSeasonRecordList!=null){
            return renderSuccess("查询成功",checkSeasonRecordList);
        }else {
            return renderError("无数据");
        }
    }


    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordListAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordListAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordListAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordListEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordListEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordListEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecordListPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckSeasonRecordListPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/seasonRecordListPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
