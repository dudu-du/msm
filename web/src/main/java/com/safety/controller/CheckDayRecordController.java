package com.safety.controller;


import com.safety.entity.CheckDayRecord;
import com.safety.exception.ProgramException;
import com.safety.service.ICheckDayRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
        String id = UUIDUtil.getUUID();
        checkDayRecord.setId(id);
        boolean result = iCheckDayRecordService.save(checkDayRecord);
        if (result){
            return renderSuccess("添加成功", id);
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
        try {
            CheckDayRecord checkDayRecord = iCheckDayRecordService.getCheckDayRecordListById(id);
            if(checkDayRecord!=null){
                return renderSuccess("查询成功",checkDayRecord);
            }else {
                return renderError("无数据");
            }
        }
        catch (ProgramException p) {
            log.error("获取日治理记录失败." + p.getMessage());
            return renderError(p.getMessage());
        } catch (Exception e) {
            log.error("获取日治理记录失败." + e.getMessage());
            return renderError("获取日治理记录失败");
        }
    }

    /**
     * 查询日治理列表
     * @return
     */
    @RequestMapping(value = "/checkDayRecord",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getCheckDayRecord(){
        List<CheckDayRecord> result = iCheckDayRecordService.getCheckDayRecord();
        return renderSuccess("查询成功",result);
    }
}
