package com.safety.service;

import com.safety.entity.CheckMonthRecordList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.entity.CheckRectificationReceipt;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 月排查记录填写列表 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckMonthRecordListService extends IService<CheckMonthRecordList> {
    /**
     * 统计月检查表中否的清单个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getChecklistResultCountByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;

    /**
     * 统计月治理检查表中否的清单对应的等级个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getChecklistLevelCountByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;
    /**
     * 统计月治理检查表中否的台账个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getLedgerResultCountByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;

    /**
     * 统计月治理检查表中否的清单对应的台账个数
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getLedgerLevelCountByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;

    /**
     * 获取月治理检查表中否的回执单列表
     * @param orgId
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<CheckRectificationReceipt> getReceiptListByOrg(String orgId, LocalDate startTime, LocalDate endTime) throws Exception;
}
