package com.safety.controller;


import com.safety.entity.CheckHolidayRecordList;
import com.safety.service.ICheckHolidayRecordListService;
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
 * 综合节假日排查记录填写列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkHolidayRecordList")
public class CheckHolidayRecordListController extends BaseController {
    @Autowired
    private ICheckHolidayRecordListService iCheckHolidayRecordListService;
    /**
     * 添加
     * @param checkHolidayRecordList
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecordList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckHolidayRecordList(CheckHolidayRecordList checkHolidayRecordList){
        String id = UUIDUtil.getUUID();
        checkHolidayRecordList.setId(id);
        boolean result = iCheckHolidayRecordListService.save(checkHolidayRecordList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkHolidayRecordList
     * @return
     */
    @RequestMapping(value = "/checkHolidayRecordList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckHolidayRecordList(CheckHolidayRecordList checkHolidayRecordList){
        boolean result = iCheckHolidayRecordListService.updateById(checkHolidayRecordList);
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
    @RequestMapping(value = "/checkHolidayRecordList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckHolidayRecordList(String id){
        boolean result = iCheckHolidayRecordListService.removeById(id);
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
    @RequestMapping(value = "/checkHolidayRecordList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckHolidayRecordListById(String id){
        CheckHolidayRecordList checkHolidayRecordList = iCheckHolidayRecordListService.getById(id);
        if(checkHolidayRecordList!=null){
            return renderSuccess("查询成功",checkHolidayRecordList);
        }else {
            return renderSuccess("无数据");
        }
    }
}
