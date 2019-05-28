package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDay;
import com.safety.entity.CheckOffgradeList;

/**
 * <p>
 * 日治理记录 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckOffgradeListService extends IService<CheckOffgradeList> {
    PageInfo<CheckOffgradeList> getByPage(Integer currentPage, Integer pageSize);
}
