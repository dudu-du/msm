package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 专项检查
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckSpecial extends Model<CheckSpecial> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 检查时间
     */
    private LocalDateTime checkTime;

    /**
     * 检查范围
     */
    private String checkScope;

    /**
     * 检查人ID
     */
    private String checkPersonFk;

    /**
     * 检查人姓名
     */
    private String checkPersonName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 所属机构ID
     */
    private String orgFk;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckScope() {
        return checkScope;
    }

    public void setCheckScope(String checkScope) {
        this.checkScope = checkScope;
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
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }
    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CheckSpecial{" +
        "id=" + id +
        ", checkTime=" + checkTime +
        ", checkScope=" + checkScope +
        ", checkPersonFk=" + checkPersonFk +
        ", checkPersonName=" + checkPersonName +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        "}";
    }
}
