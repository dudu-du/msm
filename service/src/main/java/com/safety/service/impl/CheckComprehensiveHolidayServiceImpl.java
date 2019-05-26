package com.safety.service.impl;

import com.safety.entity.CheckComprehensiveHoliday;
import com.safety.entity.CheckComprehensiveHolidayList;
import com.safety.mapper.CheckComprehensiveHolidayListMapper;
import com.safety.mapper.CheckComprehensiveHolidayMapper;
import com.safety.service.ICheckComprehensiveHolidayService;
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
 * 综合检查(节假日、复产前) 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckComprehensiveHolidayServiceImpl extends ServiceImpl<CheckComprehensiveHolidayMapper, CheckComprehensiveHoliday> implements ICheckComprehensiveHolidayService {

    @Autowired
    private CheckComprehensiveHolidayMapper checkComprehensiveHolidayMapper;
    @Autowired
    private CheckComprehensiveHolidayListMapper checkComprehensiveHolidayListMapper;

    @Override
    public CheckComprehensiveHoliday getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckComprehensiveHoliday checkComprehensiveHoliday = checkComprehensiveHolidayMapper.selectByParam(param);
        if (checkComprehensiveHoliday!=null){
            String id = checkComprehensiveHoliday.getId();
            Map map = new HashMap();
            map.put("checkComprehensiveHolidayFk",id);
            List<CheckComprehensiveHolidayList> list = checkComprehensiveHolidayListMapper.selectByPid(map);
            checkComprehensiveHoliday.setCheckComprehensiveHolidayList(list);
        }else {
            checkComprehensiveHoliday = new CheckComprehensiveHoliday();
            checkComprehensiveHoliday.setId(UUIDUtil.getUUID());
            checkComprehensiveHoliday.setOrgFk(orgId);
            checkComprehensiveHoliday.setCreateTime(year);
            checkComprehensiveHolidayMapper.insert(checkComprehensiveHoliday);
            checkComprehensiveHoliday.setCheckComprehensiveHolidayList(new ArrayList<>());
        }
        return checkComprehensiveHoliday;
    }

    @Override
    public boolean addCheckComprehensiveHoliday(CheckComprehensiveHoliday checkComprehensiveHoliday) {
        List<CheckComprehensiveHolidayList> checkComprehensiveHolidayLists = checkComprehensiveHoliday.getCheckComprehensiveHolidayList();
        if (checkComprehensiveHolidayLists.size()>0){
            for (CheckComprehensiveHolidayList checkComprehensiveHolidayList:checkComprehensiveHolidayLists){
                checkComprehensiveHolidayList.setId(UUIDUtil.getUUID());
                checkComprehensiveHolidayListMapper.insert(checkComprehensiveHolidayList);
            }
        }
        return true;
    }
}
