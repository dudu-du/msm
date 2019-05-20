package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author welsee
 * @since 2019-05-13
 */
@Data
public class CalendarEvent extends Model<CalendarEvent> {

    private static final long serialVersionUID = 1L;

    private String id;

    private LocalDate time;

    private String orgId;

    /**
     * 假期类型
     */
    private Integer holidayType;

    /**
     * 区分假期或者工作日  0-假期  1-工作日
     */
    private Integer type;

    private Integer sort;

    private Integer del;

    private LocalDateTime createdatetime;

    private LocalDateTime modifydatetime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
