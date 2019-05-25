package com.safety.service.impl;

import com.safety.entity.RiskIdentification;
import com.safety.entity.RiskIdentificationList;
import com.safety.mapper.RiskIdentificationListMapper;
import com.safety.mapper.RiskIdentificationMapper;
import com.safety.service.IRiskIdentificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 安全风险辨识清单总表 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskIdentificationServiceImpl extends ServiceImpl<RiskIdentificationMapper, RiskIdentification> implements IRiskIdentificationService {

    @Autowired
    private RiskIdentificationMapper riskIdentificationMapper;
    @Autowired
    private RiskIdentificationListMapper riskIdentificationListMapper;

    @Override
    public RiskIdentification getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        RiskIdentification riskIdentification = riskIdentificationMapper.selectByParam(param);
        if (riskIdentification!=null){
            String id = riskIdentification.getId();
            Map map = new HashMap();
            map.put("risk_identification_fk",id);
            List<RiskIdentificationList> list = riskIdentificationListMapper.selectByMap(map);
            riskIdentification.setRiskIdentificationList(list);
        }else {
            riskIdentification = new RiskIdentification();
            riskIdentification.setId(UUIDUtil.getUUID());
            riskIdentification.setOrgFk(orgId);
            riskIdentification.setCreateTime(year);
            riskIdentificationMapper.insert(riskIdentification);
            riskIdentification.setRiskIdentificationList(new ArrayList<>());
        }
        return riskIdentification;
    }

    @Override
    public RiskIdentification getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        RiskIdentification riskIdentification = riskIdentificationMapper.selectByParam(param);
        if (riskIdentification!=null){
            Map map = new HashMap();
            map.put("risk_identification_fk",id);
            List<RiskIdentificationList> list = riskIdentificationListMapper.selectByMap(map);
            riskIdentification.setRiskIdentificationList(list);
        }
        return riskIdentification;
    }
}
