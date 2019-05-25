package com.safety.mapper;

import com.safety.entity.CheckMonth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 月治理记录 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckMonthMapper extends BaseMapper<CheckMonth> {

    CheckMonth selectByParam(Map param);
}
