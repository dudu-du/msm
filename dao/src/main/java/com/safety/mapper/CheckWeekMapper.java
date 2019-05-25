package com.safety.mapper;

import com.safety.entity.CheckWeek;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 周治理记录 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckWeekMapper extends BaseMapper<CheckWeek> {

    CheckWeek selectByParam(Map param);
}
