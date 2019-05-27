package com.safety.mapper;

import com.safety.entity.CheckDayRecordList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录填写列表 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckDayRecordListMapper extends BaseMapper<CheckDayRecordList> {
    List<Map<String,Object>> selectChecklistResultCountByOrg(Map map);
    List<Map<String,Object>> selectChecklistLevelCountByOrg(Map map);
    List<Map<String,Object>> selectLedgerResultCountByOrg(Map map);
    List<Map<String,Object>> selectLedgerLevelCountByOrg(Map map);
}
