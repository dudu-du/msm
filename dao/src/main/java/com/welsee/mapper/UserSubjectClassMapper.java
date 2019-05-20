package com.welsee.mapper;

import com.welsee.entity.UserSubjectClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExtClass;
import com.welsee.extentity.ExtResUserSubjectClass;
import com.welsee.extentity.ExtTeacherClass;
import com.welsee.extentity.ExtUserSubjectClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-21
 */
public interface UserSubjectClassMapper extends BaseMapper<UserSubjectClass> {

    /**
     * 获取科目人员对应关系
     *
     * @param schoolId    学校ID
     * @param classNumber 班级编号
     * @return
     */
    List<ExtUserSubjectClass> getSubjectUsers(@Param("schoolId") String schoolId,
                                              @Param("classNumber") String classNumber);

    /**
     * 获取科目人员对应关系
     * @param teacherId
     * @param classNumber
     * @return
     */
    List<ExtUserSubjectClass> getClassSubjectByTeacher(@Param("teacherId")String teacherId,@Param("classNumber")String classNumber);
    /**
     * 删除教师之前的关系
     *
     * @param map
     * @return
     */
    int deleteUserSubjectClassMap(Map<String, String> map);

    /**
     * 获取任课教师列表
     *
     * @param map
     * @return
     */
    List<ExtResUserSubjectClass> getUserSubjectClassList(Map<String, Object> map);

    /**
     * 通过id删除教师的关系
     *
     * @param deleteIds
     * @return
     */
    int deleteUserSubjectClassMapByIds(@Param("deleteIds") String deleteIds);

    /**
     * 通过用户ID删除教师关系
     * @param orgId
     * @param userIds
     * @return
     */
    int removeUserSubjectClassByUserId(@Param("orgId")String orgId,@Param("userIds")String userIds);
    /**
     * 获取任课教师总数
     *
     * @param hashMap
     * @return
     */
    int getUserSubjectClassCount(Map<String, Object> hashMap);

    /**
     * 筛选任课教师总总数
     *
     * @param hashMap
     * @return
     */
    int getUserSubjectClassCountListByFilter(Map<String, Object> hashMap);

    /**
     * 筛选任课教师总列表
     *
     * @param map
     * @return
     */
    List<ExtResUserSubjectClass> getUserSubjectClassListByFilter(Map<String, Object> map);

    /**
     * 模糊查询任课老师总数量
     *
     * @param hashMap
     * @return
     */
    int getUserSubjectClassCountListByLike(Map<String, Object> hashMap);

    /**
     * 模糊查询任课老师总列表
     *
     * @param map
     * @return
     */
    List<ExtResUserSubjectClass> getUserSubjectClassListByLike(Map<String, Object> map);

    List<ExtUserSubjectClass> getTeacherAddressList(Map<String, Object> map);

    List<UserSubjectClass> getUserSubjectClassListByParam(Map<String, Object> map);

    void deleteUserSubjectClassMapByClassInfo(Map<String, Object> delMap);

    /**
     * 教师的授课班级
     * @param userId
     * @param schoolYear
     * @return
     */
    List<ExtTeacherClass> getClassByTeacher(@Param("userId")String userId, @Param("schoolYear")Integer schoolYear);

    void deleteUserSubjectClassMapBySubjectId(Map<String, Object> map);
}
