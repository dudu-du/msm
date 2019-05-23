package com.safety.controller;


import com.safety.entity.RiskIdentificationList;
import com.safety.service.IRiskIdentificationListService;
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
@RequestMapping("/safety/riskIdentificationList")
public class RiskIdentificationListController extends BaseController {

    @Autowired
    private IRiskIdentificationListService iRiskIdentificationListService;
    /**
     * 添加风险辨识清单
     * @param riskIdentificationList
     * @return
     */
    @RequestMapping(value = "/identificationList",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public JsonResult addIdentification(RiskIdentificationList riskIdentificationList){
        String id = UUIDUtil.getUUID();
        riskIdentificationList.setId(id);
        boolean result = iRiskIdentificationListService.save(riskIdentificationList);
        if (result){
            return renderSuccess("添加成功", id);
        }else {
            return renderSuccess("添加失败");
        }
    }

    /**
     * 修改风险辨识清单
     * @param riskIdentificationList
     * @return
     */
    @RequestMapping(value = "/identificationList",method = RequestMethod.PUT)
    @ResponseBody
    @CrossOrigin
    public JsonResult updateIdentification(RiskIdentificationList riskIdentificationList){
        boolean result = iRiskIdentificationListService.updateById(riskIdentificationList);
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
    @RequestMapping(value = "/identificationList",method = RequestMethod.DELETE)
    @ResponseBody
    @CrossOrigin
    public JsonResult deleteIdentification(String id){
        boolean result = iRiskIdentificationListService.removeById(id);
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
    @RequestMapping(value = "/identificationList",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getIdentificationById(String id){
        RiskIdentificationList riskIdentificationList = iRiskIdentificationListService.getById(id);
        if(riskIdentificationList!=null){
            return renderSuccess("查询成功",riskIdentificationList);
        }else {
            return renderSuccess("无数据");
        }
    }
}
