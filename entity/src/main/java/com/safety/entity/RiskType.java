package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 风险类型
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class RiskType extends Model<RiskType> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer level;

    /**
     * 父id
     */
    private Long fid;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
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
        return "RiskType{" +
        "id=" + id +
        ", name=" + name +
        ", level=" + level +
        ", fid=" + fid +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
