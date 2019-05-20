package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.Class;
import com.welsee.entity.DeviceClass;
import com.welsee.entity.DeviceInfo;
import com.welsee.extentity.ExtClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-11
 */
public interface DeviceClassMapper extends BaseMapper<DeviceClass> {

    /**
     * 删除班牌分配
     *
     * @param map
     * @return
     */
    boolean delWisdomPlateMapByIds(Map<String, Object> map);

    /**
     * 判断分配的班牌是否已被分配班级了
     *
     * @param classId  班级id
     * @param deviceId 设备id
     * @return
     */
    DeviceClass isDistributionDevice(@Param("classId") String classId, @Param("deviceId") String deviceId);

    /**
     * 获取学校下的班级总数量
     *
     * @param hashMap
     * @return
     */
    int selectDeviceClassCountByOrgIdAndSchoolYear(Map<String, Object> hashMap);

    /**
     * 班牌分配列表(分页)
     *
     * @param map
     * @return
     */
    List<ExtClass> getDeviceClassListByOrgIdAndPageAndSchoolYear(Map<String, Object> map);

    /**
     * 通过设备id获取班级信息
     *
     * @param deviceId 设备id
     * @return
     */
    Class getClassInfoByDeviceId(@Param("deviceId") String deviceId);


    /**
     * 删除该班牌之前绑定的关系
     *
     * @param map
     */
    void delWisdomPlateMapByDeviceId(Map<String,Object> map);

    int getWisdomPlateMapByIdAndDeviceId(Map<String, Object> findMap);
}
