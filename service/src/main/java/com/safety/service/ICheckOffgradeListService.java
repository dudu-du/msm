package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDay;
import com.safety.entity.CheckOffgradeList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日治理记录 服务类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface ICheckOffgradeListService extends IService<CheckOffgradeList> {
    PageInfo<CheckOffgradeList> getByPage(Integer currentPage, Integer pageSize, String orgId);

    /**
     * 按事故类型统计否的检查记录
     * @param orgId
     * @param checkType
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getOffgradeTroubleCountByOrg(String orgId, String checkType,
                            LocalDate startTime, LocalDateTime endTime) throws Exception;

    /**
     * 按风险等级统计否的检查记录
     * @param orgId
     * @param checkType
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getOffgradeLevelCountByOrg(String orgId, String checkType,
                            LocalDate startTime, LocalDateTime endTime) throws Exception;

    /**
     * 按事故类型统计否的检查记录
     * @param orgId
     * @param checkType
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> getOffgradeHarmfulCountByOrg(String orgId, String checkType,
                                                          LocalDate startTime, LocalDateTime endTime) throws Exception;
}
