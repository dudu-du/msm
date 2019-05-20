package com.welsee.mapper;

import com.welsee.entity.PatriarchChild;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-03-04
 */
public interface PatriarchChildMapper extends BaseMapper<PatriarchChild> {

    /**
     * 解绑孩子
     * @param userId
     * @param childIds
     * @return
     */
    boolean unbindingChild(@Param("userId") String userId,
                           @Param("childIds") String childIds,
                           @Param("modifydatetime")LocalDateTime modifydatetime);

}
