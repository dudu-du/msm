package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.dto.ClassInfoResult;
import com.welsee.dto.EvaluationGradleResult;
import com.welsee.entity.Class;
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
 * @since 2018-11-14
 */
public interface ClassMapper extends BaseMapper<Class> {


    /**
     * 批量建班
     *
     * @param classList 班级list
     * @return boolean
     */
    boolean insertClassBatch(List<Class> classList);

    /**
     * 新建一个班级
     *
     * @param aClass 要建的班级信息
     * @return boolean
     */
    boolean insertClass(Class aClass);


    /**
     * 通过班级号查询班级信息
     *
     * @param classNumber 班级号
     * @param orgId       机构id
     * @return class
     */
    Class selectByClassNumber(String classNumber, String orgId);


    /**
     * 获取该学段下的年级数量,用于计算该学段能建班的最小入学年份
     *
     * @param dictCode 学段
     * @return int
     */
    int getSumGradeByDictCode(String dictCode);

    /**
     * 获取学校下的班级总数
     *
     * @param map 学校和当前学年
     * @return int
     */
    int selectCountByOrgIdAndSchoolYear(Map<String, Object> map);

    /**
     * 通过机构id(学校id)获取其下的班级列表
     *
     * @param map map
     * @return list
     */
    List<ExtClass> getClassListByOrgIdAndPageAndSchoolYear(Map<String, Object> map);

    /**
     * 获取模糊查询下的学校班级总数量
     *
     * @param map 学校和班级号和当前学年
     * @return int
     */
    int selectCountByOrgIdAndClassNumberLike(Map<String, Object> map);

    /**
     * 通过模糊班级号和学校id获取班级列表
     *
     * @param map map
     * @return
     */
    List<ExtClass> getClassLisLikeBySectionAndGrade(Map<String, Object> map);


    /**
     * 获取该班级所在学校的的具体信息
     *
     * @return
     */
    List<ClassInfoResult> getClassInfoByClassNumberAndOrgId(Map<String, Object> map);

    /**
     * 获取班级信息
     *
     * @param map
     * @return
     */
    List<ClassInfoResult> getClassInfoByIds(Map<String, Object> map);


    /**
     * 删除班级列表
     *
     * @param map
     * @return
     */
    int deleteByIds(Map<String, Object> map);

    /**
     * 通过学段和机构id获取班级信息
     *
     * @param map
     * @return
     */
    List<ClassInfoResult> getClassInfoBySectionAndOrg(Map<String, Object> map);

    /**
     * 获取班级信息
     *
     * @param map
     * @return
     */
    List<Class> getPublicClassList(Map<String, Object> map);

    /**
     *获取班级信息
     * @param classToken
     * @return
     */
    ClassInfoResult getClassByToken(@Param("classToken")String classToken,@Param("schoolYear") Integer schoolYear);

    List<Class> getClassInfoNoPage(@Param("orgId") String orgId);

    List<EvaluationGradleResult> getGradeSectionClassNumber(Map<String, Object> map);
}
