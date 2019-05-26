package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 月排查记录填写
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public class CheckMonthRecord extends Model<CheckMonthRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 月排查记录ID
     */
    private String checkMonthId;

    /**
     * 检查内容
     */
    private String checkContent;

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

    /**
     * 条目集合
     */
    private List<CheckMonthList> checkMonthList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getCheckMonthId() {
        return checkMonthId;
    }

    public void setCheckMonthId(String checkMonthId) {
        this.checkMonthId = checkMonthId;
    }
    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
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

    public List<CheckMonthList> getCheckMonthList() {
        return checkMonthList;
    }

    public void setCheckMonthList(List<CheckMonthList> checkMonthList) {
        this.checkMonthList = checkMonthList;
    }

    @Override
    public String toString() {
        return "CheckMonthRecord{" +
        "id=" + id +
        ", checkMonthId=" + checkMonthId +
        ", checkContent=" + checkContent +
        ", createPersonFk=" + createPersonFk +
        ", orgFk=" + orgFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
