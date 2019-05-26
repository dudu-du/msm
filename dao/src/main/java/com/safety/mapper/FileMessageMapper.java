package com.safety.mapper;

import com.safety.entity.FileMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 信息收集表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-26
 */
public interface FileMessageMapper extends BaseMapper<FileMessage> {

    List<FileMessage> selectFileMessage(@Param("type") int type);
}
