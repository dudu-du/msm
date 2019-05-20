package com.welsee.extentity;

import com.welsee.entity.WxInfo;
import lombok.Data;

@Data
public class ExtWxInfo extends WxInfo {
    private String orgName;//机构名称
    private String schoolImg;//校徽
}
