package com.safety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.*;
import com.safety.service.ICheckOffgradeListService;
import com.safety.tools.DictConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 日治理记录 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckOffgradeListImpl extends ServiceImpl<CheckOffgradeListMapper, CheckOffgradeList> implements ICheckOffgradeListService {

    @Autowired
    private CheckDayListMapper checkDayListMapper;
    @Autowired
    private CheckWeekListMapper checkWeekListMapper;
    @Autowired
    private CheckMonthListMapper checkMonthListMapper;
    @Autowired
    private CheckSpecialListMapper checkSpecialListMapper;
    @Autowired
    private CheckComprehensiveHolidayListMapper checkComprehensiveHolidayListMapper;
    @Autowired
    private CheckComprehensiveSeasonListMapper checkComprehensiveSeasonListMapper;
    @Autowired
    private RiskIdentificationListMapper riskIdentificationListMapper;

    private final String DAY_TYPE = "日治理记录";
    private final String WEEK_TYPE = "周排查记录";
    private final String MONTH_TYPE = "月排查记录";
    private final String SPECIAL_TYPE = "专项检查";
    private final String HOLIDAY_TYPE = "综合检查(节假日、复产前)";
    private final String SEASON_TYPE = "综合检查(季节性)";

    @Override
    public PageInfo<CheckOffgradeList> getByPage(Integer currentPage, Integer pageSize, String orgId) {
        PageHelper.startPage(currentPage, pageSize);
        Map map = new HashMap();
        map.put("orgFk",orgId);
        List<CheckOffgradeList> CheckOffgradeLists = baseMapper.selectByParam(map);
        for (CheckOffgradeList checkOffgradeList:CheckOffgradeLists){
            String checkType = checkOffgradeList.getCheckType();
            String checkListFk = checkOffgradeList.getCheckListFk();
            switch (checkType){
                case DAY_TYPE:{
                    CheckDayList checkDayList = checkDayListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkDayList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                case WEEK_TYPE:{
                    CheckWeekList checkWeekList = checkWeekListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkWeekList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                case MONTH_TYPE:{
                    CheckMonthList checkMonthList = checkMonthListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkMonthList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                case SPECIAL_TYPE:{
                    CheckSpecialList checkSpecialList = checkSpecialListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkSpecialList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                case HOLIDAY_TYPE:{
                    CheckComprehensiveHolidayList checkComprehensiveHolidayList = checkComprehensiveHolidayListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkComprehensiveHolidayList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                case SEASON_TYPE:{
                    CheckComprehensiveSeasonList checkComprehensiveSeasonList = checkComprehensiveSeasonListMapper.selectById(checkListFk);
                    String riskIdentificationListId = checkComprehensiveSeasonList.getRiskIdentificationListId();
                    RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                    checkOffgradeList.setRiskIdentificationList(riskIdentificationList);
                    break;
                }
                default:{
                    checkOffgradeList.setRiskIdentificationList(null);
                }
            }
        }
        return new PageInfo<>(CheckOffgradeLists);
    }

    @Override
    public List<Map<String,Object>> getOffgradeTroubleCountByOrg(String orgId, String checkType,
                                     LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("checkType",checkType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        switch (checkType){
            case DictConstants.CHECK_TYPE_DAY:
                result = baseMapper.selectDayOffgradeTroubleCountByOrg(param);
                break;
            case DictConstants.CHECK_TYPE_WEEK:
                result = baseMapper.selectWeekOffgradeTroubleCountByOrg(param);
                break;
            case DictConstants.CHECK_TYPE_MONTH:
                result = baseMapper.selectMonthOffgradeTroubleCountByOrg(param);
                break;
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> getOffgradeLevelCountByOrg(String orgId, String checkType,
                                   LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("checkType",checkType);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        switch (checkType){
            case DictConstants.CHECK_TYPE_DAY:
                result = baseMapper.selectDayOffgradeLevelCountByOrg(param);
                break;
            case DictConstants.CHECK_TYPE_WEEK:
                result = baseMapper.selectWeekOffgradeLevelCountByOrg(param);
                break;
            case DictConstants.CHECK_TYPE_MONTH:
                result = baseMapper.selectMonthOffgradeLevelCountByOrg(param);
                break;
        }
        return result;
    }
}
