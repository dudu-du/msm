package com.safety.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safety.entity.RiskIdentification;
import com.safety.service.IOrgService;
import com.safety.service.IRiskIdentificationListService;
import com.safety.service.IRiskIdentificationService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;

/**
 * <p>
 * 安全风险辨识清单总表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@Slf4j
@RequestMapping("/safety/riskIdentification")
public class RiskIdentificationController extends BaseController {
    @Autowired
    private IRiskIdentificationService iRiskIdentificationService;
    @Autowired
    private IRiskIdentificationListService iRiskIdentificationListService;
    @Autowired
    private IOrgService iOrgService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/riskIdentificationView",method = RequestMethod.GET)
    public BaseModelAndView getIdentificationList(){
    	
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("risk/incidentification");
        return modelAndView;
    }

    /**
     * 添加风险辨识清单
     * @param riskIdentification
     * @return
     */
    @RequestMapping(value = "/riskIdentification",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addRiskIdentification(RiskIdentification riskIdentification){
        String id = UUIDUtil.getUUID();
        riskIdentification.setId(id);
        boolean result = iRiskIdentificationService.save(riskIdentification);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改风险辨识清单
     * @param riskIdentification
     * @return
     */
    @RequestMapping(value = "/riskIdentification",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateRiskIdentification(RiskIdentification riskIdentification){
        boolean result = iRiskIdentificationService.updateById(riskIdentification);
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
    @RequestMapping(value = "/riskIdentification",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteRiskIdentification(String id){
        boolean result = iRiskIdentificationService.removeById(id);
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
    @RequestMapping(value = "/riskIdentification",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getRiskIdentificationById(String id){
        RiskIdentification riskIdentification = iRiskIdentificationService.getById(id);
        if(riskIdentification!=null){
            return renderSuccess("查询成功",riskIdentification);
        }else {
            return renderSuccess("无数据");
        }
    }
}
