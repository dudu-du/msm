package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.Sensitives;
import com.welsee.extentity.ExtSensitive;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-04-16
 */
public interface SensitiveMapper extends BaseMapper<Sensitives> {
    Integer getSensitiveCount();
    List<Sensitives> getSensitiveByMap(Map map);
    List<Sensitives> getSensitiveListForStr(Map map);
    List<Sensitives> getSensitiveByWords(@Param("words") String words);
    ExtSensitive getExtSensitive();
    Integer delSensitive(@Param("SensitiveIds") String SensitiveIds, @Param("time") LocalDateTime time);
}
