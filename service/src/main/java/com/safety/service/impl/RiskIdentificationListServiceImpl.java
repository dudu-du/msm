package com.safety.service.impl;

import com.safety.entity.RiskDict;
import com.safety.entity.RiskIdentificationList;
import com.safety.mapper.RiskDictMapper;
import com.safety.mapper.RiskIdentificationListMapper;
import com.safety.service.IRiskIdentificationListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private RiskDictMapper riskDictMapper;

    @Override
    public List<Map<String,Object>> getLevelCountByOrgId(String orgId) {
        Map<String,String> param = new HashMap<>();
        param.put("orgId",orgId);
        return baseMapper.selectLevelCountByOrg(param);
    }

    @Override
    public boolean addRiskIdentificationList(RiskIdentificationList riskIdentificationList) {
        String id = UUIDUtil.getUUID();
        riskIdentificationList.setId(id);
        riskIdentificationList.setCreateTime(LocalDateTime.now());
        String levelFk = riskIdentificationList.getLevelFk();
        RiskDict riskDict = riskDictMapper.selectById(levelFk);
        riskIdentificationList.setLevelName(riskDict.getName());
        riskIdentificationList.setLevelNum(riskDict.getValue());
        baseMapper.insert(riskIdentificationList);
        return true;
    }

    @Override
    public boolean updateRiskIdentificationList(RiskIdentificationList riskIdentificationList) {
        String levelFk = riskIdentificationList.getLevelFk();
        RiskDict riskDict = riskDictMapper.selectById(levelFk);
        riskIdentificationList.setLevelName(riskDict.getName());
        riskIdentificationList.setLevelNum(riskDict.getValue());
        baseMapper.updateById(riskIdentificationList);
        return true;
    }
}
