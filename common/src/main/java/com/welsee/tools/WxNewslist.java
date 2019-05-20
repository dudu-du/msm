package com.welsee.tools;

import lombok.Data;

import java.util.List;

@Data
public class WxNewslist {
    private String code;
    private String msg;
    private List newslist;
}
