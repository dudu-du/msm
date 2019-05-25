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
import com.safety.entity.RiskNotice;
import com.safety.entity.RiskNoticeList;
import com.safety.mapper.RiskNoticeListMapper;
import com.safety.mapper.RiskNoticeMapper;
import com.safety.service.IRiskNoticeService;
import com.safety.tools.UUIDUtil;

/**
 * <p>
 * 安全风险公告栏 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class RiskNoticeServiceImpl extends ServiceImpl<RiskNoticeMapper, RiskNotice> implements IRiskNoticeService {

	@Autowired
	private RiskNoticeMapper riskNoticeMapper;
	@Autowired
	private RiskNoticeListMapper riskNoticeListMapper;
	@Override
	public RiskNotice getByParam(String orgId, String yearStr) {
		//根据机构ID和年份查询当前是否有值
        Map<String,Object> param = new HashMap<>();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        RiskNotice riskNotice = riskNoticeMapper.selectByParam(param);
        if (riskNotice!=null){
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskNotice.setState(1);
            }else {
            	riskNotice.setState(0);
            }
            String id = riskNotice.getId();
            Map<String,Object> map = new HashMap<>();
            map.put("riskNoticeFk",id);
            List<RiskNoticeList> list = riskNoticeListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            riskNotice.setRiskNoticeList(list);
        }else {
        	riskNotice = new RiskNotice();
        	riskNotice.setId(UUIDUtil.getUUID());
        	riskNotice.setOrgFk(orgId);
        	riskNotice.setCreateTime(year);
        	riskNoticeMapper.insert(riskNotice);
            //判断时间 修改状态
            LocalDateTime localDateTime = LocalDateTime.now();
            int nowYear = localDateTime.getYear();
            if (nowYear == Integer.parseInt(yearStr)){
            	riskNotice.setState(1);
            }else {
            	riskNotice.setState(0);
            }
            riskNotice.setRiskNoticeList(new ArrayList<>());
        }
        return riskNotice;
	}

	private void sortList(List<RiskNoticeList> list){
        int index = 1;
        int union = 1;
        String postName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
        	RiskNoticeList riskNoticeList = list.get(i);
            if (postName.equals(riskNoticeList.getLocationName())){
                union++;
                RiskNoticeList first = list.get(position);
                first.setUnion(union);
            }else {
                postName = riskNoticeList.getLocationName();
                RiskNoticeList first = list.get(position);
                first.setUnion(union);
                riskNoticeList.setIndex(index);
                riskNoticeList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
	
}
