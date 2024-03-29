package com.safety.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.safety.dto.user.OrgAdminParams;
import com.safety.dto.user.SchoolAdminParams;
import com.safety.dto.user.WorkerParams;
import com.safety.entity.Person;
import com.safety.entity.Worker;
import com.safety.exception.ProgramException;
import com.safety.extentity.ExtPerson;
import com.safety.tools.PageData;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.session.Session;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author safety
 * @since 2018-11-29
 */
public interface IPersonService extends IService<Person> {

    ExtPerson selectByLoginId(String loginId) throws ProgramException;

    List<String> getRoleListByUId(String userId) throws Exception;
    /**
     * 注意此方法会走切面类：PersonAspect
     *
     * @param entity
     * @return
     * @throws ProgramException
     */
    boolean saveEntity(ExtPerson entity) throws ProgramException;

    /**
     * 注意此方法会走切面类：PersonAspect
     *
     * @param entity
     * @return
     * @throws ProgramException
     */
    boolean updateEntityById(ExtPerson entity) throws ProgramException;

    /**
     * 根据用户ID删除
     * 注意此方法会走切面类：PersonAspect
     *
     * @param id 用户Id 多个由','号分隔
     * @return
     * @throws ProgramException
     */
    boolean removeByIdStr(String id) throws ProgramException;

    /**
     * 注意此方法会走切面类：PersonAspect
     *
     * @param entityList
     * @return
     * @throws ProgramException
     */
    boolean saveEntityBatch(List<ExtPerson> entityList) throws ProgramException;

    /**
     * 根据用户ID获取loginId
     *
     * @param personId
     * @return
     */
    List<String> getLoginIdByPersonId(String personId);

    /**
     * 插入person角色对应关系
     *
     * @param userId person Id
     * @param role   角色id，以,分隔
     * @return
     */
    public boolean addRoleByUser(String userId, String role) throws Exception;

    /**
     * 删除用户所属角色
     *
     * @param userId 用户id
     * @return
     */
    public boolean deleteRoleByUser(String userId) throws Exception;

    /**
     * 获取机构管理员列表
     *
     * @param orgAdminParams
     * @return
     */
    PageData getOrgAdminListByScope(OrgAdminParams orgAdminParams) throws ProgramException;

    /**
     * 获取校管理员列表
     *
     * @param schoolAdminParams
     * @return
     */
    PageData getSchoolAdminListByScope(SchoolAdminParams schoolAdminParams) throws ProgramException;

    /**
     * 获取教师列表
     *
     * @param workerParams
     * @return
     */
    PageData getWorkerListByScope(WorkerParams workerParams) throws ProgramException;

    /**
     * 获取教师列表(不带分页)
     *
     * @param workerParams
     * @return
     * @throws ProgramException
     */
    List<Worker> getWorkerListNoPage(WorkerParams workerParams) throws ProgramException;

    /**
     * 获取当前用户信息
     *
     * @param
     * @return
     * @throws Exception
     */
//    public static Person getCurrentPerson() {
//        Session session = SecurityUtils.getSubject().getSession();
//        Person person = (Person) session.getAttribute("MEMBER_USER_PERSON");
//        return person;
//    }
}
