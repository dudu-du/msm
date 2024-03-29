package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.SafetyNotificationCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 岗位安全风险告知卡 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ISafetyNotificationCardService extends IService<SafetyNotificationCard> {
    SafetyNotificationCard getById(String id);

    boolean addSafetyNotificationCard(SafetyNotificationCard safetyNotificationCard);

    PageInfo<SafetyNotificationCard> getByPage(Integer currentPage, Integer pageSize, String orgId);

    boolean updateSafetyNotificationCard(SafetyNotificationCard safetyNotificationCard);
}
