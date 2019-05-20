package com.welsee.extentity;

public class ExtDeviceClass extends ExtClass {

    /**
     * 分配班牌id
     */
    private String deviceClassId;


    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备id
     */
    private String deviceId;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceClassId() {
        return deviceClassId;
    }

    public void setDeviceClassId(String deviceClassId) {
        this.deviceClassId = deviceClassId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
