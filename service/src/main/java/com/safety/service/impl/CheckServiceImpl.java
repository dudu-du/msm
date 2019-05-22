package com.safety.service.impl;

import com.safety.entity.Check;
import com.safety.mapper.CheckMapper;
import com.safety.service.ICheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 安全检查 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {

}
