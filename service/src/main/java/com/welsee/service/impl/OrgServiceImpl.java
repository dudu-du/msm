package com.welsee.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welsee.entity.Org;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtOrg;
import com.welsee.mapper.OrgMapper;
import com.welsee.service.IOrgService;
import com.welsee.tools.DictConstants;
import com.welsee.tools.HanyupinyinUtil;
import com.welsee.tools.PageData;
import com.welsee.tools.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
@Service
@Slf4j
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Override
    public PageData getOrgList(int page, int pageSize, String orgType, String parentId) throws Exception {
        if (orgType == null || parentId == null) {
            throw new ProgramException("参数错误！");
        }
        Org org = new Org();
        org.setOrgType(orgType);
        org.setParentId(parentId);
        //获取满足条件机构总数量
        int count = baseMapper.selectCount(org);
        //从第几个开始
        int start = PageData.calcFirstItemIndexOfPage(page, pageSize, count);
        log.info("从第几个开始=================" + start);


        Map<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("pageSize", pageSize);
        map.put("orgType", orgType);
        map.put("parentId", parentId);

        //获取应用的分页的数据
        List<Org> clientList = baseMapper.getOrgListByPage(map);
        return new PageData(page, count, pageSize, clientList);
    }

    @Override
    public List<Org> getOrgList(String orgType, String parentId) throws Exception {
        if (orgType == null || parentId == null) {
            throw new ProgramException("参数错误！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("orgType", orgType);
        map.put("parentId", parentId);

        //获取应用的分页的数据
        List<Org> clientList = baseMapper.getOrgListByPage(map);
        return clientList;
    }


    @Cacheable(value = "getAllOrgList", key = "#parentId")
    @Override
    public List getAllOrgList(String parentId) throws Exception {
        if (parentId == null || "".equals(parentId)) {
            throw new ProgramException("参数错误！");
        }
        List<ExtOrg> list = baseMapper.getOrgList(parentId);
        return list;
    }

    @Override
    public List getDepartmentList(String parentId) throws Exception {
        if (parentId == null || "".equals(parentId)) {
            throw new ProgramException("参数错误！");
        }
        List<ExtOrg> extOrgList = baseMapper.getExtOrgList(parentId);
        if(extOrgList.size()>0){
            ExtOrg extOrg = extOrgList.get(0);
            List<ExtOrg> list = baseMapper.getDepartmentList(parentId);
            if(DictConstants.ORGTYPE_DEPARTMENT.equals(extOrg.getOrgType())){//如果父类是部门
                return list;
            }else {
                extOrg.setChildren(list);
            }
        }else{
            throw new ProgramException("没有该机构信息！");
        }
        return extOrgList;
    }

    @Override
    public List getDepartmentRow(String parentId) throws Exception {
        if (parentId == null || "".equals(parentId)) {
            throw new ProgramException("参数错误！");
        }
        Org org = baseMapper.selectById(parentId);
        List<Org> orgList = baseMapper.getOrgRow(parentId);
        List list = new ArrayList();
        list.add(org);
        list.addAll(orgList);
        return list;
    }

    @Override
    public List getAllOrgListByType(String orgType) throws Exception {
        if (orgType == null || "".equals(orgType)) {
            throw new ProgramException("参数错误！");
        }
        List<Org> list = baseMapper.getOrgListByType(orgType);
        return list;
    }

    @Override
    public Org getOrgById(String orgId) throws Exception {
        if (orgId == null || "".equals(orgId)) {
            throw new ProgramException("参数错误！");
        }
        Org org = baseMapper.selectById(orgId);
        return org;
    }

    @Override
    public Org getOrgByCode(String code) throws Exception {
        if (code == null || "".equals(code)) {
            throw new ProgramException("参数错误！");
        }
        Org org = new Org();
        org.setCode(code);
        List<Org> list = baseMapper.getOrg(org);
        if (list.size() > 0) {
            return list.get(0);
        }
        return org;
    }

    @Override
    public Org getSchoolByName(String name) throws Exception {
        if (name == null || "".equals(name)) {
            throw new ProgramException("参数错误！");
        }
        Org org = new Org();
        org.setName(name);
        org.setOrgType(DictConstants.ORGTYPE_SCHOOL);
        List<Org> list = baseMapper.getOrg(org);
        if (list.size() > 0) {
            return list.get(0);
        }
        return org;
    }

    @Caching(evict = {@CacheEvict(value = "getAllOrgList", allEntries = true), @CacheEvict(value = "getOrgListBystr", allEntries = true)})
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean addOrg(String orgType, String code, String name, String simpleName, String parentId,
                          String remarksType,Integer sort,String domainName) throws Exception {
        if (orgType == null || "".equals(orgType) || code == null || "".equals(code) || name == null || "".equals(name) || parentId == null || "".equals(parentId)) {
            throw new ProgramException("参数错误！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        List<Org> orgList = baseMapper.getOrgListByPage(map);
        Org org = new Org();
        String orgId = UUIDUtil.getUUID();
        org.setId(orgId);
        if (DictConstants.ORGTYPE_DEPARTMENT.equals(orgType)) {//如果是部门
            if ("0".equals(parentId)) {
                throw new ProgramException("添加失败，部门必须要有父级！");
            }
            Org org1 = baseMapper.selectById(parentId);
            if (!DictConstants.ORGTYPE_DEPARTMENT.equals(org1.getOrgType())) {//父类ID不是部门
                org.setDepartmentId(org1.getId());
            } else {
                org.setDepartmentId(org1.getDepartmentId());
            }
            org.setOrgIds(org1.getOrgIds() + orgId + ",");
            org.setCode("");//部门没有code值
        } else {
            if (orgList.size() > 0) {
                throw new ProgramException("添加失败，机构编码已存在！");
            }
            if ("0".equals(parentId)) {//顶级机构
                org.setOrgIds(orgId+",");
            } else {//添加父类机构串，查询用
                Org org1 = baseMapper.selectById(parentId);
                org.setOrgIds(org1.getOrgIds() + orgId + ",");
            }
            org.setCode(code);
        }
        org.setParentId(parentId);
        org.setOrgType(orgType);
        org.setName(name);
        org.setSort(sort);
        org.setDomainName(domainName);
        org.setFirstPy(HanyupinyinUtil.getFirstLetters(name));
        org.setFullPy(HanyupinyinUtil.toHanyuPinyin(name));
        if (remarksType == null) {
            remarksType = "";
        }
        org.setRemarksType(remarksType);
        org.setSimpleName(simpleName);
        org.setCreatedatetime(LocalDateTime.now());
        org.setModifydatetime(LocalDateTime.now());
        org.setDel(0);
        int insert = baseMapper.insert(org);
        log.info("添加应用影响条数====================" + insert);
        return insert > 0;
    }

    @Caching(evict = {@CacheEvict(value = "getSchoolMap", key = "#orgId"), @CacheEvict(value = "getSchoolMapBydomainName", allEntries = true), @CacheEvict(value = "getAllOrgList", allEntries = true), @CacheEvict(value = "getOrgListBystr", allEntries = true)})
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updateOrg(String orgId, String orgType, String code, String name, String simpleName, String parentId,
                             String remarksType, Integer sort,String domainName) throws Exception {
        if (orgId == null || "".equals(orgId)) {
            throw new ProgramException("参数错误！");
        }
        Org oldOrg = baseMapper.selectById(orgId);
        Org parentOrg = baseMapper.selectById(parentId);
        Org org = new Org();
        if (DictConstants.ORGTYPE_DEPARTMENT.equals(oldOrg.getOrgType())) {//如果是部门不判断code
        }else{
            if (code != null && !"".equals(code)) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", code);
                List<Org> orgList = baseMapper.getOrgListByPage(map);
                if (orgList.size() > 0) {
                    int i = 0;
                    for (Org orgs : orgList) {
                        if (orgs.getId().equals(orgId)) {
                            i = 1;
                            break;
                        }
                    }
                    if (i == 0) {
                        throw new ProgramException("修改失败，机构编码已存在！");
                    }
                }
                org.setCode(code);
            }
        }
        org.setId(orgId);
        if (name != null && !"".equals(name)) {
            org.setName(name);
            org.setFirstPy(HanyupinyinUtil.getFirstLetters(name));
            org.setFullPy(HanyupinyinUtil.toHanyuPinyin(name));
        }
        if (simpleName != null && !"".equals(simpleName))
            org.setSimpleName(simpleName);
        if(parentId!=null||!"".equals(parentId)){
            if(!parentId.equals(oldOrg.getParentId())){//更换父类id组
//                org.setOrgIds(oldOrg.getOrgIds().replace(oldOrg.getParentId(),parentId));
                List<Org> orgList = baseMapper.getOrgChildList(oldOrg.getId());
                for(Org org1 : orgList){
                    String oldstr = org1.getOrgIds();
                    String str = parentOrg.getOrgIds();
                    String newstr = str + oldstr.substring(oldstr.indexOf(oldOrg.getParentId())+oldOrg.getParentId().length()+1);
                    org1.setOrgIds(newstr);
                    org1.setModifydatetime(LocalDateTime.now());
                    baseMapper.updateById(org1);
                }
            }
            org.setParentId(parentId);
        }
        if (orgType != null && !"".equals(orgType))
            org.setOrgType(orgType);
        if (remarksType != null && !"".equals(remarksType))
            org.setRemarksType(remarksType);
        if (sort != null && !"".equals(sort))
            org.setSort(sort);
        org.setDomainName(domainName);
        org.setModifydatetime(LocalDateTime.now());
        int update = baseMapper.updateById(org);
        log.info("修改机构影响条数====================" + update);
        return update > 0;
    }


    @Caching(evict = {@CacheEvict(value = "getSchoolMap", key = "#orgId"), @CacheEvict(value = "getSchoolMapBydomainName", allEntries = true), @CacheEvict(value = "getAllOrgList", allEntries = true), @CacheEvict(value = "getOrgListBystr", allEntries = true)})
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean delOrg(String orgId) throws Exception {
        if (orgId == null || "".equals(orgId)) {
            throw new ProgramException("参数错误！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", orgId);
        //获取应用的分页的数据
        List<Org> clientList = baseMapper.getOrgListByPage(map);
        if (clientList.size() > 0) {
            throw new ProgramException("名下有机构不能删除！");
        }
//        if (personService.checkOrgHasPerson(orgId)) {
//            throw new ProgramException("机构下存在人员不能删除！");
//        }
//        if (personService.checkDepartmentHasPerson(orgId)) {
//            throw new ProgramException("部门下存在人员不能删除！");
//        }
        int i = baseMapper.deleteById(orgId, LocalDateTime.now());
        return i > 0;
    }

    @Cacheable(value = "getOrgListBystr")
    @Override
    public List<Org> getOrgListBystr(String orgType, String str) throws Exception {
        if (orgType == null || str == null) {
            throw new ProgramException("参数错误！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("orgType", orgType);
        map.put("str", str);

        //获取应用的分页的数据
        List<Org> clientList = baseMapper.getOrgListByStr(map);
        return clientList;
    }

}
