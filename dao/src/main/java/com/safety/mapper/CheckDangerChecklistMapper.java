package com.safety.mapper;

import com.safety.entity.CheckDangerChecklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患排查清单 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDangerChecklistMapper extends BaseMapper<CheckDangerChecklist> {

    List<CheckDangerChecklist> selectAll();

    List<CheckDangerChecklist> selectByParam(Map map);
}
