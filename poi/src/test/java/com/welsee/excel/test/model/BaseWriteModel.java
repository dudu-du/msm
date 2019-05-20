package com.welsee.excel.test.model;

import com.welsee.excel.annotation.ExcelProperty;
import com.welsee.excel.metadata.BaseRowModel;

public class BaseWriteModel extends BaseRowModel {
    @ExcelProperty(value = {"姓名*"},index = 0)
    protected String realName;

    @ExcelProperty(value = {"用户名"},index = 1)
    protected String loginName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
