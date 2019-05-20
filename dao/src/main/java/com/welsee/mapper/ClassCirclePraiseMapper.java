package com.welsee.mapper;

import com.welsee.entity.ClassCirclePraise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public interface ClassCirclePraiseMapper extends BaseMapper<ClassCirclePraise> {

    ClassCirclePraise getPraiseByClassCircleId(@Param("classCircleId") String classCircleId);

}
