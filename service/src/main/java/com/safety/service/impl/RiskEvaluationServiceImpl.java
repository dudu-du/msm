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
import com.safety.entity.RiskControlList;
import com.safety.entity.RiskEvaluation;
import com.safety.entity.RiskEvaluationList;
import com.safety.mapper.RiskEvaluationListMapper;
import com.safety.mapper.RiskEvaluationMapper;
import com.safety.service.IRiskEvaluationService;
import com.safety.tools.UUIDUtil;

/**
 * <p>
 * 安全风险动态评估 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskEvaluationServiceImpl extends ServiceImpl<RiskEvaluationMapper, RiskEvaluation> implements IRiskEvaluationService {

	@Autowired
	private RiskEvaluationMapper riskEvaluationMapper;
	@Autowired
	private RiskEvaluationListMapper riskEvaluationListMapper;
	
	@Override
	public RiskEvaluation getByParam(String orgId, String yearStr) {
		//根据机构ID和年份查询当前是否有值
        Map<String,Object> param = new HashMap<>();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        RiskEvaluation riskEvaluation = riskEvaluationMapper.selectByParam(param);
        if (riskEvaluation!=null){
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskEvaluation.setState(1);
            }else {
            	riskEvaluation.setState(0);
            }
            String id = riskEvaluation.getId();
            Map<String,Object> map = new HashMap<>();
            map.put("riskEvaluationFk",id);
            List<RiskEvaluationList> list = riskEvaluationListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            riskEvaluation.setRiskEvaluationList(list);
        }else {
        	riskEvaluation = new RiskEvaluation();
        	riskEvaluation.setId(UUIDUtil.getUUID());
        	riskEvaluation.setOrgFk(orgId);
        	riskEvaluation.setCreateTime(year);
        	riskEvaluationMapper.insert(riskEvaluation);
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskEvaluation.setState(1);
            }else {
            	riskEvaluation.setState(0);
            }
            riskEvaluation.setRiskEvaluationList(new ArrayList<>());
        }
        return riskEvaluation;
	}
	private void sortList(List<RiskEvaluationList> list){
        int index = 1;
        int union = 1;
        String postName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
        	RiskEvaluationList riskContorlList = list.get(i);
            if (postName.equals(riskContorlList.getPostName())){
                union++;
                RiskEvaluationList first = list.get(position);
                first.setUnion(union);
            }else {
                postName = riskContorlList.getPostName();
                RiskEvaluationList first = list.get(position);
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
