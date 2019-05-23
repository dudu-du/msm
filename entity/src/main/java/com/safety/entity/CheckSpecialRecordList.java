package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 周排查记录填写列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckSpecialRecordList extends Model<CheckSpecialRecordList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 专项排查记录填写ID
     */
    private String checkSpecialRecordId;

    /**
     * 专项排查记录列表ID
     */
    private String checkSpecialListId;

    /**
     * 填写结果
     */
    private String result;

    /**
     * 填写人ID
     */
    private String createPersonFk;

    /**
     * 所属机构ID
     */
    private String orgFk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCheckSpecialRecordId() {
        return checkSpecialRecordId;
    }

    public void setCheckSpecialRecordId(String checkSpecialRecordId) {
        this.checkSpecialRecordId = checkSpecialRecordId;
    }
    public String getCheckSpecialListId() {
        return checkSpecialListId;
    }

    public void setCheckSpecialListId(String checkSpecialListId) {
        this.checkSpecialListId = checkSpecialListId;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    @Override
    public String toString() {
        return "CheckSpecialRecordList{" +
        "id=" + id +
        ", checkSpecialRecordId=" + checkSpecialRecordId +
        ", checkSpecialListId=" + checkSpecialListId +
        ", result=" + result +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
