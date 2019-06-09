package com.safety.service.impl;

import com.safety.entity.CheckComprehensiveSeason;
import com.safety.entity.CheckComprehensiveSeasonList;
import com.safety.mapper.CheckComprehensiveSeasonListMapper;
import com.safety.mapper.CheckComprehensiveSeasonMapper;
import com.safety.service.ICheckComprehensiveSeasonService;
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
 * 综合检查(季节性) 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckComprehensiveSeasonServiceImpl extends ServiceImpl<CheckComprehensiveSeasonMapper, CheckComprehensiveSeason> implements ICheckComprehensiveSeasonService {

    @Autowired
    private CheckComprehensiveSeasonMapper checkComprehensiveSeasonMapper;
    @Autowired
    private CheckComprehensiveSeasonListMapper checkComprehensiveSeasonListMapper;

    @Override
    public CheckComprehensiveSeason getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckComprehensiveSeason checkComprehensiveSeason = checkComprehensiveSeasonMapper.selectByParam(param);
        if (checkComprehensiveSeason!=null){
            String id = checkComprehensiveSeason.getId();
            Map map = new HashMap();
            map.put("checkComprehensiveSeasonFk",id);
            List<CheckComprehensiveSeasonList> list = checkComprehensiveSeasonListMapper.selectByPid(map);
            checkComprehensiveSeason.setCheckComprehensiveSeasonList(list);
        }else {
            checkComprehensiveSeason = new CheckComprehensiveSeason();
            checkComprehensiveSeason.setId(UUIDUtil.getUUID());
            checkComprehensiveSeason.setOrgFk(orgId);
            checkComprehensiveSeason.setCreateTime(year);
            checkComprehensiveSeasonMapper.insert(checkComprehensiveSeason);
            checkComprehensiveSeason.setCheckComprehensiveSeasonList(new ArrayList<>());
        }
        return checkComprehensiveSeason;
    }

    @Override
    public boolean addCheckComprehensiveSeason(CheckComprehensiveSeason checkComprehensiveSeason) {
        List<CheckComprehensiveSeasonList> checkComprehensiveSeasonLists = checkComprehensiveSeason.getCheckComprehensiveSeasonList();
        String orgId = checkComprehensiveSeason.getOrgFk();
        if (checkComprehensiveSeasonLists.size()>0){
            for (CheckComprehensiveSeasonList checkComprehensiveSeasonList:checkComprehensiveSeasonLists){
                checkComprehensiveSeasonList.setId(UUIDUtil.getUUID());
                checkComprehensiveSeason.setCreateTime(LocalDateTime.now());
                checkComprehensiveSeason.setOrgFk(orgId);
                checkComprehensiveSeasonListMapper.insert(checkComprehensiveSeasonList);
            }
        }
        return true;
    }
}
