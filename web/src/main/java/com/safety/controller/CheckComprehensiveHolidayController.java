package com.safety.controller;


import com.safety.entity.CheckComprehensiveHoliday;
import com.safety.service.ICheckComprehensiveHolidayService;
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
    public JsonResult addCheckComprehensiveHoliday(CheckComprehensiveHoliday checkComprehensiveHoliday){
        String id = UUIDUtil.getUUID();
        checkComprehensiveHoliday.setId(id);
        boolean result = iCheckComprehensiveHolidayService.save(checkComprehensiveHoliday);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateCheckComprehensiveHoliday(CheckComprehensiveHoliday checkComprehensiveHoliday){
        boolean result = iCheckComprehensiveHolidayService.updateById(checkComprehensiveHoliday);
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
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckComprehensiveHoliday(String id){
        boolean result = iCheckComprehensiveHolidayService.removeById(id);
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
    @RequestMapping(value = "/checkComprehensiveHoliday",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckComprehensiveHolidayById(String id){
        CheckComprehensiveHoliday checkComprehensiveHoliday = iCheckComprehensiveHolidayService.getById(id);
        if(checkComprehensiveHoliday!=null){
            return renderSuccess("查询成功",checkComprehensiveHoliday);
        }else {
            return renderSuccess("无数据");
        }
    }
}
