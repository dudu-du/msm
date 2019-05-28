package com.safety.controller;


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
        String id = UUIDUtil.getUUID();
        checkHolidayRecord.setId(id);
        checkHolidayRecord.setCreateTime(LocalDateTime.now());
        boolean result = iCheckHolidayRecordService.save(checkHolidayRecord);
        if (result){
            return renderSuccess("添加成功", id);
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
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordById(String id){
        CheckHolidayRecord checkHolidayRecord = iCheckHolidayRecordService.getById(id);
        if(checkHolidayRecord!=null){
            return renderSuccess("查询成功",checkHolidayRecord);
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
    @RequestMapping(value = "/checkHolidayRecordUpdate",method = RequestMethod.GET)
    public BaseModelAndView getCheckHolidayRecordUpdate(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("check/holidayRecordUpdate");
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
