package com.safety.service;

import com.safety.entity.CheckComprehensiveHoliday;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 综合检查(节假日、复产前) 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckComprehensiveHolidayService extends IService<CheckComprehensiveHoliday> {

    CheckComprehensiveHoliday getByParam(String orgId, String year);

    boolean addCheckComprehensiveHoliday(CheckComprehensiveHoliday checkComprehensiveHoliday);

}
