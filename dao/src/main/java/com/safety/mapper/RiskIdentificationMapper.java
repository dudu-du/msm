package com.safety.mapper;

import com.safety.entity.RiskIdentification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单总表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskIdentificationMapper extends BaseMapper<RiskIdentification> {

    RiskIdentification selectByParam(Map param);
}
