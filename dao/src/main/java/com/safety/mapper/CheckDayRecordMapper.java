package com.safety.mapper;

import com.safety.entity.CheckDayRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录填写 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDayRecordMapper extends BaseMapper<CheckDayRecord> {
    List<CheckDayRecord> selectCheckDayRecord();
    CheckDayRecord selectCheckDayRecordListById(@Param("id") String id);

    List<CheckDayRecord> selectAll();

    CheckDayRecord selectByParam(Map param);

    int updateById(CheckDayRecord checkDayRecord);
}
