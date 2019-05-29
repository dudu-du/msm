package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckHolidayRecord;
import com.safety.service.ICheckHolidayRecordService;
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
 * 综合节假日排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkHolidayRecord")
public class CheckHolidayRecordController extends BaseController {
    @Autowired
    private ICheckHolidayRecordService iCheckHolidayRecordService;
    /**
     * 添加
     * @param checkHolidayRecord
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckHolidayRecord(@RequestBody CheckHolidayRecord checkHolidayRecord){
        boolean result = iCheckHolidayRecordService.addCheckHolidayRecord(checkHolidayRecord);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkHolidayRecord
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckHolidayRecord(@RequestBody CheckHolidayRecord checkHolidayRecord){
        boolean result = iCheckHolidayRecordService.updateById(checkHolidayRecord);
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
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckHolidayRecord(String id){
        boolean result = iCheckHolidayRecordService.removeById(id);
        if (result){
            return renderSuccess("删除成功");
        }else {
            return renderError("删除失败");
        }
    }

    /**
     * 通过ID查询b'
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordById(String id){
        CheckHolidayRecord checkHolidayRecord = iCheckHolidayRecordService.getById(id);
        if(checkHolidayRecord!=null){
            return renderSuccess("查询成功",checkHolidayRecord);
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
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordByParam(String orgId, String year){
        CheckHolidayRecord checkHolidayRecord = iCheckHolidayRecordService.getByParam(orgId,year);
        if(checkHolidayRecord!=null){
            return renderSuccess("查询成功",checkHolidayRecord);
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
    @RequestMapping(value = "/checkHolidayRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize){
        PageInfo<CheckHolidayRecord> page = iCheckHolidayRecordService.getByPage(currentPage, pageSize);
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
    @RequestMapping(value = "/checkHolidayRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckHolidayRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/holidayRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckHolidayRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/holidayRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckHolidayRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/holidayRecordPage");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
