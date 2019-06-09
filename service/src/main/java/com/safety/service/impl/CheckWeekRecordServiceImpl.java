package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.mapper.*;
import com.safety.service.ICheckWeekRecordService;
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
 * 月排查记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckWeekRecordServiceImpl extends ServiceImpl<CheckWeekRecordMapper, CheckWeekRecord> implements ICheckWeekRecordService {

    @Autowired
    private CheckWeekRecordMapper checkWeekRecordMapper;
    @Autowired
    private CheckWeekRecordListMapper checkWeekRecordListMapper;
    @Autowired
    private CheckWeekListMapper checkWeekListMapper;
    @Autowired
    private CheckWeekMapper checkWeekMapper;
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
    private final String CHECK_TYPE = "周排查记录";
    
    @Override
    public boolean addCheckWeekRecord(CheckWeekRecord checkWeekRecord) {
        List<CheckWeekList> checkWeekLists = checkWeekRecord.getCheckWeekList();
        checkWeekRecord.setCheckWeekList(null);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        checkWeekRecord.setCheckContent(checkWeekRecord.getCheckPersonName()+" 于 "+date+" 填写");
        checkWeekRecord.setCreateTime(LocalDateTime.now());
        checkWeekRecordMapper.insert(checkWeekRecord);
        String checkWeekRecordId = checkWeekRecord.getId();
        //获取机构ID
        String orgId = checkWeekRecord.getOrgFk();
        if (checkWeekLists.size()>0){
            for (CheckWeekList checkWeekList:checkWeekLists){
                //判断是否填写值
                if (checkWeekList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_week_list_id",checkWeekList.getId());
                map.put("check_week_record_id",checkWeekRecordId);
                List<CheckWeekRecordList> list = checkWeekRecordListMapper.selectByMap(map);
                CheckWeekRecordList checkWeekRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkWeekRecordList = list.get(0);
                    checkWeekRecordListMapper.updateById(checkWeekRecordList);
                }else {
                    checkWeekRecordList = new CheckWeekRecordList();
                    checkWeekRecordList.setId(UUIDUtil.getUUID());
                    checkWeekRecordList.setCheckWeekListId(checkWeekList.getId());
                    checkWeekRecordList.setCheckWeekRecordId(checkWeekRecordId);
                    checkWeekRecordList.setResult(checkWeekList.getResult());
                    checkWeekRecordList.setCreateTime(LocalDateTime.now());
                    checkWeekRecordList.setOrgFk(orgId);
                    checkWeekRecordListMapper.insert(checkWeekRecordList);
                }
                String result = checkWeekRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkWeekList.getId());
                map1.put("check_fk",checkWeekRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    String checkOffgradeListId = UUIDUtil.getUUID();
                    checkOffgradeList.setId(checkOffgradeListId);
                    checkOffgradeList.setContent(checkWeekList.getCheckContent());
                    checkOffgradeList.setCheckFk(checkWeekRecordId);
                    checkOffgradeList.setCheckListFk(checkWeekList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    //TODO:机构id需从条目列表获得 暂时用模板获取
                    checkOffgradeList.setOrgFk(orgId);
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkWeekList.getLevelName());
                    checkOffgradeList.setCreateTime(LocalDateTime.now());
                    checkOffgradeListMapper.insert(checkOffgradeList);
                    //获取填写的清单 台账 回执单 并保存
                    CheckDangerChecklist checkDangerChecklist = checkWeekList.getCheckDangerChecklist();
                    if (checkDangerChecklist!=null){
                        checkDangerChecklist.setId(UUIDUtil.getUUID());
                        checkDangerChecklist.setOffgradeListFk(checkOffgradeListId);
                        checkDangerChecklist.setCheckType(CHECK_TYPE);
                        checkDangerChecklist.setOrgFk(orgId);
                        checkDangerChecklistMapper.insert(checkDangerChecklist);
                    }
                    CheckDangerLedger checkDangerLedger = checkWeekList.getCheckDangerLedger();
                    if (checkDangerLedger!=null){
                        checkDangerLedger.setId(UUIDUtil.getUUID());
                        checkDangerLedger.setOffgradeListFk(checkOffgradeListId);
                        checkDangerLedger.setCheckType(CHECK_TYPE);
                        checkDangerLedger.setOrgFk(orgId);
                        checkDangerLedgerMapper.insert(checkDangerLedger);
                    }
                    CheckRectificationReceipt checkRectificationReceipt = checkWeekList.getCheckRectificationReceipt();
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
    public CheckWeekRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
//        CheckWeekRecord checkWeekRecord = checkWeekRecordMapper.selectByParam(param);
//        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
//        if (checkWeekRecord!=null){
//            String checkWeekId = checkWeekRecord.getCheckWeekId();
//            Map map = new HashMap();
//            map.put("checkWeekFk",checkWeekId);
//            List<CheckWeekList> list = checkWeekListMapper.selectByParam(map);
//            if (list.size()>0){
//                sortList(list);
//            }
//            checkWeekRecord.setCheckWeekList(list);
//        }else {
            //先获取是否已经添加好模板
            CheckWeek checkWeek = checkWeekMapper.selectByParam(param);
            if (checkWeek==null){
                return null;
            }
            String checkWeekId = checkWeek.getId();
            CheckWeekRecord checkWeekRecord = new CheckWeekRecord();
            checkWeekRecord.setCheckWeekId(checkWeekId);
            checkWeekRecord.setId(UUIDUtil.getUUID());
            checkWeekRecord.setOrgFk(orgId);
//            checkWeekRecord.setCreateTime(LocalDateTime.now());
//            checkWeekRecordMapper.insert(checkWeekRecord);
            Map map = new HashMap();
            map.put("checkWeekFk",checkWeekId);
            map.put("checkWeekRecordId",checkWeekRecord.getId());
            List<CheckWeekList> list = checkWeekListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkWeekRecord.setCheckWeekList(list);
//        }
        return checkWeekRecord;
    }

    @Override
    public PageInfo<CheckWeekRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckWeekRecord> checkWeekRecords = checkWeekRecordMapper.selectAll();
        return new PageInfo<>(checkWeekRecords);
    }

    @Override
    public CheckWeekRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckWeekRecord checkWeekRecord = checkWeekRecordMapper.selectByParam(param);
        if (checkWeekRecord!=null){
            String checkWeekId = checkWeekRecord.getCheckWeekId();
            Map map = new HashMap();
            map.put("checkWeekFk",checkWeekId);
            map.put("checkWeekRecordId",checkWeekRecord.getId());
            List<CheckWeekList> list = checkWeekListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkWeekRecord.setCheckWeekList(list);
        }
        return checkWeekRecord;
    }

    private void sortList(List<CheckWeekList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckWeekList checkWeekList = list.get(i);
            String riskIdentificationListId = checkWeekList.getRiskIdentificationListId();
            RiskIdentificationList riskIdentificationList = riskIdentificationListMapper.selectById(riskIdentificationListId);
            checkWeekList.setRiskIdentificationList(riskIdentificationList);
            if (checkWeekList.getResult()==null || checkWeekList.getResult().isEmpty()){
                list.get(i).setResult(YES);
            }
            if (checkTypeName.equals(checkWeekList.getCheckTypeName())){
                union++;
                CheckWeekList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkWeekList.getCheckTypeName();
                CheckWeekList first = list.get(position);
                first.setUnion(union);
                checkWeekList.setIndex(index);
                checkWeekList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
