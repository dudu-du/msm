package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.*;
import com.safety.mapper.*;
import com.safety.service.ICheckSpecialRecordService;
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
public class CheckSpecialRecordServiceImpl extends ServiceImpl<CheckSpecialRecordMapper, CheckSpecialRecord> implements ICheckSpecialRecordService {

    @Autowired
    private CheckSpecialRecordMapper checkSpecialRecordMapper;
    @Autowired
    private CheckSpecialRecordListMapper checkSpecialRecordListMapper;
    @Autowired
    private CheckSpecialListMapper checkSpecialListMapper;
    @Autowired
    private CheckSpecialMapper checkSpecialMapper;
    @Autowired
    private CheckOffgradeListMapper checkOffgradeListMapper;

    private final String YES = "1";
    private final String NO = "0";
    private final String CHECK_TYPE = "专项检查";

    @Override
    public CheckSpecialRecord getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
//        CheckSpecialRecord checkSpecialRecord = checkSpecialRecordMapper.selectByParam(param);
//        //TODO:需要增加判断该数据是否已经填写过 若填写过则直接获取已填数据
//        if (checkSpecialRecord!=null){
//            String checkSpecialId = checkSpecialRecord.getCheckSpecialId();
//            Map map = new HashMap();
//            map.put("checkSpecialFk",checkSpecialId);
//            List<CheckSpecialList> list = checkSpecialListMapper.selectByParam(map);
//            if (list.size()>0){
//                sortList(list);
//            }
//            checkSpecialRecord.setCheckSpecialList(list);
//        }else {
            //先获取是否已经添加好模板
            CheckSpecial checkSpecial = checkSpecialMapper.selectByParam(param);
            if (checkSpecial==null){
                return null;
            }
            String checkSpecialId = checkSpecial.getId();
            CheckSpecialRecord checkSpecialRecord = new CheckSpecialRecord();
            checkSpecialRecord.setCheckSpecialId(checkSpecialId);
            checkSpecialRecord.setId(UUIDUtil.getUUID());
            checkSpecialRecord.setOrgFk(orgId);
//            checkSpecialRecord.setCreateTime(LocalDateTime.now());
//            checkSpecialRecordMapper.insert(checkSpecialRecord);
            Map map = new HashMap();
            map.put("checkSpecialFk",checkSpecialId);
            List<CheckSpecialList> list = checkSpecialListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkSpecialRecord.setCheckSpecialList(list);
//        }
        return checkSpecialRecord;
    }

    @Override
    public boolean addCheckSpecialRecord(CheckSpecialRecord checkSpecialRecord) {
        List<CheckSpecialList> checkSpecialLists = checkSpecialRecord.getCheckSpecialList();
        checkSpecialRecord.setCheckSpecialList(null);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());
        checkSpecialRecord.setCheckContent(checkSpecialRecord.getCheckPersonName()+" 于 "+date+" 填写");
        checkSpecialRecord.setCreateTime(LocalDateTime.now());
        checkSpecialRecordMapper.insert(checkSpecialRecord);
        String checkSpecialRecordId = checkSpecialRecord.getId();
        if (checkSpecialLists.size()>0){
            for (CheckSpecialList checkSpecialList:checkSpecialLists){
                //判断是否填写值
                if (checkSpecialList.getResult()==null){
                    continue;
                }
                //先查询是否已填入值
                Map map = new HashMap();
                map.put("check_special_list_id",checkSpecialList.getId());
                map.put("check_special_record_id",checkSpecialRecordId);
                List<CheckSpecialRecordList> list = checkSpecialRecordListMapper.selectByMap(map);
                CheckSpecialRecordList checkSpecialRecordList;
                //若已经有值直接进行修改
                if (list.size()>0){
                    checkSpecialRecordList = list.get(0);
                    checkSpecialRecordListMapper.updateById(checkSpecialRecordList);
                }else {
                    checkSpecialRecordList = new CheckSpecialRecordList();
                    checkSpecialRecordList.setId(UUIDUtil.getUUID());
                    checkSpecialRecordList.setCheckSpecialListId(checkSpecialList.getId());
                    checkSpecialRecordList.setCheckSpecialRecordId(checkSpecialRecordId);
                    checkSpecialRecordList.setResult(checkSpecialList.getResult());
                    checkSpecialRecordList.setCreateTime(LocalDateTime.now());
                    checkSpecialRecordListMapper.insert(checkSpecialRecordList);
                }
                String result = checkSpecialRecordList.getResult();
                //查询是否已经添加
                Map map1 = new HashMap();
                map1.put("check_list_fk",checkSpecialList.getId());
                map1.put("check_fk",checkSpecialRecordId);
                List<CheckOffgradeList> list1 = checkOffgradeListMapper.selectByMap(map1);
                //之前没有值 且保存为否时 新增新值
                if (NO.equals(result)&&list1.size()<1){
                    CheckOffgradeList checkOffgradeList = new CheckOffgradeList();
                    checkOffgradeList.setId(UUIDUtil.getUUID());
                    checkOffgradeList.setContent(checkSpecialList.getCheckContent());
                    checkOffgradeList.setCheckFk(checkSpecialRecordId);
                    checkOffgradeList.setCheckListFk(checkSpecialList.getId());
                    checkOffgradeList.setCheckType(CHECK_TYPE);
                    checkOffgradeList.setState("0");
                    checkOffgradeList.setOrgFk(checkSpecialList.getOrgFk());
                    //TODO:此处只保存了安全风险等级名称
                    checkOffgradeList.setLevelName(checkSpecialList.getLevelName());
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
    public PageInfo<CheckSpecialRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckSpecialRecord> checkSpecialRecords = checkSpecialRecordMapper.selectAll();
        return new PageInfo<>(checkSpecialRecords);
    }

    @Override
    public CheckSpecialRecord getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        CheckSpecialRecord checkSpecialRecord = checkSpecialRecordMapper.selectByParam(param);
        if (checkSpecialRecord!=null){
            String checkSpecialId = checkSpecialRecord.getCheckSpecialId();
            Map map = new HashMap();
            map.put("checkSpecialFk",checkSpecialId);
            List<CheckSpecialList> list = checkSpecialListMapper.selectByParam(map);
            if (list.size()>0){
                sortList(list);
            }
            checkSpecialRecord.setCheckSpecialList(list);
        }
        return checkSpecialRecord;
    }

    private void sortList(List<CheckSpecialList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckSpecialList checkSpecialList = list.get(i);
            if (checkTypeName.equals(checkSpecialList.getCheckTypeName())){
                union++;
                CheckSpecialList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkSpecialList.getCheckTypeName();
                CheckSpecialList first = list.get(position);
                first.setUnion(union);
                checkSpecialList.setIndex(index);
                checkSpecialList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
