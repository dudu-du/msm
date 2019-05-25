package com.safety.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskControlList;

/**
 * <p>
 * 安全风险分级管控列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskControlListMapper extends BaseMapper<RiskControlList> {

	List<RiskControlList> selectByPid(Map map);
}
