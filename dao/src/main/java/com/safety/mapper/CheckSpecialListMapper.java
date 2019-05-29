package com.safety.mapper;

import com.safety.entity.CheckSpecialList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckSpecialRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 专项检查列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckSpecialListMapper extends BaseMapper<CheckSpecialList> {

    List<CheckSpecialList> selectByPid(Map map);

    List<CheckSpecialList> selectByParam(Map map);
}
