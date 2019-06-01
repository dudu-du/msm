package com.safety.mapper;

import com.safety.entity.SafetyNotificationCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 岗位安全风险告知卡 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface SafetyNotificationCardMapper extends BaseMapper<SafetyNotificationCard> {

    SafetyNotificationCard selectByParam(Map param);
}
