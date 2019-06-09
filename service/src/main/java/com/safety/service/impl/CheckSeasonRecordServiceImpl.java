package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.mapper.*;
import com.safety.service.ICheckSeasonRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 综合季度排查记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckSeasonRecordServiceImpl extends ServiceImpl<CheckSeasonRecordMapper, CheckSeasonRecord> implements ICheckSeasonRecordService {

    @Autowired
    private CheckSeasonRecordMapper checkSeasonRecordMapper;
    @Autowired
    private CheckSeasonRecordListMapper checkSeasonRecordListMapper;
    @Autowired
    private CheckComprehensiveSeasonListMapper checkComprehensiveSeasonListMapper;
    @Autowired
    private CheckComprehensiveSeasonMapper checkComprehensiveSeasonMapper;
    @Autowired
    private CheckOffgradeListMapper checkOffgradeListMapper;
    @Autowired
    private CheckDangerChecklistMapper checkDangerChecklistMapper;
    @Autowired
    private CheckDangerLedgerMapper checkDangerLedgerMapper;
    @Autowired
    private CheckRectificationReceiptMapper checkRectificationReceiptMapper;
    @Autowired
    private RiskIdentificationListMapper riskIdentificationListMapper;

    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "综合检查(季节性)";

    @Override
    public boolean addCheckSeasonRecord(CheckSeasonRecord checkSeasonRecord) {
        List<CheckComprehensiveSeasonList> checkComprehensiveSeasonLists = checkSeasonRecord.getCheckComprehensiveSeasonList();
        checkSeasonRecord.setCheckComprehensiveSeasonList(null);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        checkSeasonRecord.setCheckContent(checkSeasonRecord.getCheckPersonName()+" 于 "+date+" 填写");
        checkSeasonRecord.setCreateTime(LocalDateTime.now());
        checkSeasonRecordMapper.insert(checkSeasonRecord);
        String checkSeasonRecordId = checkSeasonRecord.getId();
        //获取机构ID
        String orgId = checkSeasonRecord.getOrgFk();
        if (checkComprehensiveSeasonLists.size()>0){
            for (CheckComprehensiveSeasonList checkComprehensiveSeasonList:checkComprehensiveSeasonLists){
                //判断是否填写值
                if (checkComprehensiveSeasonList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_season_list_id",checkComprehensiveSeasonList.getId());
                map.put("check_season_record_id",checkSeasonRecordId);
                List<CheckSeasonRecordList> list = checkSeasonRecordListMapper.selectByMap(map);
                CheckSeasonRecordList checkSeasonRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkSeasonRecordList = list.get(0);
                    checkSeasonRecordListMapper.updateById(checkSeasonRecordList);
                }else {
                    checkSeasonRecordList = new CheckSeasonRecordList();
                    checkSeasonRecordList.setId(UUIDUtil.getUUID());
                    checkSeasonRecordList.setCheckSeasonListId(checkComprehensiveSeasonList.getId());
                    checkSeasonRecordList.setCheckSeasonRecordId(checkSeasonRecordId);
                    checkSeasonRecordList.setResult(checkComprehensiveSeasonList.getResult());
                    checkSeasonRecordList.setCreateTime(LocalDateTime.now());
                    checkSeasonRecordList.setOrgFk(orgId);
                    checkSeasonRecordListMapper.insert(checkSeasonRecordList);
                }
                String result = checkSeasonRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkComprehensiveSeasonList.getId());
                map1.put("check_fk",checkSeasonRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    String checkOffgradeListId = UUIDUtil.getUUID();
                    checkOffgradeList.setId(checkOffgradeListId);
                    checkOffgradeList.setContent(checkComprehensiveSeasonList.getContent());
                    checkOffgradeList.setCheckFk(checkSeasonRecordId);
                    checkOffgradeList.setCheckListFk(checkComprehensiveSeasonList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    //TODO:机构id需从条目列表获得 暂时用模板获取
                    checkOffgradeList.setOrgFk(orgId);
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkComprehensiveSeasonList.getLevelName());
                    checkOffgradeList.setCreateTime(LocalDateTime.now());
                    checkOffgradeListMapper.insert(checkOffgradeList);
                    //获取填写的清单 台账 回执单 并保存
                    CheckDangerChecklist checkDangerChecklist = checkComprehensiveSeasonList.getCheckDangerChecklist();
                    if (checkDangerChecklist!=null){
                        checkDangerChecklist.setId(UUIDUtil.getUUID());
                        checkDangerChecklist.setOffgradeListFk(checkOffgradeListId);
                        checkDangerChecklist.setCheckType(CHECK_TYPE);
                        checkDangerChecklist.setOrgFk(orgId);
                        checkDangerChecklistMapper.insert(checkDangerChecklist);
                    }
                    CheckDangerLedger checkDangerLedger = checkComprehensiveSeasonList.getCheckDangerLedger();
                    if (checkDangerLedger!=null){
                        checkDangerLedger.setId(UUIDUtil.getUUID());
                        checkDangerLedger.setOffgradeListFk(checkOffgradeListId);
                        checkDangerLedger.setCheckType(CHECK_TYPE);
                        checkDangerLedger.setOrgFk(orgId);
                        checkDangerLedgerMapper.insert(checkDangerLedger);
                    }
                    CheckRectificationReceipt checkRectificationReceipt = checkComprehensiveSeasonList.getCheckRectificationReceipt();
                    if (checkRectificationReceipt!=null){
                        checkRectificationReceipt.setId(UUIDUtil.getUUID());
                        checkRectificationReceipt.setRecordListFk(checkOffgradeListId);
                        checkRectificationReceipt.setCheckType(CHECK_TYPE);
                        checkRectificationReceipt.setOrgFk(orgId);
                        checkRectificationReceiptMapper.insert(checkRectificationReceipt);
                    }
                }else if (YES.equals(result)&&list1.size()>0){
                    //之前有值 且保存为是时 删掉旧的值
                    checkOffgradeListMapper.deleteById(list1.get(0).getId());
                }
            }
        }
        return true;
    }

    @Override
    public CheckSeasonRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
