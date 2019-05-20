package com.safety.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.safety.entity.*;
import com.safety.exception.ProgramException;
import com.safety.extentity.*;
import com.safety.mapper.PersonMapper;
import com.safety.service.IOrgService;
import com.safety.service.IPersonService;
import com.safety.tools.DictConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author safety
 * @since 2018-11-29
 */
@Service
@Slf4j
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    @Autowired
    private IOrgService orgService;

    @Override
    public ExtPerson selectByLoginId(String loginId) throws ProgramException {
        ExtPerson person = baseMapper.getPersonByLoginId(loginId);
        if (person != null) {
            List<String> roleList = baseMapper.getRoleListByLid(loginId);
            person.setRoleList(roleList);
            List<String> permissionList = baseMapper.getPermssionListByLid(loginId);
            person.setPermissionList(permissionList);
        } else {
            throw new ProgramException("用户不存在");
        }
        return person;
    }

    @Cacheable(value = "getRoleListByUId", key = "#userId")
    @Override
    public List<String> getRoleListByUId(String userId) throws Exception {
        List<String> roleList = baseMapper.getRoleListByUId(userId);
        return roleList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveEntity(ExtPerson entity) throws ProgramException {
        if (entity.getOrgId() == null || "".equals(entity.getOrgId())) {
            throw new ProgramException("机构ID不能为空");
        }
        if (entity.getPersonType() != null && DictConstants.PERSON_STUDENT.equals(entity.getPersonType())
                && (entity.getClassNumber() == null || "".equals(entity.getClassNumber()))) {
            throw new ProgramException("班级不能为空");
        }
        if (super.save(entity)) {
            return true;
        }
        throw new ProgramException("用户保存失败");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateEntityById(ExtPerson entity) throws ProgramException {
        if (entity.getId() == null || "".equals(entity.getId())) {
            throw new ProgramException("参数不合法");
        }
        if (super.updateById(entity)) {
            return true;
        }
        throw new ProgramException("用户修改失败");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean removeByIdStr(String id) throws ProgramException {
        return baseMapper.removeEntityById(id, LocalDateTime.now());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean saveEntityBatch(List<ExtPerson> entityList) throws ProgramException {
        List<Person> personList = new ArrayList<>();
        entityList.stream().forEach(extPerson -> {
            Person person = new Person();
            BeanUtils.copyProperties(extPerson, person);
            personList.add(person);
        });
        return baseMapper.saveOrUpdateEntityBatch(personList);
    }

    public List<String> getLoginIdByPersonId(String personId) {
        return baseMapper.getLoginIdByPersonId(personId);
    }

    @CacheEvict(value = "getRoleListByUId", key = "#userId")
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean addRoleByUser(String userId, String role) throws Exception {
        if (userId == null || role == null || "".equals(userId)) {
            throw new ProgramException("参数不合法");
        }
        deleteRoleByUser(userId);
        if (!StringUtils.isEmpty(role)) {
            String[] roleArr = role.split(",");
            List list = Arrays.asList(roleArr);
            Map<String, Object> conditon = new HashMap<>();
            conditon.put("user", userId);
            conditon.put("roles", list);
            baseMapper.addRoleByUser(conditon);
        }
        return true;
    }

    @CacheEvict(value = "getRoleListByUId", key = "#userId")
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteRoleByUser(String userId) throws Exception {
        if (userId == null || "".equals(userId)) {
            throw new ProgramException("参数不合法");
        }
        baseMapper.deleteRoleByUser(userId);
        return true;
    }
}
