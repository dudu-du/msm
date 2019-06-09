package com.safety.mapper;

import com.safety.entity.CheckHolidayRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 综合节假日排查记录填写 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckHolidayRecordMapper extends BaseMapper<CheckHolidayRecord> {

    CheckHolidayRecord selectByParam(Map param);

    List<CheckHolidayRecord> selectAll(Map map);

    int updateById(CheckHolidayRecord checkMonthRecord);
}
