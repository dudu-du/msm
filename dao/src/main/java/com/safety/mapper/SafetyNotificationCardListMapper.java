package com.safety.mapper;

import com.safety.entity.SafetyNotificationCard;
import com.safety.entity.SafetyNotificationCardList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 岗位安全风险告知卡列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface SafetyNotificationCardListMapper extends BaseMapper<SafetyNotificationCardList> {

    List<SafetyNotificationCardList> selectByParam(Map map);
}
