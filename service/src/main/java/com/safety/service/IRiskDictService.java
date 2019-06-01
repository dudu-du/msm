package com.safety.service;

import com.safety.entity.RiskDict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险相关数据字典 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskDictService extends IService<RiskDict> {

    List<RiskDict> getRiskDictListByCode(String code);

    List<RiskDict> getRiskDictCodeList();
}
