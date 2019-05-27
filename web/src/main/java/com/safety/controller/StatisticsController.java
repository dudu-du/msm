package com.safety.controller;


import com.safety.entity.RiskNoticeList;
import com.safety.service.IRiskIdentificationListService;
import com.safety.service.IRiskNoticeListService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单列表 前端控制器
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Controller
@RequestMapping("/safety/Statistics")
public class StatisticsController extends BaseController {
    @Autowired
    private IRiskIdentificationListService iRiskIdentificationListService;

    /**
     * 获取风险辨识安全登记数量
     * @param
     * @return
     */
    @RequestMapping(value = "/riskIdentificationCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getRiskIdentificationCount(String orgId){
        List<Map<String,Object>> result = iRiskIdentificationListService.getLevelCountByOrgId(orgId);
        if (!result.isEmpty()){
            return renderSuccess("获取成果", result);
        }else {
            return renderError("获取失败");
        }
    }


}
