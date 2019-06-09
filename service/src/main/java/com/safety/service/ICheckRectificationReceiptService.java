package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckRectificationReceipt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 隐患整改回执单 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckRectificationReceiptService extends IService<CheckRectificationReceipt> {

    PageInfo<CheckRectificationReceipt> getByPage(Integer currentPage, Integer pageSize, String orgId);
}
