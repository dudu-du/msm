package com.safety.mapper;

import com.safety.entity.CheckDangerLedger;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患治理信息台账 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDangerLedgerMapper extends BaseMapper<CheckDangerLedger> {

    List<CheckDangerLedger> selectAll();

    List<CheckDangerLedger> selectByParam(Map map);
}
