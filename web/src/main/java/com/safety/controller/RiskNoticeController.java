package com.safety.controller;


import com.safety.entity.RiskNotice;
import com.safety.service.IRiskNoticeService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 安全风险公告栏 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskNotice")
public class RiskNoticeController extends BaseController {
    @Autowired
    private IRiskNoticeService iRiskNoticeService;
    
    @RequestMapping(value = "/riskNoticeView",method = RequestMethod.GET)
    public BaseModelAndView getIdentificationList(){
    	
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/notice");
        return modelAndView;
    }
    /**
     * 添加
     * @param riskNotice
     * @return
     */
    @RequestMapping(value = "/riskNotice",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskNotice(@RequestBody RiskNotice riskNotice){
        String id = UUIDUtil.getUUID();
        riskNotice.setId(id);
        boolean result = iRiskNoticeService.save(riskNotice);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskNotice
     * @return
     */
    @RequestMapping(value = "/riskNotice",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskNotice(@RequestBody RiskNotice riskNotice){
        boolean result = iRiskNoticeService.updateById(riskNotice);
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
    @RequestMapping(value = "/riskNotice",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskNotice(String id){
        boolean result = iRiskNoticeService.removeById(id);
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
    @RequestMapping(value = "/riskNoticeById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskNoticeById(String id){
        RiskNotice riskNotice = iRiskNoticeService.getById(id);
        if(riskNotice!=null){
            return renderSuccess("查询成功",riskNotice);
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
    @RequestMapping(value = "/riskNotice",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskControlByParam(String orgId, String year){
    	RiskNotice riskNotice = iRiskNoticeService.getByParam(orgId,year);
        if(riskNotice!=null){
            return renderSuccess("查询成功",riskNotice);
        }else {
            return renderError("无数据");
        }
    }

    /**
     * 打印页面
     * @return
     */
    @RequestMapping(value = "/riskNoticePrint",method = RequestMethod.GET)
    public BaseModelAndView getRiskNoticePrint(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/noticePrint");
        return modelAndView;
    }
}
