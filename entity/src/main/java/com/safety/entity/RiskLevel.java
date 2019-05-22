package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 风险等级
 * </p>
 *
 * @author safety
 * @since 2019-05-22
 */
public class RiskLevel extends Model<RiskLevel> {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 等级名称
     */
    private String name;

    /**
     * 描述
     */
    private String detail;

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
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RiskLevel{" +
        "id=" + id +
        ", name=" + name +
        ", detail=" + detail +
        "}";
    }
}
