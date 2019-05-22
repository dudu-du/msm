package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class CheckList extends Model<CheckList> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 0 否 1 是
     */
    private Integer isCheck;

    private String checkFk;

    private String personFk;

    /**
     * 0 未处理 1 已处理
     */
    private Integer handled;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
    public String getCheckFk() {
        return checkFk;
    }

    public void setCheckFk(String checkFk) {
        this.checkFk = checkFk;
    }
    public String getPersonFk() {
        return personFk;
    }

    public void setPersonFk(String personFk) {
        this.personFk = personFk;
    }
    public Integer getHandled() {
        return handled;
    }

    public void setHandled(Integer handled) {
        this.handled = handled;
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
        return "CheckList{" +
        "id=" + id +
        ", isCheck=" + isCheck +
        ", checkFk=" + checkFk +
        ", personFk=" + personFk +
        ", handled=" + handled +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
