package com.safety.mapper;

import com.safety.entity.CheckDayList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDayListMapper extends BaseMapper<CheckDayList> {

    List<CheckDayList> selectByPid(Map map);
}
