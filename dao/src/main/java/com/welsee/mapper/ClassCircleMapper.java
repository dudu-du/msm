package com.welsee.mapper;

import com.welsee.entity.ClassCircle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.ClassCirclePraise;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级圈表 Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public interface ClassCircleMapper extends BaseMapper<ClassCircle> {

    int getClassCircleListCount(Map<String, Object> hashMap);

    List<ClassCircle> getClassCircleListInfo(Map<String, Object> map);

    List<ClassCircle> getClassCircleByIdAndUserId(Map<String, Object> map);

    int delClassCircle(Map<String, Object> map);


}
