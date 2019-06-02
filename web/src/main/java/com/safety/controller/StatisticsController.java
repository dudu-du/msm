package com.safety.controller;

import com.safety.entity.CheckRectificationReceipt;
import com.safety.exception.ProgramException;
import com.safety.service.*;
import com.safety.tools.BaseController;
import com.safety.tools.BaseModelAndView;
import com.safety.tools.DictConstants;
import com.safety.tools.JsonResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
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
    @Autowired
    private ICheckWeekRecordListService iCheckWeekRecordListService;
    @Autowired
    private ICheckMonthRecordListService iCheckMonthRecordListService;
    @Autowired
    private ICheckOffgradeListService iCheckOffgradeListService;

    @RequestMapping(value = "/checkDay",method = RequestMethod.GET)
    public BaseModelAndView getCheckDay(){
    	
        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("statistics/day");
        return modelAndView;
    }

    @RequestMapping(value = "/checkWeek",method = RequestMethod.GET)
    public BaseModelAndView getCheckWeek(){

        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("statistics/week");
        return modelAndView;
    }

    @RequestMapping(value = "/checkMonth",method = RequestMethod.GET)
    public BaseModelAndView getCheckMonth(){

        BaseModelAndView modelAndView = new BaseModelAndView();
        modelAndView.setViewName("statistics/month");
        return modelAndView;
    }

//    @RequestMapping(value = "/dayOffgrade",method = RequestMethod.GET)
//    public BaseModelAndView getDayOffgrade(){
//
//        BaseModelAndView modelAndView = new BaseModelAndView();
//        modelAndView.setViewName("statistics/dayOffgrade");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/weekOffgrade",method = RequestMethod.GET)
//    public BaseModelAndView getWeekOffgrade(){
//
//        BaseModelAndView modelAndView = new BaseModelAndView();
//        modelAndView.setViewName("statistics/weekOffgrade");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/monthOffgrade",method = RequestMethod.GET)
//    public BaseModelAndView getMonthOffgrade(){
//
//        BaseModelAndView modelAndView = new BaseModelAndView();
//        modelAndView.setViewName("statistics/monthOffgrade");
//        return modelAndView;
//    }
    
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
    /**
     * 获取日治理检查表中否的回执单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/dayReceiptList",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayReceiptList(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<CheckRectificationReceipt> result = iCheckDayRecordListService.getReceiptListByOrg(orgId,startTime,endTime);
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
     * 统计周检查表中否的清单个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekChecklistResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekChecklistResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckWeekRecordListService.getChecklistResultCountByOrg(orgId,startTime,endTime);
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
     * 统计周检查表中否的清单对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekCheckListLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekCheckListLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckWeekRecordListService.getChecklistLevelCountByOrg(orgId,startTime,endTime);
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
     * 统计周检查表中否的台账个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekLedgertResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekLedgerResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckWeekRecordListService.getLedgerResultCountByOrg(orgId,startTime,endTime);
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
     * 统计周检查表中否的台账对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekLedgerLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekLedgerLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckWeekRecordListService.getLedgerLevelCountByOrg(orgId,startTime,endTime);
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
     * 获取周检查表中否的回执单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/weekReceiptList",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekReceiptList(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<CheckRectificationReceipt> result = iCheckWeekRecordListService.getReceiptListByOrg(orgId,startTime,endTime);
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
     * 统计月检查表中否的清单个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthChecklistResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthChecklistResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckMonthRecordListService.getChecklistResultCountByOrg(orgId,startTime,endTime);
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
     * 统计月检查表中否的清单对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthCheckListLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthCheckListLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckMonthRecordListService.getChecklistLevelCountByOrg(orgId,startTime,endTime);
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
     * 统计月检查表中否的台账个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthLedgertResultCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthLedgerResultCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckMonthRecordListService.getLedgerResultCountByOrg(orgId,startTime,endTime);
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
     * 统计月检查表中否的台账对应等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthLedgerLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthLedgerLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<Map<String,Object>> result = iCheckMonthRecordListService.getLedgerLevelCountByOrg(orgId,startTime,endTime);
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
     * 获取月检查表中否的回执单列表
     * @param
     * @return
     */
    @RequestMapping(value = "/monthReceiptList",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthReceiptList(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            List<CheckRectificationReceipt> result = iCheckMonthRecordListService.getReceiptListByOrg(orgId,startTime,endTime);
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
     * 统计日检查表中否的等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayOffgradeLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayOffgradeLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeLevelCountByOrg(
                    orgId, DictConstants.CHECK_TYPE_DAY,startTime,endDateTime);
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
     * 统计日检查表中否的事故类型个数
     * @param
     * @return
     */
    @RequestMapping(value = "/dayOffgradeTroubleCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getDayOffgradeTroubleCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeTroubleCountByOrg(
                    orgId,DictConstants.CHECK_TYPE_DAY,startTime,endDateTime);
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
     * 统计周检查表中否的等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekOffgradeLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekOffgradeLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeLevelCountByOrg(
                    orgId, DictConstants.CHECK_TYPE_WEEK,startTime,endDateTime);
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
     * 统计周检查表中否的事故类型个数
     * @param
     * @return
     */
    @RequestMapping(value = "/weekOffgradeTroubleCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getWeekOffgradeTroubleCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDateTime endDateTime = LocalDateTime.of(endTime, LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeTroubleCountByOrg(
                    orgId,DictConstants.CHECK_TYPE_WEEK,startTime,endDateTime);
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
     * 统计月检查表中否的等级个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthOffgradeLevelCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthOffgradeLevelCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDate endTimeTemp = endTime.with(TemporalAdjusters.lastDayOfMonth());
            LocalDateTime endDateTime = LocalDateTime.of(endTimeTemp,LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeLevelCountByOrg(orgId, DictConstants.CHECK_TYPE_MONTH,startTime,endDateTime);
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
     * 统计月检查表中否的事故类型个数
     * @param
     * @return
     */
    @RequestMapping(value = "/monthOffgradeTroubleCount",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public JsonResult getMonthOffgradeTroubleCount(String orgId
            ,@RequestParam("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate startTime
            ,@RequestParam("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd" ) LocalDate endTime){
        try {
            LocalDate endTimeTemp = endTime.with(TemporalAdjusters.lastDayOfMonth());
            LocalDateTime endDateTime = LocalDateTime.of(endTimeTemp,LocalTime.MAX);
            List<Map<String,Object>> result = iCheckOffgradeListService.getOffgradeTroubleCountByOrg(orgId,DictConstants.CHECK_TYPE_MONTH,startTime,endDateTime);
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
