package com.safety.mapper;

import com.safety.entity.AccessCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2018-11-06
 */
public interface AccessCodeMapper extends BaseMapper<AccessCode> {
    AccessCode getAccessCode(String clientId,String authzCode);
    void delTimeOutCode(LocalDateTime time);
}
