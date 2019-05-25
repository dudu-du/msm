package com.safety.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskEvaluation;

/**
 * <p>
 * 安全风险动态评估 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskEvaluationMapper extends BaseMapper<RiskEvaluation> {

	RiskEvaluation selectByParam(Map map);
}
