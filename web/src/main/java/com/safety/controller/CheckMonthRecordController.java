package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckMonthRecord;
import com.safety.service.ICheckMonthRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


/**
 * <p>
 * 月排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkMonthRecord")
public class CheckMonthRecordController extends BaseController {
    @Autowired
    private ICheckMonthRecordService iCheckMonthRecordService;
    /**
     * 添加
     * @param checkMonthRecord
     * @return
     */
    @RequestMapping(value = "/checkMonthRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckMonthRecord(@RequestBody CheckMonthRecord checkMonthRecord){
        boolean result = iCheckMonthRecordService.addCheckMonthRecord(checkMonthRecord);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkMonthRecord
     * @return
     */
    @RequestMapping(value = "/checkMonthRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckMonthRecord(@RequestBody CheckMonthRecord checkMonthRecord){
        boolean result = iCheckMonthRecordService.updateById(checkMonthRecord);
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
    @RequestMapping(value = "/checkMonthRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckMonthRecord(String id){
        boolean result = iCheckMonthRecordService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthRecordById(String id){
        CheckMonthRecord checkMonthRecord = iCheckMonthRecordService.getById(id);
        if(checkMonthRecord!=null){
            return renderSuccess("查询成功",checkMonthRecord);
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
    @RequestMapping(value = "/checkMonthRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthRecordByParam(String orgId, String year){
        CheckMonthRecord checkMonthRecord = iCheckMonthRecordService.getByParam(orgId,year);
        if(checkMonthRecord!=null){
            return renderSuccess("查询成功",checkMonthRecord);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 分页查询月记录
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckMonthRecord> page = iCheckMonthRecordService.getByPage(currentPage, pageSize);
        if(page!=null){
            return renderSuccess("查询成功",page);
        }else {
            return renderError("无数据");
        }
    }

    //-----------------------------------------------------页面跳转-----------------------------------------------------
    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkMonthRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonthRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/monthRecordPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
