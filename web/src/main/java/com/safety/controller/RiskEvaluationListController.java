package com.safety.controller;


import com.safety.entity.RiskEvaluationList;
import com.safety.service.IRiskEvaluationListService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * <p>
 * 安全风险动态评估列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskEvaluationList")
public class RiskEvaluationListController extends BaseController {
    @Autowired
    private IRiskEvaluationListService iRiskEvaluationListService;
    /**
     * 添加
     * @param riskEvaluationList
     * @return
     */
    @RequestMapping(value = "/riskEvaluationList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskEvaluationList(@RequestBody RiskEvaluationList riskEvaluationList){
        String id = UUIDUtil.getUUID();
        riskEvaluationList.setId(id);
        riskEvaluationList.setCreateTime(LocalDateTime.now());
        boolean result = iRiskEvaluationListService.save(riskEvaluationList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
        }
    }

    /**
     * 修改
     * @param riskEvaluationList
     * @return
     */
    @RequestMapping(value = "/riskEvaluationList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskEvaluationList(@RequestBody RiskEvaluationList riskEvaluationList){
        boolean result = iRiskEvaluationListService.updateById(riskEvaluationList);
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
    @RequestMapping(value = "/riskEvaluationList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskEvaluationList(String id){
        boolean result = iRiskEvaluationListService.removeById(id);
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
    @RequestMapping(value = "/riskEvaluationList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskEvaluationListById(String id){
        RiskEvaluationList riskEvaluationList = iRiskEvaluationListService.getById(id);
        if(riskEvaluationList!=null){
            return renderSuccess("查询成功",riskEvaluationList);
        }else {
            return renderError("无数据");
        }
    }
}
