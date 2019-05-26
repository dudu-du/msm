package com.safety.mapper;

import com.safety.entity.CheckComprehensiveSeason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 综合检查(季节性) Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckComprehensiveSeasonMapper extends BaseMapper<CheckComprehensiveSeason> {

    CheckComprehensiveSeason selectByParam(Map param);
}
