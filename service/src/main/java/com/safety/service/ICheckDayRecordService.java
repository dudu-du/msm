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
    /**
     * 获取日治理记录列表，不包括详细内容
     * @return
     */
    List<CheckDayRecord> getCheckDayRecord();

    /**
     * 根据id获取日治理记录，包括详细内容
     * @param recordId
     * @return
     * @throws Exception
     */
    CheckDayRecord getCheckDayRecordListById(String recordId) throws Exception;

    PageInfo<CheckDayRecord> getByPage(Integer currentPage, Integer pageSize);
}
