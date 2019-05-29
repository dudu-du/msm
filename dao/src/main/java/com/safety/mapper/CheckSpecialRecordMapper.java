package com.safety.mapper;

import com.safety.entity.CheckSpecialRecord;
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
public interface CheckSpecialRecordMapper extends BaseMapper<CheckSpecialRecord> {

    CheckSpecialRecord selectByParam(Map param);

    List<CheckSpecialRecord> selectAll();

    int updateById(CheckSpecialRecord checkMonthRecord);

}
