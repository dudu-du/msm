package com.safety.controller;


import com.safety.entity.CheckDayList;
import com.safety.service.ICheckDayListService;
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
 * 日治理记录列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkDayList")
public class CheckDayListController extends BaseController {
    @Autowired
    private ICheckDayListService iCheckDayListService;
    /**
     * 添加
     * @param checkDayList
     * @return
     */
    @RequestMapping(value = "/checkDayList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckDayList(CheckDayList checkDayList){
        String id = UUIDUtil.getUUID();
        checkDayList.setId(id);
        boolean result = iCheckDayListService.save(checkDayList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkDayList
     * @return
     */
    @RequestMapping(value = "/checkDayList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckDayList(CheckDayList checkDayList){
        boolean result = iCheckDayListService.updateById(checkDayList);
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
    @RequestMapping(value = "/checkDayList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckDayList(String id){
        boolean result = iCheckDayListService.removeById(id);
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
    @RequestMapping(value = "/checkDayList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckDayListById(String id){
        CheckDayList checkDayList = iCheckDayListService.getById(id);
        if(checkDayList!=null){
            return renderSuccess("查询成功",checkDayList);
        }else {
            return renderSuccess("无数据");
        }
    }
}
