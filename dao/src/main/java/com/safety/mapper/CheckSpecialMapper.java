package com.safety.mapper;

import com.safety.entity.CheckSpecial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 专项检查 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckSpecialMapper extends BaseMapper<CheckSpecial> {

    CheckSpecial selectByParam(Map param);
}
