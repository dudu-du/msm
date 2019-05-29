package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.*;
import com.safety.service.ICheckDayRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDayRecordServiceImpl extends ServiceImpl<CheckDayRecordMapper, CheckDayRecord> implements ICheckDayRecordService {

    @Autowired
    private CheckDayRecordMapper checkDayRecordMapper;
    @Autowired
    private CheckDayRecordListMapper checkDayRecordListMapper;
    @Autowired
    private CheckOffgradeListMapper checkOffgradeListMapper;
    @Autowired
    private CheckDayListMapper checkDayListMapper;
    @Autowired
    private CheckDayMapper checkDayMapper;


    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "日治理记录";


    @Override
    public PageInfo<CheckDayRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckDayRecord> checkMonthRecords = baseMapper.selectAll();
        return new PageInfo<>(checkMonthRecords);
    }

    @Override
    public boolean addCheckDayRecord(CheckDayRecord checkDayRecord) {
        List<CheckDayList> checkDayLists = checkDayRecord.getCheckDayList();
        checkDayRecordMapper.updateById(checkDayRecord);
        String checkDayRecordId = checkDayRecord.getId();
        if (checkDayLists.size()>0){
            for (CheckDayList checkDayList:checkDayLists){
                //判断是否填写值
                if (checkDayList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_day_list_id",checkDayList.getId());
                map.put("check_day_record_id",checkDayRecordId);
                List<CheckDayRecordList> list = checkDayRecordListMapper.selectByMap(map);
                CheckDayRecordList checkDayRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkDayRecordList = list.get(0);
                    checkDayRecordListMapper.updateById(checkDayRecordList);
                }else {
                    checkDayRecordList = new CheckDayRecordList();
                    checkDayRecordList.setId(UUIDUtil.getUUID());
                    checkDayRecordList.setCheckDayListId(checkDayList.getId());
                    checkDayRecordList.setCheckDayRecordId(checkDayRecordId);
                    checkDayRecordList.setResult(checkDayList.getResult());
                    checkDayRecordList.setCreateTime(LocalDateTime.now());
                    checkDayRecordListMapper.insert(checkDayRecordList);
                }
                String result = checkDayRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkDayList.getId());
                map1.put("check_fk",checkDayRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    checkOffgradeList.setId(UUIDUtil.getUUID());
                    checkOffgradeList.setCheckFk(checkDayRecordId);
                    checkOffgradeList.setCheckListFk(checkDayList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    checkOffgradeList.setOrgFk(checkDayList.getOrgFk());
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkDayList.getLevelName());
                    checkOffgradeList.setCreateTime(LocalDateTime.now());
                    checkOffgradeListMapper.insert(checkOffgradeList);
                }else if (YES.equals(result)&&list1.size()>0){
                    //之前有值 且保存为是时 删掉旧的值
                    checkOffgradeListMapper.deleteById(list1.get(0).getId());
                }
            }
        }
        return true;
    }

    @Override
    public CheckDayRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckDayRecord checkDayRecord = checkDayRecordMapper.selectByParam(param);
        if (checkDayRecord!=null){
            String checkDayId = checkDayRecord.getCheckDayId();
            Map map = new HashMap();
            map.put("checkDayFk",checkDayId);
            List<CheckDayList> list = checkDayListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkDayRecord.setCheckDayList(list);
        }
        return checkDayRecord;
    }

    @Override
    public CheckDayRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckDayRecord checkDayRecord = checkDayRecordMapper.selectByParam(param);
        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
        if (checkDayRecord!=null){
            String checkDayId = checkDayRecord.getCheckDayId();
            Map map = new HashMap();
            map.put("checkDayFk",checkDayId);
            List<CheckDayList> list = checkDayListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkDayRecord.setCheckDayList(list);
        }else {
            //先获取是否已经添加好模板
            CheckDay checkDay = checkDayMapper.selectByParam(param);
            if (checkDay==null){
                return null;
            }
            String checkDayId = checkDay.getId();
            checkDayRecord = new CheckDayRecord();
            checkDayRecord.setCheckDayId(checkDayId);
            checkDayRecord.setId(UUIDUtil.getUUID());
            checkDayRecord.setOrgFk(orgId);
            checkDayRecord.setCreateTime(LocalDateTime.now());
            checkDayRecordMapper.insert(checkDayRecord);
            checkDayRecord.setCheckDayList(new ArrayList<>());
        }
        return checkDayRecord;
    }

    private void sortList(List<CheckDayList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckDayList checkDayList = list.get(i);
            if (checkTypeName.equals(checkDayList.getCheckTypeName())){
                union++;
                CheckDayList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkDayList.getCheckTypeName();
                CheckDayList first = list.get(position);
                first.setUnion(union);
                checkDayList.setIndex(index);
                checkDayList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
