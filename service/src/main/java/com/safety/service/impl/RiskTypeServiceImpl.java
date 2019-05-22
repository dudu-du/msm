package com.safety.service.impl;

import com.safety.entity.RiskType;
import com.safety.mapper.RiskTypeMapper;
import com.safety.service.IRiskTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 风险类型 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
@Service
public class RiskTypeServiceImpl extends ServiceImpl<RiskTypeMapper, RiskType> implements IRiskTypeService {

}
