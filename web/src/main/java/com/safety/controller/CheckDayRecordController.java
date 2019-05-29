package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDayRecord;
import com.safety.exception.ProgramException;
import com.safety.service.ICheckDayRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 日治理记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@Slf4j
@RequestMapping("/safety/checkDayRecord")
public class CheckDayRecordController extends BaseController {
    @Autowired
    private ICheckDayRecordService iCheckDayRecordService;
    /**
     * 添加
     * @param checkDayRecord
     * @return
     */
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayRecord(@RequestBody CheckDayRecord checkDayRecord){
        boolean result = iCheckDayRecordService.addCheckDayRecord(checkDayRecord);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayRecord
     * @return
     */
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayRecord(@RequestBody CheckDayRecord checkDayRecord){
        boolean result = iCheckDayRecordService.updateById(checkDayRecord);
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
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayRecord(String id){
        boolean result = iCheckDayRecordService.removeById(id);
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
    @RequestMapping(value = "/checkDayRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayRecordById(String id){
        CheckDayRecord checkMonthRecord = iCheckDayRecordService.getById(id);
        if(checkMonthRecord!=null){
            return renderSuccess("查询成功",checkMonthRecord);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 查询日治理列表
     * @return
     */
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getCheckDayRecord(String orgId, String year){
        CheckDayRecord checkDayRecord = iCheckDayRecordService.getByParam(orgId,year);
        if(checkDayRecord!=null){
            return renderSuccess("查询成功",checkDayRecord);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 分页查询日治理记录
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/checkDayRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckDayRecord> page = iCheckDayRecordService.getByPage(currentPage, pageSize);
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
    @RequestMapping(value = "/checkDayRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkDayRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkDayRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckDayRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/dayRecordPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
