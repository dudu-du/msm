package com.welsee.mapper;

import com.welsee.entity.Semester;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-14
 */
public interface SemesterMapper extends BaseMapper<Semester> {


    /**
     * 获取当前学期
     *
     * @return semester
     */
    Semester getSchoolYearByCurrentSemester();


    /**
     * 获取分页的学期列表
     *
     * @param map
     * @return list
     */
    List<Semester> getSemesterListByPage(Map<String, Object> map);

    /**
     * 获取学期列表总数量
     *
     * @return int
     */
    int selectCount();

    /**
     * 将非id的学期设置为非当前学期
     *
     * @param id
     */
    void updateIsCurrentSemester(String id);

    /**
     * 获取一个学期的详细信息
     *
     * @param semesterId 学期id
     * @return semester
     */
    Semester selectById(String semesterId);

    /**
     * 删除学期(支持批量)
     *
     * @param map
     * @return
     */
    int deleteByIds(Map<String, Object> map);

    /**
     * 判断该学期是否是当前学期
     *
     * @param id 学期id
     * @return
     */
    boolean isCurrentSemester(@Param("id") String id);

    /**
     * 查询新建的学期是否已经存在
     *
     * @param map
     * @return
     */
    int isExistSemester(Map<String, Object> map);

    Semester getSemesterCurrent();
}
