package com.welsee.mapper;

import com.welsee.entity.AccessCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-06
 */
public interface AccessCodeMapper extends BaseMapper<AccessCode> {
    AccessCode getAccessCode(String clientId,String authzCode);
    void delTimeOutCode(LocalDateTime time);
}
