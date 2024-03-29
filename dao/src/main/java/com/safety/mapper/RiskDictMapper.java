package com.safety.mapper;

import com.safety.entity.RiskDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 风险相关数据字典 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskDictMapper extends BaseMapper<RiskDict> {

    List<RiskDict> getRiskDictListByCode(@Param("code") String code);

    List<RiskDict> getRiskDictCodeList();
}
