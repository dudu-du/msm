package com.welsee.extentity;

import com.welsee.entity.Org;

import java.util.List;

public class ExtOrg extends Org {
    /**
     * 下属机构
     */
    private List children;

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
