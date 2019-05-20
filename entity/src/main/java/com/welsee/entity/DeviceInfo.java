package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author welsee
 * @since 2019-01-09
 */
public class DeviceInfo extends Model<DeviceInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备的id
     */
    private String deviceId;

    /**
     * 设备的mac地址
     */
    private String deviceMac;

    /**
     * 设备的名称
     */
    private String deviceName;

    /**
     * 设备的在线状态0默认不在线,1为在线
     */
    private Integer signFlag;

    /**
     * 设备的类型0默认为校牌,1是班牌,2是LED,3是其他
     */
    private Integer deviceType;

    /**
     * 设备的内网ip
     */
    private String deviceIntranetIp;

    /**
     * 设备的外网ip
     */
    private String deviceExtranetIp;

    /**
     * 设备所属机构Id
     */
    @TableField("device_orgId")
    private String deviceOrgid;

    /**
     * 设备的启用状态
     */
    private Integer deviceEnabledState;

    /**
     * 设备屏幕截图的地址
     */
    private String deviceScreenShot;

    /**
     * 设备的屏幕状态,0默认是横屏
     */
    private Integer deviceScreenType;

    /**
     * 设备的音量
     */
    private String deviceVolume;

    /**
     * 用户的发送消息时间
     */
    private String createTime;

    /**
     * 0代表设备未删除,1代表设备删除
     */
    private Integer delState;

    /**
     * 用户的发送消息时间
     */
    private String updateTime;

    private Integer touchState;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public Integer getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(Integer signFlag) {
        this.signFlag = signFlag;
    }
    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
    public String getDeviceIntranetIp() {
        return deviceIntranetIp;
    }

    public void setDeviceIntranetIp(String deviceIntranetIp) {
        this.deviceIntranetIp = deviceIntranetIp;
    }
    public String getDeviceExtranetIp() {
        return deviceExtranetIp;
    }

    public void setDeviceExtranetIp(String deviceExtranetIp) {
        this.deviceExtranetIp = deviceExtranetIp;
    }
    public String getDeviceOrgid() {
        return deviceOrgid;
    }

    public void setDeviceOrgid(String deviceOrgid) {
        this.deviceOrgid = deviceOrgid;
    }
    public Integer getDeviceEnabledState() {
        return deviceEnabledState;
    }

    public void setDeviceEnabledState(Integer deviceEnabledState) {
        this.deviceEnabledState = deviceEnabledState;
    }
    public String getDeviceScreenShot() {
        return deviceScreenShot;
    }

    public void setDeviceScreenShot(String deviceScreenShot) {
        this.deviceScreenShot = deviceScreenShot;
    }
    public Integer getDeviceScreenType() {
        return deviceScreenType;
    }

    public void setDeviceScreenType(Integer deviceScreenType) {
        this.deviceScreenType = deviceScreenType;
    }
    public String getDeviceVolume() {
        return deviceVolume;
    }

    public void setDeviceVolume(String deviceVolume) {
        this.deviceVolume = deviceVolume;
    }
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public Integer getTouchState() {
        return touchState;
    }

    public void setTouchState(Integer touchState) {
        this.touchState = touchState;
    }

    @Override
    protected Serializable pkVal() {
        return this.deviceId;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
        "deviceId=" + deviceId +
        ", deviceMac=" + deviceMac +
        ", deviceName=" + deviceName +
        ", signFlag=" + signFlag +
        ", deviceType=" + deviceType +
        ", deviceIntranetIp=" + deviceIntranetIp +
        ", deviceExtranetIp=" + deviceExtranetIp +
        ", deviceOrgid=" + deviceOrgid +
        ", deviceEnabledState=" + deviceEnabledState +
        ", deviceScreenShot=" + deviceScreenShot +
        ", deviceScreenType=" + deviceScreenType +
        ", deviceVolume=" + deviceVolume +
        ", createTime=" + createTime +
        ", delState=" + delState +
        ", updateTime=" + updateTime +
        ", touchState=" + touchState +
        "}";
    }
}
