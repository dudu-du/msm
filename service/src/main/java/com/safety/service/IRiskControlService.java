package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.RiskControl;

/**
 * <p>
 * 分级管控 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskControlService extends IService<RiskControl> {

	RiskControl getByParam(String orgId, String year);
}
