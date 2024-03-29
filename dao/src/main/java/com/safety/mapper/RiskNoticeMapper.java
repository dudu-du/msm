package com.safety.mapper;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskNotice;

/**
 * <p>
 * 安全风险公告栏 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskNoticeMapper extends BaseMapper<RiskNotice> {

	RiskNotice selectByParam(Map map);
}
