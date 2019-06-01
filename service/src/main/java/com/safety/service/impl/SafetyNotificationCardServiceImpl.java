package com.safety.service.impl;

import com.safety.entity.SafetyNotificationCard;
import com.safety.entity.SafetyNotificationCardList;
import com.safety.mapper.SafetyNotificationCardListMapper;
import com.safety.mapper.SafetyNotificationCardMapper;
import com.safety.service.ISafetyNotificationCardService;
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
 * 岗位安全风险告知卡 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class SafetyNotificationCardServiceImpl extends ServiceImpl<SafetyNotificationCardMapper, SafetyNotificationCard> implements ISafetyNotificationCardService {

    @Autowired
    private SafetyNotificationCardMapper safetyNotificationCardMapper;
    @Autowired
    private SafetyNotificationCardListMapper safetyNotificationCardListMapper;

    @Override
    public SafetyNotificationCard getById(String id) {
        Map param = new HashMap();
        param.put("id",id);
        SafetyNotificationCard safetyNotificationCard = safetyNotificationCardMapper.selectByParam(param);
        if (safetyNotificationCard!=null){
            String safetyNotificationCardId = safetyNotificationCard.getId();
            Map map = new HashMap();
            map.put("safetyNotificationCardFk",safetyNotificationCardId);
            List<SafetyNotificationCardList> list = safetyNotificationCardListMapper.selectByParam(map);
            safetyNotificationCard.setSafetyNotificationCardList(list);
        }
        return safetyNotificationCard;
    }

    @Override
    public boolean addSafetyNotificationCard(SafetyNotificationCard safetyNotificationCard) {
        List<SafetyNotificationCardList> safetyNotificationCardLists = safetyNotificationCard.getSafetyNotificationCardList();
        if (safetyNotificationCardLists.size()>0){
            for (SafetyNotificationCardList safetyNotificationCardList:safetyNotificationCardLists){
                safetyNotificationCardList.setId(UUIDUtil.getUUID());
                safetyNotificationCardList.setCreateTime(LocalDateTime.now());
                safetyNotificationCardListMapper.insert(safetyNotificationCardList);
            }
        }
        return true;
    }
}
