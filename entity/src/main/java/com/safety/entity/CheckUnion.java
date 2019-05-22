package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 检查合并项
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class CheckUnion extends Model<CheckUnion> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String identificationPk;

    /**
     * 类型 0 专项检查 2 周检查 3 月检查
     */
    private Integer type;

    private LocalDateTime createTime;

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
    public String getIdentificationPk() {
        return identificationPk;
    }

    public void setIdentificationPk(String identificationPk) {
        this.identificationPk = identificationPk;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "CheckUnion{" +
        "id=" + id +
        ", name=" + name +
        ", identificationPk=" + identificationPk +
        ", type=" + type +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
