package com.welsee.mapper;

import com.welsee.dto.DeviceTime;
import com.welsee.entity.DeviceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExtDeviceInfo;
import com.welsee.extentity.ExtDeviceTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-09
 */
public interface DeviceInfoMapper extends BaseMapper<DeviceInfo> {

    List<DeviceInfo> getDeviceList(DeviceInfo deviceInfo);


    /**
     * 获取学校下的班牌设备
     *
     * @param orgId 机构id
     * @return
     */
    List<ExtDeviceInfo> getWisdomPlateList(@Param("orgId") String orgId);


    /**
     * 获取人员名下的校牌设备列表
     *
     * @param map
     * @return
     */
    List<DeviceInfo> getUserDeviceList(Map<String, Object> map);


    void delDeviceSchoolMap(Map<String, Object> map);

    void delDeviceClassMap(Map<String, Object> map);

    DeviceInfo getDeviceInfoByDeviceId(@Param("deviceId") String deviceId);


    String getDeviceTimeAmTimeOff(Map<String, Object> map);

    String getDeviceTimePmTimeOn(Map<String, Object> map);

    String getDeviceTimePmTimeOff(Map<String, Object> map);

    String getDeviceTimeAmTimeOn(Map<String, Object> map);

    List<DeviceTime> getDeviceTimePlainList(Map<String, Object> map);

    ExtDeviceTime getDeviceTimeType(Map<String, Object> map);

}