//        CheckSeasonRecord checkSeasonRecord = checkSeasonRecordMapper.selectByParam(param);
//        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
//        if (checkSeasonRecord!=null){
//            String checkSeasonId = checkSeasonRecord.getCheckSeasonId();
//            Map map = new HashMap();
//            map.put("checkComprehensiveSeasonFk",checkSeasonId);
//            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
//            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
//        }else {
            //先获取是否已经添加好模板
            CheckComprehensiveSeason checkComprehensiveSeason = checkComprehensiveSeasonMapper.selectByParam(param);
            if (checkComprehensiveSeason==null){
                return null;
            }
            String checkSeasonId = checkComprehensiveSeason.getId();
            CheckSeasonRecord checkSeasonRecord = new CheckSeasonRecord();
            checkSeasonRecord.setCheckSeasonId(checkSeasonId);
            checkSeasonRecord.setId(UUIDUtil.getUUID());
            checkSeasonRecord.setOrgFk(orgId);
//            checkSeasonRecord.setCreateTime(LocalDateTime.now());
//            checkSeasonRecordMapper.insert(checkSeasonRecord);
            Map map = new HashMap();
            map.put("checkComprehensiveSeasonFk",checkSeasonId);
            map.put("checkSeasonRecordId",checkSeasonRecord.getId());
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
            for (CheckComprehensiveSeasonList checkComprehensiveSeasonList:list){
                String riskIdentificationListId = checkComprehensiveSeasonList.getRiskIdentificationListId();
                RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
                checkComprehensiveSeasonList.setRiskIdentificationList(riskIdentificationList);
            }
        //默认未每条数据增加result为1
            for (CheckComprehensiveSeasonList checkComprehensiveSeasonList:list){
            if (checkComprehensiveSeasonList.getResult()==null || checkComprehensiveSeasonList.getResult().isEmpty()){
                checkComprehensiveSeasonList.setResult("1");
            }
        }
            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
//        }
        return checkSeasonRecord;
    }

    @Override
    public PageInfo<CheckSeasonRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckSeasonRecord> checkSeasonRecords = checkSeasonRecordMapper.selectAll();
        return new PageInfo<>(checkSeasonRecords);
    }

    @Override
    public CheckSeasonRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckSeasonRecord checkSeasonRecord = checkSeasonRecordMapper.selectByParam(param);
        if (checkSeasonRecord!=null){
            String checkSeasonId = checkSeasonRecord.getCheckSeasonId();
            Map map = new HashMap();
            map.put("checkComprehensiveSeasonFk",checkSeasonId);
            map.put("checkSeasonRecordId",checkSeasonRecord.getId());
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
        }
        return checkSeasonRecord;
    }
}
