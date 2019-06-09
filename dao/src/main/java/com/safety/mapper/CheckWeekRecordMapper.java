package com.safety.mapper;

import com.safety.entity.CheckWeekRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月排查记录填写 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckWeekRecordMapper extends BaseMapper<CheckWeekRecord> {

    CheckWeekRecord selectByParam(Map param);

    List<CheckWeekRecord> selectAll(Map map);

    int updateById(CheckWeekRecord checkWeekRecord);
}
