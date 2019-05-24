package com.safety.controller;


import com.safety.entity.CheckDayRecord;
import com.safety.service.ICheckDayRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 日治理记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
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
    public JsonResult addCheckDayRecord(CheckDayRecord checkDayRecord){
        String id = UUIDUtil.getUUID();
        checkDayRecord.setId(id);
        boolean result = iCheckDayRecordService.save(checkDayRecord);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateCheckDayRecord(CheckDayRecord checkDayRecord){
        boolean result = iCheckDayRecordService.updateById(checkDayRecord);
        if (result){
            return renderSuccess("修改成功");
        }else {
            return renderSuccess("修改失败");
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
            return renderSuccess("删除失败");
        }
    }

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayRecordById(String id){
        CheckDayRecord checkDayRecord = iCheckDayRecordService.getById(id);
        if(checkDayRecord!=null){
            return renderSuccess("查询成功",checkDayRecord);
        }else {
            return renderSuccess("无数据");
        }
    }
}
