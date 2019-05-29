package com.safety.mapper;

import com.safety.entity.CheckWeekList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckWeekRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 周治理记录列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckWeekListMapper extends BaseMapper<CheckWeekList> {

    List<CheckWeekList> selectByPid(Map map);

    List<CheckWeekList> selectByParam(Map map);
}
