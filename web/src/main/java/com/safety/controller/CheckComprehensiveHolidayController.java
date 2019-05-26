package com.safety.controller;


import com.safety.entity.CheckComprehensiveHoliday;
import com.safety.service.ICheckComprehensiveHolidayService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 综合检查(节假日、复产前) 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkComprehensiveHoliday")
public class CheckComprehensiveHolidayController extends BaseController {

    @Autowired
    private ICheckComprehensiveHolidayService iCheckComprehensiveHolidayService;
    /**
     * 添加
     * @param checkComprehensiveHoliday
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckComprehensiveHoliday(@RequestBody CheckComprehensiveHoliday checkComprehensiveHoliday){
        boolean result = iCheckComprehensiveHolidayService.addCheckComprehensiveHoliday(checkComprehensiveHoliday);
        if (result){
            return renderSuccess("添加成功");
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param checkComprehensiveHoliday
     * @return
     */
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckComprehensiveHoliday(@RequestBody CheckComprehensiveHoliday checkComprehensiveHoliday){
        boolean result = iCheckComprehensiveHolidayService.updateById(checkComprehensiveHoliday);
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
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckComprehensiveHoliday(String id){
        boolean result = iCheckComprehensiveHolidayService.removeById(id);
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
    @RequestMapping(value = "/checkComprehensiveHolidayById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveHolidayById(String id){
        CheckComprehensiveHoliday checkComprehensiveHoliday = iCheckComprehensiveHolidayService.getById(id);
        if(checkComprehensiveHoliday!=null){
            return renderSuccess("查询成功",checkComprehensiveHoliday);
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
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveHolidayByParam(String orgId, String year){
        CheckComprehensiveHoliday checkComprehensiveHoliday = iCheckComprehensiveHolidayService.getByParam(orgId,year);
        if(checkComprehensiveHoliday!=null){
            return renderSuccess("查询成功",checkComprehensiveHoliday);
        }else {
            return renderError("无数据");
        }
    }
}
