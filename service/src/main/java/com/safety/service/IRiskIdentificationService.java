package com.safety.service;

import com.safety.entity.RiskIdentification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

/**
 * <p>
 * 安全风险辨识清单总表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskIdentificationService extends IService<RiskIdentification> {

    RiskIdentification getByParam(String orgId, String year);

    RiskIdentification getById(String id);
}
