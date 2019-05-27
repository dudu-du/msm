package com.safety.service;

import com.safety.entity.CheckDayRecordList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录填写列表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckDayRecordListService extends IService<CheckDayRecordList> {
    /**
     * 统计日治理清单中否的个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getResultCountByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;
}
