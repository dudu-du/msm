package com.safety.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safety.entity.RiskControl;
import com.safety.service.IRiskControlService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;

/**
 * <p>
 * 分级管控 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskControl")
public class RiskControlController extends BaseController {
    @Autowired
    private IRiskControlService iRiskControlService;
    
    @RequestMapping(value = "/riskControlView",method = RequestMethod.GET)
    public BaseModelAndView getIdentificationList(){
    	
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/control");
        return modelAndView;
    }
    /**
     * 添加
     * @param riskControl
     * @return
     */
    @RequestMapping(value = "/riskControl",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskControl(@RequestBody RiskControl riskControl){
        String id = UUIDUtil.getUUID();
        riskControl.setId(id);
        boolean result = iRiskControlService.save(riskControl);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskControl
     * @return
     */
    @RequestMapping(value = "/riskControl",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskControl(@RequestBody RiskControl riskControl){
        boolean result = iRiskControlService.updateById(riskControl);
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
    @RequestMapping(value = "/riskControl",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskControl(String id){
        boolean result = iRiskControlService.removeById(id);
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
    @RequestMapping(value = "/riskControlById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskControlById(String id){
        RiskControl riskControl = iRiskControlService.getById(id);
        if(riskControl!=null){
            return renderSuccess("查询成功",riskControl);
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
    @RequestMapping(value = "/riskControl",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskControlByParam(String orgId, String year){
        RiskControl riskControl = iRiskControlService.getByParam(orgId,year);
        if(riskControl!=null){
            return renderSuccess("查询成功",riskControl);
        }else {
            return renderError("无数据");
        }
    }
}
