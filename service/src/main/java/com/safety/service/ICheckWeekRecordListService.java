package com.safety.service;

import com.safety.entity.CheckRectificationReceipt;
import com.safety.entity.CheckWeekRecordList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 周排查记录填写列表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckWeekRecordListService extends IService<CheckWeekRecordList> {
    /**
     * 统计周检查表中否的清单个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getChecklistResultCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception;

    /**
     * 统计周治理检查表中否的清单对应的等级个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getChecklistLevelCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception;
    /**
     * 统计周治理检查表中否的台账个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getLedgerResultCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception;

    /**
     * 统计周治理检查表中否的清单对应的台账个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getLedgerLevelCountByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception;

    /**
     * 获取周治理检查表中否的回执单列表
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<CheckRectificationReceipt> getReceiptListByOrg(String orgId, LocalDate startTime, LocalDateTime endTime) throws Exception;
}
