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
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
                riskIdentification.setState(1);
            }else {
                riskIdentification.setState(0);
            }
            String id = riskIdentification.getId();
            Map map = new HashMap();
            map.put("riskIdentificationFk",id);
            List<RiskIdentificationList> list = riskIdentificationListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            riskIdentification.setRiskIdentificationList(list);
        }else {
            riskIdentification = new RiskIdentification();
            riskIdentification.setId(UUIDUtil.getUUID());
            riskIdentification.setOrgFk(orgId);
            riskIdentification.setCreateTime(year);
            riskIdentificationMapper.insert(riskIdentification);
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
                riskIdentification.setState(1);
            }else {
                riskIdentification.setState(0);
            }
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
            if (list.size()>0){
                sortList(list);
            }
            riskIdentification.setRiskIdentificationList(list);
        }
        return riskIdentification;
    }

    private List<RiskIdentificationList> sortList(List<RiskIdentificationList> list){
        int index = 1;
        int union = 1;
        String postName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            RiskIdentificationList riskIdentificationList = list.get(i);
            if (postName.equals(riskIdentificationList.getPostName())){
                union++;
            }else {
                postName = riskIdentificationList.getPostName();
                RiskIdentificationList first = list.get(position);
                first.setUnion(union);
                riskIdentificationList.setIndex(index);
                riskIdentificationList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
        return list;
    }
}
