package com.safety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckOffgradeList;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.CheckOffgradeListMapper;
import com.safety.service.ICheckOffgradeListService;
import com.safety.tools.DictConstants;
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

    @Override
    public PageInfo<CheckOffgradeList> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckOffgradeList> checkMonthRecords = baseMapper.selectAll();
        return new PageInfo<>(checkMonthRecords);
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
