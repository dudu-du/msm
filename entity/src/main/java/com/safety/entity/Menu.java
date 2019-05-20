package com.safety.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author safety
 * @since 2018-11-07
 */
public class Menu extends Model<Menu> {

    private int id;

    private String menuCode;

    private int parentId;

    private String title;

    private int sysState;

    private LocalDateTime createDatetime;

    private int sort;

    private int isLeaf;

    private String url;

    private int isManage;

    private String icon;

    private String isThirdWeb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSysState() {
        return sysState;
    }

    public void setSysState(int sysState) {
        this.sysState = sysState;
    }

    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsManage() {
        return isManage;
    }

    public void setIsManage(int isManage) {
        this.isManage = isManage;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIsThirdWeb() {
        return isThirdWeb;
    }

    public void setIsThirdWeb(String isThirdWeb) {
        this.isThirdWeb = isThirdWeb;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "id=" + id +
        ", menuCode=" + menuCode +
        ", parentId=" + parentId +
        ", title=" + title +
        ", sysState=" + sysState +
        ", createDatetime=" + createDatetime +
        ", sort=" + sort +
        ", isLeaf=" + isLeaf +
        ", url=" + url +
        ", isManage=" + isManage +
        ", icon=" + icon +
        ", isThirdWeb=" + isThirdWeb +
        "}";
    }
}
