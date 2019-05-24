package com.safety.controller;


import com.safety.entity.RiskNotice;
import com.safety.service.IRiskNoticeService;
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
    /**
     * 添加
     * @param riskNotice
     * @return
     */
    @RequestMapping(value = "/riskNotice",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskNotice(RiskNotice riskNotice){
        String id = UUIDUtil.getUUID();
        riskNotice.setId(id);
        boolean result = iRiskNoticeService.save(riskNotice);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
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
    public JsonResult updateRiskNotice(RiskNotice riskNotice){
        boolean result = iRiskNoticeService.updateById(riskNotice);
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
    @RequestMapping(value = "/riskNotice",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskNotice(String id){
        boolean result = iRiskNoticeService.removeById(id);
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
    @RequestMapping(value = "/riskNotice",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskNoticeById(String id){
        RiskNotice riskNotice = iRiskNoticeService.getById(id);
        if(riskNotice!=null){
            return renderSuccess("查询成功",riskNotice);
        }else {
            return renderSuccess("无数据");
        }
    }
}
