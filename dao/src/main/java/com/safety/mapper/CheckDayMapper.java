package com.safety.mapper;

import com.safety.entity.CheckDay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 日治理记录 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDayMapper extends BaseMapper<CheckDay> {

    CheckDay selectByParam(Map param);
}
