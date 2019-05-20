package com.welsee.mapper;

import com.welsee.entity.Calendar;
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
 * @since 2019-04-17
 */
public interface CalendarMapper extends BaseMapper<Calendar> {

    void deleteCalenderByIds(Map<String, Object> map);

    int getCalendarListCount(Map<String, Object> map);

    List<Calendar> getCalendarListInfo(Map<String, Object> map);

    List<Calendar> getCalendarVacationListByOrgId(@Param("orgId") String orgId);

    List<Calendar> getCalendarVacationListByOrgIdTime(@Param("orgId") String orgId,@Param("time") LocalDate time);
}
