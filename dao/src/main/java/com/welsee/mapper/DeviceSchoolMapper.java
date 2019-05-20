package com.welsee.mapper;

import com.welsee.entity.DeviceSchool;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2019-02-14
 */
public interface DeviceSchoolMapper extends BaseMapper<DeviceSchool> {

    /**
     * 删除一个用户下的校牌关系
     *
     * @param userId
     * @return
     */
    int deleteSchoolPlateMapByUserId(@Param("userId") String userId);

    /**
     * 删除多个用户下的校牌关系
     *
     * @param userIdsDel
     * @return
     */
    int deleteSchoolPlateMapByUserIds(@Param("userIdsDel") String userIdsDel);

    /**
     * 获取校牌分配列表总数量
     *
     * @param hashMap
     * @return
     */
    int getSchoolPlateMapListCount(Map<String, Object> hashMap);

    /**
     * 获取校牌分配列表总信息
     *
     * @param map
     * @return
     */
    List<ExtClass> getSchoolPlateMapListInfo(Map<String, Object> map);

    /**
     * 获取校牌分配的信息
     *
     * @param map
     * @return
     */
    List<DeviceSchool> getDeviceSchoolInfo(Map<String, Object> map);
}
