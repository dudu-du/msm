package com.safety.controller;


import com.safety.entity.CheckMonth;
import com.safety.service.ICheckMonthService;
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
 * 月治理记录 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/checkMonth")
public class CheckMonthController extends BaseController {
    @Autowired
    private ICheckMonthService iCheckMonthService;
    /**
     * 添加
     * @param checkMonth
     * @return
     */
    @RequestMapping(value = "/checkMonth",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addCheckMonth(CheckMonth checkMonth){
        String id = UUIDUtil.getUUID();
        checkMonth.setId(id);
        boolean result = iCheckMonthService.save(checkMonth);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param checkMonth
     * @return
     */
    @RequestMapping(value = "/checkMonth",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateCheckMonth(CheckMonth checkMonth){
        boolean result = iCheckMonthService.updateById(checkMonth);
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
    @RequestMapping(value = "/checkMonth",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteCheckMonth(String id){
        boolean result = iCheckMonthService.removeById(id);
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
    @RequestMapping(value = "/checkMonth",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getCheckMonthById(String id){
        CheckMonth checkMonth = iCheckMonthService.getById(id);
        if(checkMonth!=null){
            return renderSuccess("查询成功",checkMonth);
        }else {
            return renderSuccess("无数据");
        }
    }
}
