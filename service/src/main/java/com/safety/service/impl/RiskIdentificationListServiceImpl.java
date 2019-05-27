package com.safety.service.impl;

import com.safety.entity.RiskIdentificationList;
import com.safety.mapper.RiskIdentificationListMapper;
import com.safety.service.IRiskIdentificationListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单列表 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskIdentificationListServiceImpl extends ServiceImpl<RiskIdentificationListMapper, RiskIdentificationList> implements IRiskIdentificationListService {

    @Override
    public List<Map<String,Object>> getLevelCountByOrgId(String orgId) {
        Map<String,String> param = new HashMap<>();
        param.put("orgId",orgId);
        return baseMapper.selectLevelCountByOrg(param);
    }
}
