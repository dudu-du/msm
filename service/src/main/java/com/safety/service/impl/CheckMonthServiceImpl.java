package com.safety.service.impl;

import com.safety.entity.CheckMonth;
import com.safety.entity.CheckMonthList;
import com.safety.mapper.CheckMonthListMapper;
import com.safety.mapper.CheckMonthMapper;
import com.safety.service.ICheckMonthService;
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
 * 月治理记录 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckMonthServiceImpl extends ServiceImpl<CheckMonthMapper, CheckMonth> implements ICheckMonthService {

    @Autowired
    private CheckMonthMapper checkMonthMapper;

    @Autowired
    private CheckMonthListMapper checkMonthListMapper;

    @Override
    public CheckMonth getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckMonth checkMonth = checkMonthMapper.selectByParam(param);
        if (checkMonth!=null){
            String id = checkMonth.getId();
            Map map = new HashMap();
            map.put("checkMonthFk",id);
            List<CheckMonthList> list = checkMonthListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            checkMonth.setCheckMonthList(list);
        }else {
            checkMonth = new CheckMonth();
            checkMonth.setId(UUIDUtil.getUUID());
            checkMonth.setOrgFk(orgId);
            checkMonth.setCreateTime(year);
            checkMonthMapper.insert(checkMonth);
            checkMonth.setCheckMonthList(new ArrayList<>());
        }
        return checkMonth;
    }

    @Override
    public boolean addCheckMonth(CheckMonth checkMonth) {
        List<CheckMonthList> checkMonthLists = checkMonth.getCheckMonthList();
        if (checkMonthLists.size()>0){
            for (CheckMonthList checkWeekList:checkMonthLists){
                checkWeekList.setId(UUIDUtil.getUUID());
                checkMonthListMapper.insert(checkWeekList);
            }
        }
        return true;
    }

    private void sortList(List<CheckMonthList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckMonthList checkWeekList = list.get(i);
            if (checkTypeName.equals(checkWeekList.getCheckTypeName())){
                union++;
                CheckMonthList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkWeekList.getCheckTypeName();
                CheckMonthList first = list.get(position);
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
