package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author welsee
 * @since 2019-04-18
 */
public class WxInfo extends Model<WxInfo> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String orgId;

    private String remarks;

    /**
     * 公众号的中文名，例如：河北省保定市沈庄小学
     */
    private String word;

    /**
     * 公众号唯一标识，例如：MzI0ODA4OTc4OA==
     */
    private String biz;

    private String newslist;

    private Integer del;

    private LocalDateTime createdatetime;

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
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz;
    }
    public String getNewslist() {
        return newslist;
    }

    public void setNewslist(String newslist) {
        this.newslist = newslist;
    }
    public Integer getDel() {
        return del;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        return "WxInfo{" +
        "id=" + id +
        ", orgId=" + orgId +
        ", word=" + word +
        ", biz=" + biz +
        ", remarks=" + remarks +
        ", newslist=" + newslist +
        ", del=" + del +
        ", createdatetime=" + createdatetime +
        ", modifydatetime=" + modifydatetime +
        "}";
    }
}
