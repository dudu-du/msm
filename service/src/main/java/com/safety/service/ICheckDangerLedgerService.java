package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerLedger;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 隐患治理信息台账 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckDangerLedgerService extends IService<CheckDangerLedger> {

    PageInfo<CheckDangerLedger> getByPage(Integer currentPage, Integer pageSize);
}
