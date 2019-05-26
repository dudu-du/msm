package com.safety.service;

import com.safety.entity.CheckSpecial;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 专项检查 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckSpecialService extends IService<CheckSpecial> {

    CheckSpecial getByParam(String orgId, String year);

    boolean addCheckSpecial(CheckSpecial checkSpecial);

}
