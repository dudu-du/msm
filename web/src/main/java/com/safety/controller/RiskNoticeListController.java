package com.safety.controller;


import com.safety.entity.RiskNoticeList;
import com.safety.service.IRiskNoticeListService;
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
 * 安全风险辨识清单列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskNoticeList")
public class RiskNoticeListController extends BaseController {
    @Autowired
    private IRiskNoticeListService iRiskNoticeListService;
    /**
     * 添加
     * @param riskNoticeList
     * @return
     */
    @RequestMapping(value = "/riskNoticeList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskNoticeList(RiskNoticeList riskNoticeList){
        String id = UUIDUtil.getUUID();
        riskNoticeList.setId(id);
        boolean result = iRiskNoticeListService.save(riskNoticeList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskNoticeList
     * @return
     */
    @RequestMapping(value = "/riskNoticeList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskNoticeList(RiskNoticeList riskNoticeList){
        boolean result = iRiskNoticeListService.updateById(riskNoticeList);
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
    @RequestMapping(value = "/riskNoticeList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskNoticeList(String id){
        boolean result = iRiskNoticeListService.removeById(id);
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
    @RequestMapping(value = "/riskNoticeList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskNoticeListById(String id){
        RiskNoticeList riskNoticeList = iRiskNoticeListService.getById(id);
        if(riskNoticeList!=null){
            return renderSuccess("查询成功",riskNoticeList);
        }else {
            return renderError("无数据");
        }
    }
}
