package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 信息收集表
 * </p>
 *
 * @author safety
 * @since 2019-05-26
 */
public class FileMessage extends Model<FileMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 信息文件id，唯一标识UUID
     */
    private String id;

    /**
     * 信息文件名称
     */
    private String name;

    /**
     * 信息文件保存地址
     */
    private String path;

    /**
     * 信息文件类型 1-内部信息 2-外部信息
     */
    private Integer type;

    /**
     * 机构id
     */
    private String orgFk;

    /**
     * 创建人id
     */
    private String createPersonFk;

    /**
     * 备注
     */
    private String remark;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getOrgFk() {
        return orgFk;
    }

    public void setOrgFk(String orgFk) {
        this.orgFk = orgFk;
    }
    public String getCreatePersonFk() {
        return createPersonFk;
    }

    public void setCreatePersonFk(String createPersonFk) {
        this.createPersonFk = createPersonFk;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "FileMessage{" +
        "id=" + id +
        ", name=" + name +
        ", path=" + path +
        ", type=" + type +
        ", orgFk=" + orgFk +
        ", createPersonFk=" + createPersonFk +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
