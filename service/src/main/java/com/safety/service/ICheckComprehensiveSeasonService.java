package com.safety.service;

import com.safety.entity.CheckComprehensiveSeason;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 综合检查(季节性) 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckComprehensiveSeasonService extends IService<CheckComprehensiveSeason> {

    CheckComprehensiveSeason getByParam(String orgId, String year);

    boolean addCheckComprehensiveSeason(CheckComprehensiveSeason checkComprehensiveSeason);

}
