package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author welsee
 * @since 2019-04-17
 */
@Data
public class Calendar extends Model<Calendar> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 事件简称
     */
    private String abbreviation;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private String endTime;

    /**
     * 学期名称
     */
    private String semesterName;

    /**
     * 是否假期0不是/1是
     */
    private Integer isVacation;

    private String orgId;

    private String remark;

    private Integer sort;

    private Integer del;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createdatetime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifydatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
