package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.mapper.*;
import com.safety.service.ICheckHolidayRecordService;
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
 * 综合节假日排查记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckHolidayRecordServiceImpl extends ServiceImpl<CheckHolidayRecordMapper, CheckHolidayRecord> implements ICheckHolidayRecordService {

    @Autowired
    private CheckHolidayRecordMapper checkHolidayRecordMapper;
    @Autowired
    private CheckHolidayRecordListMapper checkHolidayRecordListMapper;
    @Autowired
    private CheckComprehensiveHolidayListMapper checkComprehensiveHolidayListMapper;
    @Autowired
    private CheckComprehensiveHolidayMapper checkComprehensiveHolidayMapper;
    @Autowired
    private CheckOffgradeListMapper checkOffgradeListMapper;

    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "综合检查(节假日、复产前)";

    @Override
    public boolean addCheckHolidayRecord(CheckHolidayRecord checkHolidayRecord) {
        List<CheckComprehensiveHolidayList> checkComprehensiveHolidayLists = checkHolidayRecord.getCheckComprehensiveHolidayList();
        checkHolidayRecord.setCheckComprehensiveHolidayList(null);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        checkHolidayRecord.setCheckContent(checkHolidayRecord.getCheckPersonName()+" 于 "+date+" 填写");
        checkHolidayRecord.setCreateTime(LocalDateTime.now());
        checkHolidayRecordMapper.insert(checkHolidayRecord);
        String checkHolidayRecordId = checkHolidayRecord.getId();
        if (checkComprehensiveHolidayLists.size()>0){
            for (CheckComprehensiveHolidayList checkComprehensiveHolidayList:checkComprehensiveHolidayLists){
                //判断是否填写值
                if (checkComprehensiveHolidayList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_holiday_list_id",checkComprehensiveHolidayList.getId());
                map.put("check_holiday_record_id",checkHolidayRecordId);
                List<CheckHolidayRecordList> list = checkHolidayRecordListMapper.selectByMap(map);
                CheckHolidayRecordList checkHolidayRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkHolidayRecordList = list.get(0);
                    checkHolidayRecordListMapper.updateById(checkHolidayRecordList);
                }else {
                    checkHolidayRecordList = new CheckHolidayRecordList();
                    checkHolidayRecordList.setId(UUIDUtil.getUUID());
                    checkHolidayRecordList.setCheckHolidayListId(checkComprehensiveHolidayList.getId());
                    checkHolidayRecordList.setCheckHolidayRecordId(checkHolidayRecordId);
                    checkHolidayRecordList.setResult(checkComprehensiveHolidayList.getResult());
                    checkHolidayRecordList.setCreateTime(LocalDateTime.now());
                    checkHolidayRecordListMapper.insert(checkHolidayRecordList);
                }
                String result = checkHolidayRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkComprehensiveHolidayList.getId());
                map1.put("check_fk",checkHolidayRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    checkOffgradeList.setId(UUIDUtil.getUUID());
                    checkOffgradeList.setContent(checkComprehensiveHolidayList.getContent());
                    checkOffgradeList.setCheckFk(checkHolidayRecordId);
                    checkOffgradeList.setCheckListFk(checkComprehensiveHolidayList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    //TODO:机构id需从条目列表获得 暂时用模板获取
                    checkOffgradeList.setOrgFk(checkHolidayRecord.getOrgFk());
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkComprehensiveHolidayList.getLevelName());
                    checkOffgradeList.setCreateTime(LocalDateTime.now());
                    checkOffgradeListMapper.insert(checkOffgradeList);
                    checkComprehensiveHolidayList.setCheckOffgradeList(checkOffgradeList);
                }else if (YES.equals(result)&&list1.size()>0){
                    //之前有值 且保存为是时 删掉旧的值
                    checkOffgradeListMapper.deleteById(list1.get(0).getId());
                }
            }
        }
        checkHolidayRecord.setCheckComprehensiveHolidayList(checkComprehensiveHolidayLists);
        return true;
    }

    @Override
    public CheckHolidayRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
//        CheckHolidayRecord checkHolidayRecord = checkHolidayRecordMapper.selectByParam(param);
//        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
//        if (checkHolidayRecord!=null){
//            String checkHolidayId = checkHolidayRecord.getCheckHolidayId();
//            Map map = new HashMap();
//            map.put("checkComprehensiveHolidayFk",checkHolidayId);
//            List<CheckComprehensiveHolidayList> list = checkComprehensiveHolidayListMapper.selectByParam(map);
//            checkHolidayRecord.setCheckComprehensiveHolidayList(list);
//        }else {
            //先获取是否已经添加好模板
            CheckComprehensiveHoliday checkComprehensiveHoliday = checkComprehensiveHolidayMapper.selectByParam(param);
            if (checkComprehensiveHoliday==null){
                return null;
            }
            String checkHolidayId = checkComprehensiveHoliday.getId();
            CheckHolidayRecord checkHolidayRecord = new CheckHolidayRecord();
            checkHolidayRecord.setCheckHolidayId(checkHolidayId);
            checkHolidayRecord.setId(UUIDUtil.getUUID());
            checkHolidayRecord.setOrgFk(orgId);
//            checkHolidayRecord.setCreateTime(LocalDateTime.now());
//            checkHolidayRecordMapper.insert(checkHolidayRecord);
            Map map = new HashMap();
            map.put("checkComprehensiveHolidayFk",checkHolidayId);
            map.put("checkHolidayRecordId",checkHolidayRecord.getId());
            List<CheckComprehensiveHolidayList> list = checkComprehensiveHolidayListMapper.selectByParam(map);
            //默认未每条数据增加result为1
            for (CheckComprehensiveHolidayList checkComprehensiveHolidayList:list){
                if (checkComprehensiveHolidayList.getResult()==null || checkComprehensiveHolidayList.getResult().isEmpty()){
                    checkComprehensiveHolidayList.setResult("1");
                }
            }
            checkHolidayRecord.setCheckComprehensiveHolidayList(list);
//        }
        return checkHolidayRecord;
    }

    @Override
    public PageInfo<CheckHolidayRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckHolidayRecord> checkHolidayRecords = checkHolidayRecordMapper.selectAll();
        return new PageInfo<>(checkHolidayRecords);
    }

    @Override
    public CheckHolidayRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckHolidayRecord checkHolidayRecord = checkHolidayRecordMapper.selectByParam(param);
        if (checkHolidayRecord!=null){
            String checkHolidayId = checkHolidayRecord.getCheckHolidayId();
            Map map = new HashMap();
            map.put("checkComprehensiveHolidayFk",checkHolidayId);
            map.put("checkHolidayRecordId",checkHolidayRecord.getId());
            List<CheckComprehensiveHolidayList> list = checkComprehensiveHolidayListMapper.selectByParam(map);
            checkHolidayRecord.setCheckComprehensiveHolidayList(list);
        }
        return checkHolidayRecord;
    }
}
