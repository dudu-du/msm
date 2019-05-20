package com.welsee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.welsee.dto.user.*;
import com.welsee.entity.Class;
import com.welsee.entity.Person;
import com.welsee.entity.Student;
import com.welsee.entity.Teacher;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtPerson;
import com.welsee.tools.PageData;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author welsee
 * @since 2018-11-29
 */
public interface IPersonService extends IService<Person> {

    ExtPerson selectByLoginId(String loginId) throws ProgramException;

    List<String> getRoleListByUId(String userId) throws Exception;
    /**
     * 注意此方法会走切面类：com.welsee.aspect.PersonAspect
     *
     * @param entity
     * @return
     * @throws ProgramException
     */
    boolean saveEntity(ExtPerson entity) throws ProgramException;

    /**
     * 注意此方法会走切面类：com.welsee.aspect.PersonAspect
     *
     * @param entity
     * @return
     * @throws ProgramException
     */
    boolean updateEntityById(ExtPerson entity) throws ProgramException;

    /**
     * 根据用户ID删除
     * 注意此方法会走切面类：com.welsee.aspect.PersonAspect
     *
     * @param id 用户Id 多个由','号分隔
     * @return
     * @throws ProgramException
     */
    boolean removeByIdStr(String id) throws ProgramException;

    /**
     * 注意此方法会走切面类：com.welsee.aspect.PersonAspect
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
}
