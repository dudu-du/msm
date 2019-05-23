package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 综合季度排查记录填写列表
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckSeasonRecordList extends Model<CheckSeasonRecordList> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 综合季度排查记录填写ID
     */
    private String checkSeasonRecordId;

    /**
     * 综合季度排查记录列表ID
     */
    private String checkSeasonListId;

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
    public String getCheckSeasonRecordId() {
        return checkSeasonRecordId;
    }

    public void setCheckSeasonRecordId(String checkSeasonRecordId) {
        this.checkSeasonRecordId = checkSeasonRecordId;
    }
    public String getCheckSeasonListId() {
        return checkSeasonListId;
    }

    public void setCheckSeasonListId(String checkSeasonListId) {
        this.checkSeasonListId = checkSeasonListId;
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
        return "CheckSeasonRecordList{" +
        "id=" + id +
        ", checkSeasonRecordId=" + checkSeasonRecordId +
        ", checkSeasonListId=" + checkSeasonListId +
        ", result=" + result +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
