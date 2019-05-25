package com.safety.controller;


import com.safety.entity.CheckSeasonRecord;
import com.safety.service.ICheckSeasonRecordService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 综合季度排查记录填写 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkSeasonRecord")
public class CheckSeasonRecordController extends BaseController {
    @Autowired
    private ICheckSeasonRecordService iCheckSeasonRecordService;
    /**
     * 添加
     * @param checkSeasonRecord
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckSeasonRecord(@RequestBody CheckSeasonRecord checkSeasonRecord){
        String id = UUIDUtil.getUUID();
        checkSeasonRecord.setId(id);
        boolean result = iCheckSeasonRecordService.save(checkSeasonRecord);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkSeasonRecord
     * @return
     */
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckSeasonRecord(@RequestBody CheckSeasonRecord checkSeasonRecord){
        boolean result = iCheckSeasonRecordService.updateById(checkSeasonRecord);
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
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckSeasonRecord(String id){
        boolean result = iCheckSeasonRecordService.removeById(id);
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
    @RequestMapping(value = "/checkSeasonRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckSeasonRecordById(String id){
        CheckSeasonRecord checkSeasonRecord = iCheckSeasonRecordService.getById(id);
        if(checkSeasonRecord!=null){
            return renderSuccess("查询成功",checkSeasonRecord);
        }else {
            return renderError("无数据");
        }
    }
}
