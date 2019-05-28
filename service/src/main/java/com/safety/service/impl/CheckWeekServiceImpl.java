package com.safety.service.impl;

import com.safety.entity.CheckWeek;
import com.safety.entity.CheckWeekList;
import com.safety.mapper.CheckWeekListMapper;
import com.safety.mapper.CheckWeekMapper;
import com.safety.service.ICheckWeekService;
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
 * 周治理记录 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckWeekServiceImpl extends ServiceImpl<CheckWeekMapper, CheckWeek> implements ICheckWeekService {

    @Autowired
    private CheckWeekMapper checkWeekMapper;
    @Autowired
    private CheckWeekListMapper checkWeekListMapper;


    @Override
    public CheckWeek getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckWeek checkWeek = checkWeekMapper.selectByParam(param);
        if (checkWeek!=null){
            String id = checkWeek.getId();
            Map map = new HashMap();
            map.put("checkWeekFk",id);
            List<CheckWeekList> list = checkWeekListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            checkWeek.setCheckWeekList(list);
        }else {
            checkWeek = new CheckWeek();
            checkWeek.setId(UUIDUtil.getUUID());
            checkWeek.setOrgFk(orgId);
            checkWeek.setCreateTime(year);
            checkWeekMapper.insert(checkWeek);
            checkWeek.setCheckWeekList(new ArrayList<>());
        }
        return checkWeek;
    }

    @Override
    public boolean addCheckWeek(CheckWeek checkWeek) {
        List<CheckWeekList> checkWeekLists = checkWeek.getCheckWeekList();
        if (checkWeekLists.size()>0){
            for (CheckWeekList checkWeekList:checkWeekLists){
                checkWeekList.setId(UUIDUtil.getUUID());
                checkWeekList.setCreateTime(LocalDateTime.now());
                checkWeekListMapper.insert(checkWeekList);
            }
        }
        return true;
    }

    private void sortList(List<CheckWeekList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckWeekList checkWeekList = list.get(i);
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
