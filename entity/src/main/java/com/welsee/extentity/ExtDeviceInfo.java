package com.welsee.extentity;

import com.welsee.entity.DeviceInfo;

public class ExtDeviceInfo extends DeviceInfo {

    /**
     * 是否分配班级
     */
    private String isDistribute;

    public String getIsDistribute() {
        return isDistribute;
    }

    public void setIsDistribute(String isDistribute) {
        this.isDistribute = isDistribute;
    }
}
