package com.safety.controller;


import com.safety.entity.CheckHolidayRecord;
import com.safety.service.ICheckHolidayRecordService;
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
    public JsonResult addCheckHolidayRecord(CheckHolidayRecord checkHolidayRecord){
        String id = UUIDUtil.getUUID();
        checkHolidayRecord.setId(id);
        boolean result = iCheckHolidayRecordService.save(checkHolidayRecord);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateCheckHolidayRecord(CheckHolidayRecord checkHolidayRecord){
        boolean result = iCheckHolidayRecordService.updateById(checkHolidayRecord);
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
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckHolidayRecord(String id){
        boolean result = iCheckHolidayRecordService.removeById(id);
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
    @RequestMapping(value = "/checkHolidayRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordById(String id){
        CheckHolidayRecord checkHolidayRecord = iCheckHolidayRecordService.getById(id);
        if(checkHolidayRecord!=null){
            return renderSuccess("查询成功",checkHolidayRecord);
        }else {
            return renderSuccess("无数据");
        }
    }
}
