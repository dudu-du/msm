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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "综合检查(季节性)";

    @Override
    public boolean addCheckSeasonRecord(CheckSeasonRecord checkSeasonRecord) {
        List<CheckComprehensiveSeasonList> checkComprehensiveSeasonLists = checkSeasonRecord.getCheckComprehensiveSeasonList();
        checkSeasonRecordMapper.updateById(checkSeasonRecord);
        String checkSeasonRecordId = checkSeasonRecord.getId();
        if (checkComprehensiveSeasonLists.size()>0){
            for (CheckComprehensiveSeasonList checkComprehensiveSeasonList:checkComprehensiveSeasonLists){
                //判断是否填写值
                if (checkComprehensiveSeasonList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_holiday_list_id",checkComprehensiveSeasonList.getId());
                map.put("check_holiday_record_id",checkSeasonRecordId);
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
                    checkOffgradeList.setId(UUIDUtil.getUUID());
                    checkOffgradeList.setCheckFk(checkSeasonRecordId);
                    checkOffgradeList.setCheckListFk(checkComprehensiveSeasonList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    checkOffgradeList.setOrgFk(checkComprehensiveSeasonList.getOrgFk());
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkComprehensiveSeasonList.getLevelName());
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
    public CheckSeasonRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckSeasonRecord checkSeasonRecord = checkSeasonRecordMapper.selectByParam(param);
        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
        if (checkSeasonRecord!=null){
            String checkSeasonId = checkSeasonRecord.getCheckSeasonId();
            Map map = new HashMap();
            map.put("checkComprehensiveSeasonFk",checkSeasonId);
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
        }else {
            //先获取是否已经添加好模板
            CheckComprehensiveSeason checkComprehensiveSeason = checkComprehensiveSeasonMapper.selectByParam(param);
            if (checkComprehensiveSeason==null){
                return null;
            }
            String checkSeasonId = checkComprehensiveSeason.getId();
            checkSeasonRecord = new CheckSeasonRecord();
            checkSeasonRecord.setCheckSeasonId(checkSeasonId);
            checkSeasonRecord.setId(UUIDUtil.getUUID());
            checkSeasonRecord.setOrgFk(orgId);
            checkSeasonRecord.setCreateTime(LocalDateTime.now());
            checkSeasonRecordMapper.insert(checkSeasonRecord);
            Map map = new HashMap();
            map.put("checkComprehensiveSeasonFk",checkSeasonId);
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
        }
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
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByParam(map);
            checkSeasonRecord.setCheckComprehensiveSeasonList(list);
        }
        return checkSeasonRecord;
    }
}
