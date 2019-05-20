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
public class SectionGrade extends Model<SectionGrade> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 学段字典
     */
    private String sectionDict;

    /**
     * 年级字典
     */
    private String gradeDict;

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getSectionDict() {
        return sectionDict;
    }

    public void setSectionDict(String sectionDict) {
        this.sectionDict = sectionDict;
    }
    public String getGradeDict() {
        return gradeDict;
    }

    public void setGradeDict(String gradeDict) {
        this.gradeDict = gradeDict;
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
        return "SectionGrade{" +
        "id=" + id +
        ", sectionDict=" + sectionDict +
        ", gradeDict=" + gradeDict +
        ", sort=" + sort +
        "}";
    }
}
