package com.safety.service;

import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerChecklist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 隐患排查清单 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckDangerChecklistService extends IService<CheckDangerChecklist> {

    PageInfo<CheckDangerChecklist> getByPage(Integer currentPage, Integer pageSize);
}
