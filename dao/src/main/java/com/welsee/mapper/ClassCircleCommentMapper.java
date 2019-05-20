package com.welsee.mapper;

import com.welsee.entity.ClassCircleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public interface ClassCircleCommentMapper extends BaseMapper<ClassCircleComment> {

    List<ClassCircleComment> getClassCircleCommentByIdAndUserId(Map<String, Object> map);

    int delComment(Map<String, Object> map);

    List<ClassCircleComment> getCommentList(@Param("classCircleId") String classCircleId);
}
