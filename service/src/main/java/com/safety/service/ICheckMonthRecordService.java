package com.safety.service;

import com.safety.entity.CheckMonthRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 月排查记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckMonthRecordService extends IService<CheckMonthRecord> {

    CheckMonthRecord getByParam(String orgId, String year);

    boolean addCheckMonthRecord(CheckMonthRecord checkMonthRecord);

}
