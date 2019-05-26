package com.safety.service;

import com.safety.entity.CheckMonth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 月治理记录 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckMonthService extends IService<CheckMonth> {

    CheckMonth getByParam(String orgId, String year);

    boolean addCheckMonth(CheckMonth checkMonth);
}
