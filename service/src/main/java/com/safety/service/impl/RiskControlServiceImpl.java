package com.safety.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.entity.RiskControl;
import com.safety.entity.RiskControlList;
import com.safety.mapper.RiskControlListMapper;
import com.safety.mapper.RiskControlMapper;
import com.safety.service.IRiskControlService;
import com.safety.tools.UUIDUtil;

/**
 * <p>
 * 分级管控 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskControlServiceImpl extends ServiceImpl<RiskControlMapper, RiskControl> implements IRiskControlService {

	@Autowired
	private RiskControlMapper riskControlMapper;
	@Autowired
	private RiskControlListMapper riskControlListMapper;
	
	
	@Override
	public RiskControl getByParam(String orgId, String yearStr) {
		//根据机构ID和年份查询当前是否有值
        Map<String,Object> param = new HashMap<>();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        RiskControl riskControl = riskControlMapper.selectByParam(param);
        if (riskControl!=null){
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskControl.setState(1);
            }else {
            	riskControl.setState(0);
            }
            String id = riskControl.getId();
            Map<String,Object> map = new HashMap<>();
            map.put("riskIdentificationFk",id);
            List<RiskControlList> list = riskControlListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            riskControl.setRiskControlList(list);
        }else {
        	riskControl = new RiskControl();
        	riskControl.setId(UUIDUtil.getUUID());
        	riskControl.setOrgFk(orgId);
        	riskControl.setCreateTime(year);
        	riskControlMapper.insert(riskControl);
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskControl.setState(1);
            }else {
            	riskControl.setState(0);
            }
            riskControl.setRiskControlList(new ArrayList<>());
        }
        return riskControl;
	}

	private void sortList(List<RiskControlList> list){
        int index = 1;
        int union = 1;
        String postName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
        	RiskControlList riskContorlList = list.get(i);
            if (postName.equals(riskContorlList.getPostName())){
                union++;
                RiskControlList first = list.get(position);
                first.setUnion(union);
            }else {
                postName = riskContorlList.getPostName();
                RiskControlList first = list.get(position);
                first.setUnion(union);
                riskContorlList.setIndex(index);
                riskContorlList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
