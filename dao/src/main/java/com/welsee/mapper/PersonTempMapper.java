package com.welsee.mapper;

import com.welsee.entity.PersonTemp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExtPerson;
import com.welsee.extentity.ExtReuseRoutine;
import com.welsee.extentity.ExtReuseStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-12-18
 */
public interface PersonTempMapper extends BaseMapper<PersonTemp> {
    /**
     * 获取登陆名验证记录
     * @param uuid
     * @return
     */
    List<ExtReuseRoutine> getVerifyRecord(@Param("uuid") String uuid);

    /**
     * 获取合并后的数据(临时表与person表数据合并)
     * @param uuid
     * @return
     */
    List<ExtPerson> getCompleteData(@Param("uuid") String uuid);

    /**
     * 获取登陆名重复的学生
     * @param uuid
     * @return
     */
    List<ExtReuseStudent> getVerifyStudentRecord(@Param("uuid") String uuid);

}
