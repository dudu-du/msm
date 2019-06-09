package com.safety.service;

import com.safety.entity.RiskIdentificationList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单列表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface IRiskIdentificationListService extends IService<RiskIdentificationList> {
    /**
     * 根据机构id获取风险辨识安全登记数量，管理员默认orgId=0，查询所有机构
     * @param orgId
     * @return
     */
    List<Map<String,Object>> getLevelCountByOrgId(String orgId);

    boolean addRiskIdentificationList(RiskIdentificationList riskIdentificationList);

    boolean updateRiskIdentificationList(RiskIdentificationList riskIdentificationList);
}
