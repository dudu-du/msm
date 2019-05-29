package com.safety.mapper;

import com.safety.entity.CheckComprehensiveSeasonList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.CheckSeasonRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 综合检查(季节性)列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckComprehensiveSeasonListMapper extends BaseMapper<CheckComprehensiveSeasonList> {

    List<CheckComprehensiveSeasonList> selectByPid(Map map);

    List<CheckComprehensiveSeasonList> selectByParam(Map map);
}
