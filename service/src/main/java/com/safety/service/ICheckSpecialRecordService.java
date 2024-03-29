package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckSpecialRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 月排查记录填写 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckSpecialRecordService extends IService<CheckSpecialRecord> {

    CheckSpecialRecord getByParam(String orgId, String year);

    boolean addCheckSpecialRecord(CheckSpecialRecord checkSpecialRecord);

    PageInfo<CheckSpecialRecord> getByPage(Integer currentPage, Integer pageSize, String orgId);

    CheckSpecialRecord getById(String id);
}
