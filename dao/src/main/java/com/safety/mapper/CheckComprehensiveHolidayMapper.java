package com.safety.mapper;

import com.safety.entity.CheckComprehensiveHoliday;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 综合检查(节假日、复产前) Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckComprehensiveHolidayMapper extends BaseMapper<CheckComprehensiveHoliday> {

    CheckComprehensiveHoliday selectByParam(Map param);
}
