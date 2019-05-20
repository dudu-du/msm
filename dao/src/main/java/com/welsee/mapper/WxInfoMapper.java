package com.welsee.mapper;

import com.welsee.entity.WxInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExtWxInfo;
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
 * @since 2019-04-18
 */
public interface WxInfoMapper extends BaseMapper<WxInfo> {
    WxInfo getWxInfo(@Param("orgId") String orgId);
    List<ExtWxInfo> getWxInfoListBypage(Map map);
    ExtWxInfo getExtWxInfo(@Param("orgId") String orgId);
    Integer wxInfoCount();
    Integer delwxInfo(@Param("wxInfoIds") String wxInfoIds, @Param("time") LocalDateTime time);
    WxInfo getWxInfoById(@Param("wxInfoId") String wxInfoId);
    WxInfo getWxInfoByOrgId(@Param("orgId") String orgId);
}
