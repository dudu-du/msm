package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.mapper.*;
import com.safety.service.ICheckMonthRecordService;
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
 * 月排查记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckMonthRecordServiceImpl extends ServiceImpl<CheckMonthRecordMapper, CheckMonthRecord> implements ICheckMonthRecordService {

    @Autowired
    private CheckMonthRecordMapper checkMonthRecordMapper;
    @Autowired
    private CheckMonthRecordListMapper checkMonthRecordListMapper;
    @Autowired
    private CheckMonthListMapper checkMonthListMapper;
    @Autowired
    private CheckMonthMapper checkMonthMapper;
    @Autowired
    private CheckOffgradeListMapper checkOffgradeListMapper;

    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "月排查记录";

    @Override
    public CheckMonthRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckMonthRecord checkMonthRecord = checkMonthRecordMapper.selectByParam(param);
        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
        if (checkMonthRecord!=null){
            String checkMonthId = checkMonthRecord.getCheckMonthId();
            Map map = new HashMap();
            map.put("checkMonthFk",checkMonthId);
            List<CheckMonthList> list = checkMonthListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkMonthRecord.setCheckMonthList(list);
        }else {
            //先获取是否已经添加好模板
            CheckMonth checkMonth = checkMonthMapper.selectByParam(param);
            if (checkMonth==null){
                return null;
            }
            String checkMonthId = checkMonth.getId();
            checkMonthRecord = new CheckMonthRecord();
            checkMonthRecord.setCheckMonthId(checkMonthId);
            checkMonthRecord.setId(UUIDUtil.getUUID());
            checkMonthRecord.setOrgFk(orgId);
            checkMonthRecord.setCreateTime(LocalDateTime.now());
            checkMonthRecordMapper.insert(checkMonthRecord);
            Map map = new HashMap();
            map.put("checkMonthFk",checkMonthId);
            List<CheckMonthList> list = checkMonthListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkMonthRecord.setCheckMonthList(list);
        }
        return checkMonthRecord;
    }

    @Override
    public boolean addCheckMonthRecord(CheckMonthRecord checkMonthRecord) {
        List<CheckMonthList> checkMonthLists = checkMonthRecord.getCheckMonthList();
        checkMonthRecordMapper.updateById(checkMonthRecord);
        String checkMonthRecordId = checkMonthRecord.getId();
        if (checkMonthLists.size()>0){
            for (CheckMonthList checkMonthList:checkMonthLists){
                //判断是否填写值
                if (checkMonthList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_month_list_id",checkMonthList.getId());
                map.put("check_month_record_id",checkMonthRecordId);
                List<CheckMonthRecordList> list = checkMonthRecordListMapper.selectByMap(map);
                CheckMonthRecordList checkMonthRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkMonthRecordList = list.get(0);
                    checkMonthRecordListMapper.updateById(checkMonthRecordList);
                }else {
                    checkMonthRecordList = new CheckMonthRecordList();
                    checkMonthRecordList.setId(UUIDUtil.getUUID());
                    checkMonthRecordList.setCheckMonthListId(checkMonthList.getId());
                    checkMonthRecordList.setCheckMonthRecordId(checkMonthRecordId);
                    checkMonthRecordList.setResult(checkMonthList.getResult());
                    checkMonthRecordList.setCreateTime(LocalDateTime.now());
                    checkMonthRecordListMapper.insert(checkMonthRecordList);
                }
                String result = checkMonthRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkMonthList.getId());
                map1.put("check_fk",checkMonthRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    checkOffgradeList.setId(UUIDUtil.getUUID());
                    checkOffgradeList.setCheckFk(checkMonthRecordId);
                    checkOffgradeList.setCheckListFk(checkMonthList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    checkOffgradeList.setOrgFk(checkMonthList.getOrgFk());
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkMonthList.getLevelName());
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
    public PageInfo<CheckMonthRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckMonthRecord> checkMonthRecords = checkMonthRecordMapper.selectAll();
        return new PageInfo<>(checkMonthRecords);
    }

    @Override
    public CheckMonthRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckMonthRecord checkMonthRecord = checkMonthRecordMapper.selectByParam(param);
        if (checkMonthRecord!=null){
            String checkMonthId = checkMonthRecord.getCheckMonthId();
            Map map = new HashMap();
            map.put("checkMonthFk",checkMonthId);
            List<CheckMonthList> list = checkMonthListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkMonthRecord.setCheckMonthList(list);
        }
        return checkMonthRecord;
    }

    private void sortList(List<CheckMonthList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckMonthList checkMonthList = list.get(i);
            if (checkTypeName.equals(checkMonthList.getCheckTypeName())){
                union++;
                CheckMonthList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkMonthList.getCheckTypeName();
                CheckMonthList first = list.get(position);
                first.setUnion(union);
                checkMonthList.setIndex(index);
                checkMonthList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
