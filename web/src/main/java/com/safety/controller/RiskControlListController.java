package com.safety.controller;


import com.safety.entity.RiskControlList;
import com.safety.service.IRiskControlListService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 * 安全风险分级管控列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskControlList")
public class RiskControlListController extends BaseController {
    @Autowired
    private IRiskControlListService iRiskControlListService;
    /**
     * 添加
     * @param riskControlList
     * @return
     */
    @RequestMapping(value = "/riskControlList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskControlList(@RequestBody RiskControlList riskControlList){
        String id = UUIDUtil.getUUID();
        riskControlList.setId(id);
        riskControlList.setCreateTime(LocalDateTime.now());
        boolean result = iRiskControlListService.save(riskControlList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskControlList
     * @return
     */
    @RequestMapping(value = "/riskControlList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskControlList(@RequestBody RiskControlList riskControlList){
        boolean result = iRiskControlListService.updateById(riskControlList);
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
    @RequestMapping(value = "/riskControlList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskControlList(String id){
        boolean result = iRiskControlListService.removeById(id);
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
    @RequestMapping(value = "/riskControlList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskControlListById(String id){
        RiskControlList riskControlList = iRiskControlListService.getById(id);
        if(riskControlList!=null){
            return renderSuccess("查询成功",riskControlList);
        }else {
            return renderError("无数据");
        }
    }
}
