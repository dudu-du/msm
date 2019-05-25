package com.safety.service;

import com.safety.entity.CheckWeek;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 周治理记录 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckWeekService extends IService<CheckWeek> {

    CheckWeek getByParam(String orgId, String year);
}
