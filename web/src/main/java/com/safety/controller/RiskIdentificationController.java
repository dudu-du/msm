package com.safety.controller;


import com.safety.entity.RiskIdentification;
import com.safety.service.IRiskIdentificationService;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
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
 * 安全风险辨识清单总表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/riskIdentification")
public class RiskIdentificationController extends BaseController {
    @Autowired
    private IRiskIdentificationService iRiskIdentificationService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/identificationView",method = RequestMethod.GET)
    public BaseModelAndView getIdentificationList(){
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("/mechanismAdmin");
        return modelAndView;
    }

    /**
     * 添加风险辨识清单
     * @param riskIdentification
     * @return
     */
    @RequestMapping(value = "/identification",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addIdentification(RiskIdentification riskIdentification){
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
    @RequestMapping(value = "/identification",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateIdentification(RiskIdentification riskIdentification){
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
    @RequestMapping(value = "/identification",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteIdentification(String id){
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
    @RequestMapping(value = "/identification",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getIdentificationById(String id){
        RiskIdentification riskIdentification = iRiskIdentificationService.getById(id);
        if(riskIdentification!=null){
            return renderSuccess("查询成功",riskIdentification);
        }else {
            return renderSuccess("无数据");
        }
    }
}
