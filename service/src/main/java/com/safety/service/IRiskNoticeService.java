package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.RiskNotice;

/**
 * <p>
 * 安全风险公告栏 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskNoticeService extends IService<RiskNotice> {

	RiskNotice getByParam(String orgId, String yearStr);
}
