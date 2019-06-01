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
    public RiskIdentification getByParam(String orgId, String yearStr, String postName, String levelName) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        RiskIdentification riskIdentification = riskIdentificationMapper.selectByParam(param);
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("vb",0);
        countMap.put("b",0);
        countMap.put("c",0);
        countMap.put("l",0);
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
            map.put("postName",postName);
            map.put("levelName",levelName);
            List<RiskIdentificationList> list = riskIdentificationListMapper.selectByPid(map);
            if (list.size()>0){
                countMap = sortList(list);
            }
            riskIdentification.setCountMap(countMap);
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
            riskIdentification.setCountMap(countMap);
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
            map.put("riskIdentificationFk",id);
            List<RiskIdentificationList> list = riskIdentificationListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            riskIdentification.setRiskIdentificationList(list);
        }
        return riskIdentification;
    }

    private Map<String,Integer> sortList(List<RiskIdentificationList> list){
        Map<String,Integer> count = new HashMap();
        //重大风险 verybig
        int vb = 0;
        //较大风险 big
        int b = 0;
        //一般风险 common
        int c = 0;
        //低风险 low
        int l = 0;
        //当前为第几个合并的数据
        int index = 1;
        //合并项有多少条
        int union = 1;
        String postName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            RiskIdentificationList riskIdentificationList = list.get(i);
            switch (riskIdentificationList.getLevelName()){
                case "重大风险":
                    vb++;
                    break;
                case "较大风险":
                    b++;
                    break;
                case "一般风险":
                    c++;
                    break;
                case "低风险":
                    l++;
                    break;
            }
            if (postName.equals(riskIdentificationList.getPostName())){
                union++;
                RiskIdentificationList first = list.get(position);
                first.setUnion(union);
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
        count.put("vb",vb);
        count.put("b",b);
        count.put("c",c);
        count.put("l",l);
        return count;
    }
}
