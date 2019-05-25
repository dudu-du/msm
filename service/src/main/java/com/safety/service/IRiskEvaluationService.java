package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.RiskEvaluation;

/**
 * <p>
 * 安全风险动态评估 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskEvaluationService extends IService<RiskEvaluation> {
	RiskEvaluation getByParam(String orgId, String year);
}
