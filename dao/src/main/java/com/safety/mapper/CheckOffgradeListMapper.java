package com.safety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckDay;
import com.safety.entity.CheckOffgradeList;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckOffgradeListMapper extends BaseMapper<CheckOffgradeList> {

    List<CheckOffgradeList> selectAll();
    List<Map<String,Object>> selectDayOffgradeTroubleCountByOrg(Map map);
    List<Map<String,Object>> selectDayOffgradeLevelCountByOrg(Map map);
    List<Map<String,Object>> selectWeekOffgradeTroubleCountByOrg(Map map);
    List<Map<String,Object>> selectWeekOffgradeLevelCountByOrg(Map map);
    List<Map<String,Object>> selectMonthOffgradeTroubleCountByOrg(Map map);
    List<Map<String,Object>> selectMonthOffgradeLevelCountByOrg(Map map);
}
