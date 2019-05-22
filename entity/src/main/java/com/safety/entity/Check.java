package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 安全检查
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class Check extends Model<Check> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String item;

    private String info;

    private String identificationFk;

    /**
     * 类型 0 专项检查 1 日检查 2 周检查 3 月检查
     */
    private Integer type;

    private String unionFk;

    private String departmentFk;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getIdentificationFk() {
        return identificationFk;
    }

    public void setIdentificationFk(String identificationFk) {
        this.identificationFk = identificationFk;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getUnionFk() {
        return unionFk;
    }

    public void setUnionFk(String unionFk) {
        this.unionFk = unionFk;
    }
    public String getDepartmentFk() {
        return departmentFk;
    }

    public void setDepartmentFk(String departmentFk) {
        this.departmentFk = departmentFk;
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
        return "Check{" +
        "id=" + id +
        ", item=" + item +
        ", info=" + info +
        ", identificationFk=" + identificationFk +
        ", type=" + type +
        ", unionFk=" + unionFk +
        ", departmentFk=" + departmentFk +
        ", createTime=" + createTime +
        ", modifyTime=" + modifyTime +
        "}";
    }
}
