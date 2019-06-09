package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckHolidayRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 综合节假日排查记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckHolidayRecordService extends IService<CheckHolidayRecord> {

    boolean addCheckHolidayRecord(CheckHolidayRecord checkHolidayRecord);

    CheckHolidayRecord getByParam(String orgId, String year);

    PageInfo<CheckHolidayRecord> getByPage(Integer currentPage, Integer pageSize, String orgId);

    CheckHolidayRecord getById(String id);
}
