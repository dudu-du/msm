package com.safety.mapper;

import com.safety.entity.RiskIdentificationList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskIdentificationListMapper extends BaseMapper<RiskIdentificationList> {

    List<RiskIdentificationList> selectByPid(Map map);
}
