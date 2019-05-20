package com.welsee.mapper;

import com.welsee.dto.DeviceTime;
import com.welsee.entity.CalendarEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-05-06
 */
public interface CalendarEventMapper extends BaseMapper<CalendarEvent> {

    int getCalendarEventCountByTypeAndOrgId(Map<String, Object> map);

    List<CalendarEvent> selectWorkdayByOrgTime(@Param("orgId")String orgId,@Param("time") LocalDate time);

    List<CalendarEvent> selectHolidayByOrgTime(@Param("orgId")String orgId,@Param("time") LocalDate time);

}
