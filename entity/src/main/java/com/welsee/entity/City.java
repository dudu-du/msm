package com.welsee.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author welsee
 * @since 2019-01-25
 */
public class City extends Model<City> {

    private static final long serialVersionUID = 1L;

    private String cityId;

    private String cityEn;

    private String cityCn;

    private String countryCode;

    private String countryEn;

    private String countryCn;

    private String provinceEn;

    private String provinceCn;

    private String adminDistrictEn;

    private String adminDistrictCn;

    private String latitude;

    private String lngitude;

    private String adCode;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }
    public String getCityCn() {
        return cityCn;
    }

    public void setCityCn(String cityCn) {
        this.cityCn = cityCn;
    }
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }
    public String getCountryCn() {
        return countryCn;
    }

    public void setCountryCn(String countryCn) {
        this.countryCn = countryCn;
    }
    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }
    public String getProvinceCn() {
        return provinceCn;
    }

    public void setProvinceCn(String provinceCn) {
        this.provinceCn = provinceCn;
    }
    public String getAdminDistrictEn() {
        return adminDistrictEn;
    }

    public void setAdminDistrictEn(String adminDistrictEn) {
        this.adminDistrictEn = adminDistrictEn;
    }
    public String getAdminDistrictCn() {
        return adminDistrictCn;
    }

    public void setAdminDistrictCn(String adminDistrictCn) {
        this.adminDistrictCn = adminDistrictCn;
    }
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLngitude() {
        return lngitude;
    }

    public void setLngitude(String lngitude) {
        this.lngitude = lngitude;
    }
    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.cityId;
    }

    @Override
    public String toString() {
        return "Citylist{" +
        "cityId=" + cityId +
        ", cityEn=" + cityEn +
        ", cityCn=" + cityCn +
        ", countryCode=" + countryCode +
        ", countryEn=" + countryEn +
        ", countryCn=" + countryCn +
        ", provinceEn=" + provinceEn +
        ", provinceCn=" + provinceCn +
        ", admin districtEn=" + adminDistrictEn +
        ", admin districtCn=" + adminDistrictCn +
        ", latitude=" + latitude +
        ", lngitude=" + lngitude +
        ", adCode=" + adCode +
        "}";
    }
}
