package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckSeasonRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 综合季度排查记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckSeasonRecordService extends IService<CheckSeasonRecord> {

    boolean addCheckSeasonRecord(CheckSeasonRecord checkSeasonRecord);

    CheckSeasonRecord getByParam(String orgId, String year);

    PageInfo<CheckSeasonRecord> getByPage(Integer currentPage, Integer pageSize);

    CheckSeasonRecord getById(String id);
}
