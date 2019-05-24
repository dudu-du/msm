package com.safety.controller;


import com.safety.entity.RiskEvaluation;
import com.safety.service.IRiskEvaluationService;
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
 * 安全风险动态评估 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskEvaluation")
public class RiskEvaluationController extends BaseController {
    @Autowired
    private IRiskEvaluationService iRiskEvaluationService;
    /**
     * 添加
     * @param riskEvaluation
     * @return
     */
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskEvaluation(RiskEvaluation riskEvaluation){
        String id = UUIDUtil.getUUID();
        riskEvaluation.setId(id);
        boolean result = iRiskEvaluationService.save(riskEvaluation);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改
     * @param riskEvaluation
     * @return
     */
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskEvaluation(RiskEvaluation riskEvaluation){
        boolean result = iRiskEvaluationService.updateById(riskEvaluation);
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
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskEvaluation(String id){
        boolean result = iRiskEvaluationService.removeById(id);
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
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskEvaluationById(String id){
        RiskEvaluation riskEvaluation = iRiskEvaluationService.getById(id);
        if(riskEvaluation!=null){
            return renderSuccess("查询成功",riskEvaluation);
        }else {
            return renderSuccess("无数据");
        }
    }
}
