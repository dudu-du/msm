package com.welsee.mapper;

import com.welsee.entity.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExtSchool;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学校信息 Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-12-17
 */
public interface SchoolMapper extends BaseMapper<School> {
    ExtSchool getSchoolInfo(String orgId);
    Map getSchoolMap(String orgId);
    Map getSchoolMapBydomainName(String domainName);
    List<School> getSchool(School school);
    Integer deleteById(String schoolId, LocalDateTime time);
}
