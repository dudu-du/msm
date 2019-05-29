package com.safety.mapper;

import com.safety.entity.CheckSeasonRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 综合季度排查记录填写 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckSeasonRecordMapper extends BaseMapper<CheckSeasonRecord> {

    CheckSeasonRecord selectByParam(Map param);

    List<CheckSeasonRecord> selectAll();

    int updateById(CheckSeasonRecord checkSeasonRecord);
}
