package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDayRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 日治理记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckDayRecordService extends IService<CheckDayRecord> {

    PageInfo<CheckDayRecord> getByPage(Integer currentPage, Integer pageSize, String orgId);

    boolean addCheckDayRecord(CheckDayRecord checkDayRecord);

    CheckDayRecord getById(String id);

    CheckDayRecord getByParam(String orgId, String year);
}
