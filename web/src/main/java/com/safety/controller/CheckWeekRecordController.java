package com.safety.controller;


import com.safety.entity.CheckWeekRecord;
import com.safety.service.ICheckWeekRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
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
        String id = UUIDUtil.getUUID();
        checkWeekRecord.setId(id);
        boolean result = iCheckWeekRecordService.save(checkWeekRecord);
        if (result){
            return renderSuccess("添加成功", id);
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
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkWeekRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckWeekRecordById(String id){
        CheckWeekRecord checkWeekRecord = iCheckWeekRecordService.getById(id);
        if(checkWeekRecord!=null){
            return renderSuccess("查询成功",checkWeekRecord);
        }else {
            return renderError("无数据");
        }
    }
}
