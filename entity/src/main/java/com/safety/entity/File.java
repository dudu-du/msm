package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 信息收集表
 * </p>
 *
 * @author safety
 * @since 2019-05-25
 */
public class File extends Model<File> {

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
     * 备注
     */
    private String remark;

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
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "File{" +
        "id=" + id +
        ", name=" + name +
        ", path=" + path +
        ", type=" + type +
        ", remark=" + remark +
        "}";
    }
}
