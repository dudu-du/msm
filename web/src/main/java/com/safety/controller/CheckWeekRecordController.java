package com.safety.controller;


import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckWeekRecord;
import com.safety.service.ICheckWeekRecordService;
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
 * 月排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkWeekRecord")
public class CheckWeekRecordController extends BaseController {
    @Autowired
    private ICheckWeekRecordService iCheckWeekRecordService;
    /**
     * 添加
     * @param checkWeekRecord
     * @return
     */
    @RequestMapping(value = "/checkWeekRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckWeekRecord(@RequestBody CheckWeekRecord checkWeekRecord){
        boolean result = iCheckWeekRecordService.addCheckWeekRecord(checkWeekRecord);
        if (result){
            return renderSuccess("添加成功",result);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkWeekRecord
     * @return
     */
    @RequestMapping(value = "/checkWeekRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckWeekRecord(@RequestBody CheckWeekRecord checkWeekRecord){
        boolean result = iCheckWeekRecordService.updateById(checkWeekRecord);
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
    @RequestMapping(value = "/checkWeekRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckWeekRecord(String id){
        boolean result = iCheckWeekRecordService.removeById(id);
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
    @RequestMapping(value = "/checkWeekRecordById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekRecordById(String id){
        CheckWeekRecord checkWeekRecord = iCheckWeekRecordService.getById(id);
        if(checkWeekRecord!=null){
            return renderSuccess("查询成功",checkWeekRecord);
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
    @RequestMapping(value = "/checkWeekRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekRecordByParam(String orgId, String year){
        CheckWeekRecord checkWeekRecord = iCheckWeekRecordService.getByParam(orgId,year);
        if(checkWeekRecord!=null){
            return renderSuccess("查询成功",checkWeekRecord);
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
    @RequestMapping(value = "/checkWeekRecordByPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekRecordByPage(@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="10")Integer pageSize,@RequestParam(defaultValue="")String orgId){
        PageInfo<CheckWeekRecord> page = iCheckWeekRecordService.getByPage(currentPage, pageSize, orgId);
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
    @RequestMapping(value = "/checkWeekRecordAdd",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordAdd(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordAdd");
        return modelAndView;
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordEdit",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordEdit(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordEdit");
        return modelAndView;
    }

    /**
     * 分页查询页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordPage",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordPage(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordPage");
        return modelAndView;
    }


    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/checkWeekRecordPrint",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeekRecordPrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/weekRecordPrint");
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
}
