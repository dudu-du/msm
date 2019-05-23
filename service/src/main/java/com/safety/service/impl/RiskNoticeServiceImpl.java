package com.safety.service.impl;

import com.safety.entity.RiskNotice;
import com.safety.mapper.RiskNoticeMapper;
import com.safety.service.IRiskNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 安全风险公告栏 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskNoticeServiceImpl extends ServiceImpl<RiskNoticeMapper, RiskNotice> implements IRiskNoticeService {

}
