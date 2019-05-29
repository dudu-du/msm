package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckWeekRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 月排查记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckWeekRecordService extends IService<CheckWeekRecord> {

    boolean addCheckWeekRecord(CheckWeekRecord checkWeekRecord);

    CheckWeekRecord getByParam(String orgId, String year);

    PageInfo<CheckWeekRecord> getByPage(Integer currentPage, Integer pageSize);

    CheckWeekRecord getById(String id);
}
