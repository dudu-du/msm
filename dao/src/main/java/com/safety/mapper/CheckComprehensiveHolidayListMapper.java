package com.safety.mapper;

import com.safety.entity.CheckComprehensiveHolidayList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckHolidayRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 综合检查(节假日、复产前)列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckComprehensiveHolidayListMapper extends BaseMapper<CheckComprehensiveHolidayList> {

    List<CheckComprehensiveHolidayList> selectByPid(Map map);

    List<CheckComprehensiveHolidayList> selectByParam(Map map);
}
