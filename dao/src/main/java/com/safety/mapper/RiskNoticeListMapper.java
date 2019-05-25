package com.safety.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.RiskNoticeList;

/**
 * <p>
 * 安全风险辨识清单列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface RiskNoticeListMapper extends BaseMapper<RiskNoticeList> {

	List<RiskNoticeList> selectByPid(Map map);
}
