package com.safety.service.impl;

import com.safety.entity.RiskDict;
import com.safety.mapper.RiskDictMapper;
import com.safety.service.IRiskDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 风险相关数据字典 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskDictServiceImpl extends ServiceImpl<RiskDictMapper, RiskDict> implements IRiskDictService {

    @Autowired
    private RiskDictMapper riskDictMapper;

    @Override
    public List<RiskDict> getRiskDictListByCode(String code) {
       return riskDictMapper.getRiskDictListByCode(code);
    }

    @Override
    public List<RiskDict> getRiskDictCodeList() {
        return riskDictMapper.getRiskDictCodeList();
    }
}
