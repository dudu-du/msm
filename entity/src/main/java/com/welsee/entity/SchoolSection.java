package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public class SchoolSection extends Model<SchoolSection> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 学校类型字典
     */
    private String schoolDict;

    /**
     * 学段字典
     */
    private String sectionDict;

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getSchoolDict() {
        return schoolDict;
    }

    public void setSchoolDict(String schoolDict) {
        this.schoolDict = schoolDict;
    }
    public String getSectionDict() {
        return sectionDict;
    }

    public void setSectionDict(String sectionDict) {
        this.sectionDict = sectionDict;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SchoolSection{" +
        "id=" + id +
        ", schoolDict=" + schoolDict +
        ", sectionDict=" + sectionDict +
        ", sort=" + sort +
        "}";
    }
}
