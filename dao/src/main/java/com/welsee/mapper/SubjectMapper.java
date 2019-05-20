package com.welsee.mapper;

import com.welsee.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.extentity.ExSubjectTeacher;
import com.welsee.extentity.ExtSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-27
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     * (添加科目)判断该科目是否已经存在
     *
     * @param map
     * @return
     */
    boolean isSubjectExit(Map<String, Object> map);

    /**
     * (查询科目)超级管理员角色下的科目总数
     *
     * @return
     */
    int selectSupCountByRole();


    /**
     * (查询)通过校级管理员下的科目数量
     *
     * @return
     */
    int selectCountBySectionCode(Map<String, Object> map);

    /**
     * (查询)通过不同角色获取不同的科目列表
     *
     * @param map
     * @return
     */
    List<Subject> getSubjectByPage(Map<String, Object> map);

    /**
     * 批量删除科目
     *
     * @param map
     * @return
     */
    int deleteByIds(Map<String, Object> map);

    /**
     * 获取学校下所有科目
     *
     * @param schoolId
     * @return
     */
    List<ExtSubject> getSubjectBySchoolId(String schoolId);

    /**
     * 通过学校id和学段获取学校下的科目
     *
     * @param map
     * @return
     */
    List<Subject> getSubjectListInfoNoPage(Map<String, String> map);

    /**
     * 根据机构id和班级号获取该班级下的科目和对应的教师
     *
     * @param map
     * @return
     */
    List<ExSubjectTeacher> getSubjectAndTeacherByOrgIdAndClassNumber(Map<String, Object> map);

    /**
     * 获取班级下科目名称失败
     *
     * @param map
     * @return
     */
    List<ExSubjectTeacher> getSubjectNameByOrgIdAndClassNumber(Map<String, Object> map);

    /**
     * 获取科目
     * @param ids
     * @return
     */
    List<Subject> getSubject(@Param("ids") String ids);
}
