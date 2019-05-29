package com.safety.mapper;

import com.safety.entity.CheckMonthRecord;
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
public interface CheckMonthRecordMapper extends BaseMapper<CheckMonthRecord> {

    CheckMonthRecord selectByParam(Map param);

    List<CheckMonthRecord> selectAll();

}
