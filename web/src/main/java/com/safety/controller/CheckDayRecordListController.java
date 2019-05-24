package com.safety.controller;


import com.safety.entity.CheckDayRecordList;
import com.safety.service.ICheckDayRecordListService;
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
 * 日治理记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDayRecordList")
public class CheckDayRecordListController extends BaseController {
    @Autowired
    private ICheckDayRecordListService iCheckDayRecordListService;
    /**
     * 添加
     * @param checkDayRecordList
     * @return
     */
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayRecordList(CheckDayRecordList checkDayRecordList){
        String id = UUIDUtil.getUUID();
        checkDayRecordList.setId(id);
        boolean result = iCheckDayRecordListService.save(checkDayRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayRecordList
     * @return
     */
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayRecordList(CheckDayRecordList checkDayRecordList){
        boolean result = iCheckDayRecordListService.updateById(checkDayRecordList);
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
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayRecordList(String id){
        boolean result = iCheckDayRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkDayRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayRecordListById(String id){
        CheckDayRecordList checkDayRecordList = iCheckDayRecordListService.getById(id);
        if(checkDayRecordList!=null){
            return renderSuccess("查询成功",checkDayRecordList);
        }else {
            return renderSuccess("无数据");
        }
    }
}
