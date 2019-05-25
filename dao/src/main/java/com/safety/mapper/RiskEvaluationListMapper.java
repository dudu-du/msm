package com.safety.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskEvaluationList;

/**
 * <p>
 * 安全风险动态评估列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskEvaluationListMapper extends BaseMapper<RiskEvaluationList> {
	List<RiskEvaluationList> selectByPid(Map map);
}
