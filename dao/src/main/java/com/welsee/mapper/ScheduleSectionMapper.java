package com.welsee.mapper;

import com.welsee.entity.ScheduleSection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-18
 */
public interface ScheduleSectionMapper extends BaseMapper<ScheduleSection> {

    /**
     * 批量保存/更新课表
     * @param scheduleSections
     * @return
     */
    boolean saveScheduleSectionBatch(List<ScheduleSection> scheduleSections);

    /**
     * 获取课表
     * @param schoolId      学校ID
     * @param classNumber   班级编号，多个逗号分隔，不传则返回学校所有班级课表
     * @return
     */
    List<ScheduleSection> getScheduleSection(@Param("schoolId")String schoolId,@Param("classNumber")String classNumber);


    /**
     * 删除课表
     * @param schoolId      学校ID
     * @param classNumber   [可选]班级编号，多个逗号分隔
     * @return
     */
    boolean removeSchedule(@Param("schoolId")String schoolId,@Param("classNumber")String classNumber);

    /**
     * 修改课表
     * @param schoolId
     * @param teacherId
     * @param section
     * @param sectionCourse
     * @return
     */
    boolean updateSchedule(@Param("schoolId") String schoolId,
                           @Param("classNumber")String teacherId,
                           @Param("section")String section,
                           @Param("sectionCourse")String sectionCourse,
                           @Param("modifydatetime")LocalDateTime modifydatetime);

    /**
     * 删除课表中的科目
     * @param schoolId      学校ID
     * @param subjectNames  科目名称，多个逗号分隔
     * @return
     */
    boolean removeSubject(@Param("schoolId")String schoolId,@Param("subjectNames")String subjectNames);
}
