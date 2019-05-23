package com.safety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.dto.user.OfficeAdminParams;
import com.safety.dto.user.SchoolAdminParams;
import com.safety.dto.user.TeacherParams;
import com.safety.entity.OfficeAdmin;
import com.safety.entity.Person;
import com.safety.entity.Teacher;
import com.safety.extentity.ExtPerson;
import com.safety.extentity.ExtPersonDepartment;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2018-11-29
 */
public interface PersonMapper extends BaseMapper<Person> {

    ExtPerson getPersonByLoginId(String loginId);

    /**
     * 获取用户角色集合
     * params loginId
     *
     * @return
     */
    List<String> getRoleListByLid(@Param("loginId") String loginId);

    /**
     * 获取用户角色集合
     * params uId
     *
     * @return
     */
    List<String> getRoleListByUId(@Param("uId") String uId);

    /**
     * 获取用户权限集合
     * params loginId
     *
     * @return
     */
    List<String> getPermssionListByLid(@Param("loginId") String loginId);

    /**
     * 查询用户登陆ID
     *
     * @param id 用户ID，多个以','号分隔
     * @return
     */
    List<String> getLoginIdByPersonId(@Param("id") String id);

    /**
     * 删除/禁用（支持批量）
     *
     * @param id 多个ID，','号分隔
     * @return
     */
    boolean removeEntityById(@Param("id") Serializable id, @Param("modifydatetime") LocalDateTime localDateTime);

    boolean saveOrUpdateEntityBatch(List<Person> personList);

    /**
     * 增加角色权限关系
     *
     * @param map
     * @return
     */
    void addRoleByUser(Map<String, Object> map);

    /**
     * 删除某Person的所有角色
     *
     * @param userId
     * @return list
     */
    void deleteRoleByUser(@Param("userId") String userId);

    /**
     * 修改班主任
     *
     * @param teacherId
     * @param classId
     * @return
     */
    int updateMaster(@Param("teacherId") String teacherId, @Param("classId") String classId,
                     @Param("modifydatetime") LocalDateTime localDateTime);

    /**
     * 移除班主任，支持批量
     *
     * @param teacherId 多个由','号分隔
     * @return
     */
    boolean removeMaster(@Param("id") String teacherId, @Param("modifydatetime") LocalDateTime localDateTime);

    /**
     * 获取人员信息
     *
     * @param personId
     * @return
     */
    ExtPerson getPersonInfoById(@Param("id") String personId);

    /**
     * 获取卡号数量
     *
     * @param cardNo
     * @return
     */
    int getCardNoCount(@Param("cardNo") String cardNo);

    /**
     * 获取所有部门ID
     *
     * @param departmentId 部门ID
     * @return
     */
    List<String> getDepartmentAllByDepartmentId(@Param("departmentId") String departmentId);

    /**
     * 获取人员数量(含机构)
     *
     * @param orgId 机构ID
     * @return
     */
    int getUserWithDepartmentCount(@Param("orgId") String orgId);

    /**
     * 获取人员(含机构)
     *
     * @param orgId
     * @param start
     * @param pageSize
     * @return
     */
    List<ExtPersonDepartment> getUserWithDepartment(@Param("orgId") String orgId,
                                                    @Param("start") Integer start,
                                                    @Param("pageSize") Integer pageSize);

    /**
     * 获取人员数量
     *
     * @param orgId 机构ID
     * @return
     */
    int getPersonCountByOrgId(@Param("orgId") String orgId);

    /**
     * 获取部门人员数量
     *
     * @param departmentId
     * @return
     */
    int getPersonCountByDepartmentId(@Param("departmentId") String departmentId);

    /**
     * 获取部门人员数量
     *
     * @param departmentId 部门ID
     * @return
     */
    int getDepartmentUserCount(@Param("departmentId") String departmentId);

    /**
     * 获取部门已存在的用户记录
     *
     * @param departmentId 部门ID
     * @param userId       用户ID
     * @return
     */
    List<String> getExistsUser(@Param("departmentId") String departmentId, @Param("userId") String userId);

    /**
     * 获取用户已存在的部门
     *
     * @param departmentId
     * @param userId
     * @return
     */
    List<String> getExistsDepartment(@Param("departmentId") String departmentId, @Param("userId") String userId);

    /**
     * 获取部门人员
     *
     * @param departmentId 部门ID
     * @param start        开始页
     * @param pageSize     每页显示多少条
     * @return
     */
    List<ExtPersonDepartment> getDepartmentUserList(@Param("departmentId") String departmentId,
                                                    @Param("start") Integer start,
                                                    @Param("pageSize") Integer pageSize);

    /**
     * 添加部门-人员关系
     *
     * @param departmentUsers
     * @return
     */
    boolean addDepartmentUser(Map<String, Object> departmentUsers);

    /**
     * 添加人员-部门关系
     *
     * @param departmentUsers
     * @return
     */
    boolean addUserDepartment(Map<String, Object> departmentUsers);

    /**
     * 移除部门-人员关系
     *
     * @param department
     * @param userId
     * @return
     */
    boolean removeDepartmentUser(@Param("departmentId") String department, @Param("userId") String userId);

    /**
     * 重置机构部门人员
     *
     * @param orgId 机构ID，多个逗号分隔
     * @return
     */
    boolean removeOrgDepartmentUser(@Param("orgId") String orgId);

    /**
     * 检查用户是否为班主任
     *
     * @param userId
     * @return
     */
    int isMaster(@Param("userId") String userId);

    /**
     * 检查用户做为班主任所带的班级
     *
     * @param userId
     * @return
     */
    List<Class> getClassByMaster(@Param("userId") String userId);

    /**
     * 获取学生的ID
     *
     * @param studentName 学生名字
     * @param cardNumber  学生卡号
     * @return
     */
    Map getStudent(@Param("studentName") String studentName, @Param("cardNumber") String cardNumber);

    /**
     * 获取学生信息
     *
     * @param schoolId
     * @param classNumber 多个逗号分隔
     * @return
     */
    List<ExtPerson> getStudentListByClassNoPage(@Param("schoolId") String schoolId,
                                                @Param("classNumber") String classNumber,
                                                @Param("studentName") String studentName);

    /**
     * 获取班主任
     *
     * @param userId 用户id,多个逗号分隔
     * @return
     */
    List<ExtPerson> getMasterByUserId(@Param("userId") String userId);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    Map<String, Object> getUserInfo(@Param("userId") String userId);

    Map<String,Object> getUserInfoHasPassword(@Param("loginName") String loginName);

    Map<String,Object> getUserInfoByName(@Param("loginName") String loginName);

    /**
     * 局管理员列表
     *
     * @param officeAdminParams
     * @return
     */
    int getOfficeAdminCount(OfficeAdminParams officeAdminParams);

    List<OfficeAdmin> getOfficeAdminList(OfficeAdminParams officeAdminParams);

    /**
     * 校管理员列表
     *
     * @param schoolAdminParams
     * @return
     */
    int getSchoolAdminCount(SchoolAdminParams schoolAdminParams);

    List<OfficeAdmin> getSchoolAdminList(SchoolAdminParams schoolAdminParams);

    /**
     * 教师列表
     *
     * @param teacherParams
     * @return
     */
    int getTeacherCount(TeacherParams teacherParams);

    List<Teacher> getTeacherList(TeacherParams teacherParams);
}
