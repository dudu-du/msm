package com.welsee.extentity;

import com.welsee.dto.DeviceTime;
import lombok.Data;

/**
 * Created by 李新宇
 * 2019-05-10 15:23
 */
@Data
public class ExtDeviceTime extends DeviceTime {

    /**
     * 开关机类型
     *
     * @return
     */
    private String timeRules;
}
