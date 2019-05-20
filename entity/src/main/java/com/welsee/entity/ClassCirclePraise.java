package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author welsee
 * @since 2019-02-25
 */
public class ClassCirclePraise extends Model<ClassCirclePraise> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 点赞的班级圈id
     */
    private String classCircleId;

    /**
     * 点赞人的所有id
     */
    private String userIds;

    /**
     * 多少人点赞
     */
    private Integer counts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassCircleId() {
        return classCircleId;
    }

    public void setClassCircleId(String classCircleId) {
        this.classCircleId = classCircleId;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ClassCirclePraise{" +
                "id='" + id + '\'' +
                ", classCircleId='" + classCircleId + '\'' +
                ", userIds='" + userIds + '\'' +
                ", counts=" + counts +
                '}';
    }
}
