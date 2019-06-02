package com.safety.service.impl;

import com.safety.entity.CheckDayRecordList;
import com.safety.entity.CheckRectificationReceipt;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.CheckDayRecordListMapper;
import com.safety.service.ICheckDayRecordListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录填写列表 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDayRecordListServiceImpl extends ServiceImpl<CheckDayRecordListMapper, CheckDayRecordList> implements ICheckDayRecordListService {
    @Override
    public List<Map<String,Object>> getChecklistResultCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return baseMapper.selectChecklistResultCountByOrg(param);
    }

    @Override
    public List<Map<String,Object>> getChecklistLevelCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return baseMapper.selectChecklistLevelCountByOrg(param);
    }

    @Override
    public List<Map<String,Object>> getLedgerResultCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return baseMapper.selectLedgerResultCountByOrg(param);
    }

    @Override
    public List<Map<String,Object>> getLedgerLevelCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return baseMapper.selectLedgerLevelCountByOrg(param);
    }

    @Override
    public List<CheckRectificationReceipt> getReceiptListByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception{
        if(StringUtils.isEmpty(orgId) || startTime == null || endTime == null){
            throw new ProgramException("参数错误");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgId",orgId);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        return baseMapper.selectReceiptByOrg(param);
    }
}
