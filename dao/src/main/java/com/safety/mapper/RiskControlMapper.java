package com.safety.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskControl;

/**
 * <p>
 * 分级管控 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskControlMapper extends BaseMapper<RiskControl> {
	RiskControl selectByParam(Map param);
}
