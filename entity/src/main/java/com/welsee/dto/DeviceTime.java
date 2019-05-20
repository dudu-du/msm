package com.welsee.dto;

import lombok.Data;

import java.time.LocalTime;

/**
 * Created by 李新宇
 * 2019-05-09 14:06
 */
@Data
public class DeviceTime {

    private LocalTime timeOn;

    private LocalTime timeOff;
}
