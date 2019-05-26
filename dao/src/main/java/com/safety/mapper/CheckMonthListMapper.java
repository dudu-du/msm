package com.safety.mapper;

import com.safety.entity.CheckMonthList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckMonthRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月治理记录列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckMonthListMapper extends BaseMapper<CheckMonthList> {

    List<CheckMonthList> selectByPid(Map map);

    List<CheckMonthList> selectByParam(Map map);
}
