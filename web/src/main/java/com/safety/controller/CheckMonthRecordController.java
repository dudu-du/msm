package com.safety.controller;


import com.safety.entity.CheckMonthRecord;
import com.safety.service.ICheckMonthRecordService;
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
}
