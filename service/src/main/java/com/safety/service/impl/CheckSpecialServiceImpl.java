package com.safety.service.impl;

import com.safety.entity.CheckSpecial;
import com.safety.entity.CheckSpecialList;
import com.safety.mapper.CheckSpecialListMapper;
import com.safety.mapper.CheckSpecialMapper;
import com.safety.service.ICheckSpecialService;
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
 * 专项检查 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckSpecialServiceImpl extends ServiceImpl<CheckSpecialMapper, CheckSpecial> implements ICheckSpecialService {

    @Autowired
    private CheckSpecialMapper checkSpecialMapper;
    @Autowired
    private CheckSpecialListMapper checkSpecialListMapper;

    @Override
    public CheckSpecial getByParam(String orgId, String yearStr) {
        //根据机构ID和年份查询当前是否有值
        Map param = new HashMap();
        param.put("orgFk",orgId);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime year = LocalDateTime.parse(yearStr+"-01-01 00:00:00",df);
        param.put("createTime",year);
        CheckSpecial checkSpecial = checkSpecialMapper.selectByParam(param);
        if (checkSpecial!=null){
            String id = checkSpecial.getId();
            Map map = new HashMap();
            map.put("checkSpecialFk",id);
            List<CheckSpecialList> list = checkSpecialListMapper.selectByPid(map);
            if (list.size()>0){
                sortList(list);
            }
            checkSpecial.setCheckSpecialList(list);
        }else {
            checkSpecial = new CheckSpecial();
            checkSpecial.setId(UUIDUtil.getUUID());
            checkSpecial.setOrgFk(orgId);
            checkSpecial.setCreateTime(year);
            checkSpecialMapper.insert(checkSpecial);
            checkSpecial.setCheckSpecialList(new ArrayList<>());
        }
        return checkSpecial;
    }

    @Override
    public boolean addCheckSpecial(CheckSpecial checkSpecial) {
        List<CheckSpecialList> checkSpecialLists = checkSpecial.getCheckSpecialList();
        if (checkSpecialLists.size()>0){
            for (CheckSpecialList checkSpecialList:checkSpecialLists){
                checkSpecialList.setId(UUIDUtil.getUUID());
                checkSpecialListMapper.insert(checkSpecialList);
            }
        }
        return true;
    }

    private void sortList(List<CheckSpecialList> list){
        int index = 1;
        int union = 1;
        String checkTypeName = "";
        int position = 0;
        for (int i=0;i<list.size();i++){
            CheckSpecialList checkSpecialList = list.get(i);
            if (checkTypeName.equals(checkSpecialList.getCheckTypeName())){
                union++;
                CheckSpecialList first = list.get(position);
                first.setUnion(union);
            }else {
                checkTypeName = checkSpecialList.getCheckTypeName();
                CheckSpecialList first = list.get(position);
                first.setUnion(union);
                checkSpecialList.setIndex(index);
                checkSpecialList.setUnion(1);
                index++;
                position = i;
                union = 1;
            }
        }
    }
}
