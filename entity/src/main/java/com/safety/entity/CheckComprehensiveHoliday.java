package com.safety.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 综合检查(节假日、复产前)
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckComprehensiveHoliday extends Model<CheckComprehensiveHoliday> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 查负责人
     */
    private String personChargeFk;

    /**
     * 查负责人姓名
     */
    private String personChargeName;

    /**
     * 检查时间
     */
    private LocalDateTime checkTime;

    /**
     * 检查人ID字符串
     */
    private String checkPersonFk;

    /**
     * 检查人姓名字符串
     */
    private String checkPersonName;

    /**
     * 受检查部门ID
     */
    private String checkOrgFk;

    /**
     * 受检查部门名称
     */
    private String checkOrgName;

    /**
     * 参加人员ID字符串
     */
    private String joinPersonFk;

    /**
     * 参加人员姓名字符串
     */
    private String joinPersonName;

    /**
     * 表单所属机构ID
     */
    private String orgFk;

    /**
     * 表单所属机构名称
     */
    private String orgName;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 子表集合
     */
    @TableField(exist = false)
    private List<CheckComprehensiveHolidayList> checkComprehensiveHolidayList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getPersonChargeFk() {
        return personChargeFk;
    }

    public void setPersonChargeFk(String personChargeFk) {
        this.personChargeFk = personChargeFk;
    }
    public String getPersonChargeName() {
        return personChargeName;
    }

    public void setPersonChargeName(String personChargeName) {
        this.personChargeName = personChargeName;
    }
    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckPersonFk() {
        return checkPersonFk;
    }

    public void setCheckPersonFk(String checkPersonFk) {
        this.checkPersonFk = checkPersonFk;
    }
    public String getCheckPersonName() {
        return checkPersonName;
    }

    public void setCheckPersonName(String checkPersonName) {
        this.checkPersonName = checkPersonName;
    }
    public String getCheckOrgFk() {
        return checkOrgFk;
    }

    public void setCheckOrgFk(String checkOrgFk) {
        this.checkOrgFk = checkOrgFk;
    }
    public String getCheckOrgName() {
        return checkOrgName;
    }

    public void setCheckOrgName(String checkOrgName) {
        this.checkOrgName = checkOrgName;
    }
    public String getJoinPersonFk() {
        return joinPersonFk;
    }

    public void setJoinPersonFk(String joinPersonFk) {
        this.joinPersonFk = joinPersonFk;
    }
    public String getJoinPersonName() {
        return joinPersonName;
    }

    public void setJoinPersonName(String joinPersonName) {
        this.joinPersonName = joinPersonName;
    }
    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public List<CheckComprehensiveHolidayList> getCheckComprehensiveHolidayList() {
        return checkComprehensiveHolidayList;
    }

    public void setCheckComprehensiveHolidayList(List<CheckComprehensiveHolidayList> checkComprehensiveHolidayList) {
        this.checkComprehensiveHolidayList = checkComprehensiveHolidayList;
    }

    @Override
    public String toString() {
        return "CheckComprehensiveHoliday{" +
        "id=" + id +
        ", personChargeFk=" + personChargeFk +
        ", personChargeName=" + personChargeName +
        ", checkTime=" + checkTime +
        ", checkPersonFk=" + checkPersonFk +
        ", checkPersonName=" + checkPersonName +
        ", checkOrgFk=" + checkOrgFk +
        ", checkOrgName=" + checkOrgName +
        ", joinPersonFk=" + joinPersonFk +
        ", joinPersonName=" + joinPersonName +
        ", orgFk=" + orgFk +
        ", orgName=" + orgName +
        ", createPersonFk=" + createPersonFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
