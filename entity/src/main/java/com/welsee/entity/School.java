package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 学校信息
 * </p>
 *
 * @author welsee
 * @since 2019-01-11
 */
public class School extends Model<School> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 学校主图片
     */
    private String schoolImg;

    /**
     * 校徽
     */
    private String schoolIcon;

    /**
     * 校名的拼音全称
     */
    @TableField("school_CN")
    private String schoolCn;

    /**
     * 校训
     */
    @TableField("school_Motto")
    private String schoolMotto;

    /**
     * 字体颜色
     */
    private String fontColour;

    /**
     * 学校标题字体
     */
    @TableField("school_Font")
    private String schoolFont;

    /**
     * 学校标题字体
     */
    @TableField("place_code")
    private String placeCode;

    /**
     * 学校简介
     */
    @TableField("school_Profile")
    private String schoolProfile;

    /**
     * 是否删除,  0未删除/1已删除
     */
    private Integer del;

    /**
     * 创建时间
     */
    private LocalDateTime createdatetime;

    /**
     * 更新时间
     */
    private LocalDateTime modifydatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getSchoolImg() {
        return schoolImg;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg;
    }
    public String getSchoolIcon() {
        return schoolIcon;
    }

    public void setSchoolIcon(String schoolIcon) {
        this.schoolIcon = schoolIcon;
    }
    public String getSchoolCn() {
        return schoolCn;
    }

    public void setSchoolCn(String schoolCn) {
        this.schoolCn = schoolCn;
    }
    public String getSchoolMotto() {
        return schoolMotto;
    }

    public void setSchoolMotto(String schoolMotto) {
        this.schoolMotto = schoolMotto;
    }
    public String getFontColour() {
        return fontColour;
    }

    public void setFontColour(String fontColour) {
        this.fontColour = fontColour;
    }
    public String getSchoolFont() {
        return schoolFont;
    }

    public void setSchoolFont(String schoolFont) {
        this.schoolFont = schoolFont;
    }
    public String getSchoolProfile() {
        return schoolProfile;
    }

    public void setSchoolProfile(String schoolProfile) {
        this.schoolProfile = schoolProfile;
    }
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }
    public LocalDateTime getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(LocalDateTime modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "School{" +
        "id=" + id +
        ", orgId=" + orgId +
        ", schoolImg=" + schoolImg +
        ", schoolIcon=" + schoolIcon +
        ", schoolCn=" + schoolCn +
        ", schoolMotto=" + schoolMotto +
        ", fontColour=" + fontColour +
        ", schoolFont=" + schoolFont +
        ", schoolProfile=" + schoolProfile +
        ", del=" + del +
        ", createdatetime=" + createdatetime +
        ", modifydatetime=" + modifydatetime +
        "}";
    }
}
