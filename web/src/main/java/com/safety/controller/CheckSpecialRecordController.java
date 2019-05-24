package com.safety.controller;


import com.safety.entity.CheckSpecialRecord;
import com.safety.service.ICheckSpecialRecordService;
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
 * 月排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSpecialRecord")
public class CheckSpecialRecordController extends BaseController {
    @Autowired
    private ICheckSpecialRecordService iCheckSpecialRecordService;
    /**
     * 添加
     * @param checkSpecialRecord
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSpecialRecord(CheckSpecialRecord checkSpecialRecord){
        String id = UUIDUtil.getUUID();
        checkSpecialRecord.setId(id);
        boolean result = iCheckSpecialRecordService.save(checkSpecialRecord);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSpecialRecord
     * @return
     */
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSpecialRecord(CheckSpecialRecord checkSpecialRecord){
        boolean result = iCheckSpecialRecordService.updateById(checkSpecialRecord);
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
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSpecialRecord(String id){
        boolean result = iCheckSpecialRecordService.removeById(id);
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
    @RequestMapping(value = "/checkSpecialRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSpecialRecordById(String id){
        CheckSpecialRecord checkSpecialRecord = iCheckSpecialRecordService.getById(id);
        if(checkSpecialRecord!=null){
            return renderSuccess("查询成功",checkSpecialRecord);
        }else {
            return renderSuccess("无数据");
        }
    }
}
