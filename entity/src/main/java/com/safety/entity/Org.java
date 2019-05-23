package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author safety
 * @since 2018-11-22
 */
public class Org extends Model<Org> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 机构类型
     */
    private String orgType;

    private String code;

    private String name;

    /**
     * 简称
     */
    private String simpleName;

    /**
     * 拼音简拼
     */
    private String firstPy;

    /**
     * 拼音全拼
     */
    private String fullPy;

    private String parentId;

    /**
     * 所有父类机构ID，查询用
     */
    private String orgIds;

    /**
     * 部门所属的机构名
     */
    private String departmentId;

    /**
     * 补充类型，比如学校类型
     */
    private String remarksType;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 负责人
     */
    private String header;

    /**
     * 成员
     */
    private String worker;

    /**
     * 排序
     */
    private Integer sort;

    private Integer del;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdatetime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }
    public String getFirstPy() {
        return firstPy;
    }

    public void setFirstPy(String firstPy) {
        this.firstPy = firstPy;
    }
    public String getFullPy() {
        return fullPy;
    }

    public void setFullPy(String fullPy) {
        this.fullPy = fullPy;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String orgIds) {
        this.orgIds = orgIds;
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getRemarksType() {
        return remarksType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public void setRemarksType(String remarksType) {
        this.remarksType = remarksType;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }
    public LocalDateTime getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(LocalDateTime modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Org{" +
        "id=" + id +
        ", orgType=" + orgType +
        ", code=" + code +
        ", name=" + name +
        ", simpleName=" + simpleName +
        ", firstPy=" + firstPy +
        ", fullPy=" + fullPy +
        ", parentId=" + parentId +
        ", orgIds=" + orgIds +
        ", departmentId=" + departmentId +
        ", remarksType=" + remarksType +
        ", domainName=" + domainName +
        ", sort=" + sort +
        ", del=" + del +
        ", createdatetime=" + createdatetime +
        ", modifydatetime=" + modifydatetime +
        "}";
    }
}
