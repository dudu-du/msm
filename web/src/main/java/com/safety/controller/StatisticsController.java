package com.safety.controller;

import com.safety.exception.ProgramException;
import com.safety.service.ICheckDayRecordListService;
import com.safety.service.IRiskIdentificationListService;
import com.safety.tools.BaseController;
import com.safety.tools.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
@Slf4j
public class StatisticsController extends BaseController {
    @Autowired
    private IRiskIdentificationListService iRiskIdentificationListService;
    @Autowired
    private ICheckDayRecordListService iCheckDayRecordListService;

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
            return renderSuccess("获取成功", result);
        }else {
            return renderError("获取失败");
        }
    }

    /**
     * 统计日治理检查表中否的清单个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayChecklistResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayChecklistResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckDayRecordListService.getChecklistResultCountByOrg(orgId,startTime,endTime);
            return renderSuccess("获取成功", result);
        }
        catch (ProgramException e){
            log.error("获取失败",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("获取失败",e);
            return renderError("获取失败");
        }
    }

    /**
     * 统计日治理检查表中否的清单对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayCheckListLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayCheckListLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckDayRecordListService.getChecklistLevelCountByOrg(orgId,startTime,endTime);
            return renderSuccess("获取成功", result);
        }
        catch (ProgramException e){
            log.error("获取失败",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("获取失败",e);
            return renderError("获取失败");
        }
    }

    /**
     * 统计日治理检查表中否的台账个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayLedgertResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayLedgerResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckDayRecordListService.getLedgerResultCountByOrg(orgId,startTime,endTime);
            return renderSuccess("获取成功", result);
        }
        catch (ProgramException e){
            log.error("获取失败",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("获取失败",e);
            return renderError("获取失败");
        }
    }

    /**
     * 统计日治理检查表中否的台账对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayLedgerLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayLedgerLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckDayRecordListService.getLedgerLevelCountByOrg(orgId,startTime,endTime);
            return renderSuccess("获取成功", result);
        }
        catch (ProgramException e){
            log.error("获取失败",e);
            return renderError(e.getMessage());
        }catch (Exception e) {
            log.error("获取失败",e);
            return renderError("获取失败");
        }
    }
}
