package com.safety.controller;


import com.safety.entity.RiskEvaluation;
import com.safety.service.IRiskEvaluationService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
    
    @RequestMapping(value = "/riskEvaluationView",method = RequestMethod.GET)
    public BaseModelAndView getIdentificationList(){
    	
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/evaluation");
        return modelAndView;
    }
    /**
     * 添加
     * @param riskEvaluation
     * @return
     */
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskEvaluation(@RequestBody RiskEvaluation riskEvaluation){
        String id = UUIDUtil.getUUID();
        riskEvaluation.setId(id);
        boolean result = iRiskEvaluationService.save(riskEvaluation);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderError("添加失败");
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
    public JsonResult updateRiskEvaluation(@RequestBody RiskEvaluation riskEvaluation){
        boolean result = iRiskEvaluationService.updateById(riskEvaluation);
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
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskEvaluation(String id){
        boolean result = iRiskEvaluationService.removeById(id);
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
    @RequestMapping(value = "/riskEvaluation",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskEvaluationById(String id){
        RiskEvaluation riskEvaluation = iRiskEvaluationService.getById(id);
        if(riskEvaluation!=null){
            return renderSuccess("查询成功",riskEvaluation);
        }else {
            return renderError("无数据");
        }
    }
}
