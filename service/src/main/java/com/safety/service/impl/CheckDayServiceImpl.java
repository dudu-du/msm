package com.safety.service.impl;

import com.safety.entity.CheckDay;
import com.safety.entity.CheckDayList;
import com.safety.entity.CheckMonthList;
import com.safety.mapper.CheckDayListMapper;
import com.safety.mapper.CheckDayMapper;
import com.safety.service.ICheckDayService;
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
 * 日治理记录 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDayServiceImpl extends ServiceImpl<CheckDayMapper, CheckDay> implements ICheckDayService {

    @Autowired
    private CheckDayMapper checkDayMapper;
    @Autowired
    private CheckDayListMapper checkDayListMapper;

    @Override
    public CheckDay getByParam(String orgId, String yearStr,String teamId) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckDay checkDay = checkDayMapper.selectByParam(param);
        if (checkDay!=null){
            String id = checkDay.getId();
            Map map = new HashMap();
            map.put("checkDayFk",id);
            map.put("checkTeamFk",teamId);
            List<CheckDayList> list = checkDayListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            checkDay.setCheckDayList(list);
        }else {
            checkDay = new CheckDay();
            checkDay.setId(UUIDUtil.getUUID());
            checkDay.setOrgFk(orgId);
            checkDay.setCreateTime(year);
            checkDayMapper.insert(checkDay);
            checkDay.setCheckDayList(new ArrayList<>());
        }
        return checkDay;
    }

    @Override
    public boolean addCheckDay(CheckDay checkDay) {
        List<CheckDayList> checkDayLists = checkDay.getCheckDayList();
        if (checkDayLists.size()>0){
            for (CheckDayList checkDayList:checkDayLists){
                checkDayList.setId(UUIDUtil.getUUID());
                checkDayList.setCreateTime(LocalDateTime.now());
                checkDayListMapper.insert(checkDayList);
            }
        }
        return true;
    }

    private void sortList(List<CheckDayList> list){
        int index = 1;
        int union = 1;
        String checkTeamName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckDayList checkDayList = list.get(i);
            if (checkTeamName.equals(checkDayList.getCheckTeamName())){
                union++;
                CheckDayList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTeamName = checkDayList.getCheckTypeName();
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
