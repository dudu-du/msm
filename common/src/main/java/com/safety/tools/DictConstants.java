package com.safety.tools;

/**
 * @author lxy
 * @descript:字典表基本常量
 * @date:2018年11月19日
 */
public class DictConstants {
    //学期
    public static final String XUEQI = "WE_XUEQI";
    //学校类型
    public static final String SCHOOLTYPE = "WE_SCHOOLTYPE";
    //学段
    public static final String XUEDUAN = "WE_XUEDUAN";
    //角色
    public static final String ROLE = "WE_ROLE";
    //权限
    public static final String PERMISSION = "WE_PERMISSION";
    //学级
    public static final String XUEJI = "WE_XUEJI";
    //机构——学校
    public static final String ORGTYPE_SCHOOL = "OT_SCHOOL";
    //机构——局单位
    public static final String ORGTYPE_BUREAU = "OT_BUREAU";
    //机构——部门
    public static final String ORGTYPE_DEPARTMENT = "OT_DEPARTMENT";
    //学段-幼儿园
    public static final String XUEDUAN_YOUERYUAN = "XD_YOUERYUAN";
    //学段-小学
    public static final String XUEDUAN_XIAOXUE = "XD_XIAOXUE";
    //学段-初中
    public static final String XUEDUAN_CHUZHONG = "XD_CHUZHONG";
    //学段-高中
    public static final String XUEDUAN_GAOZHONG = "XD_GAOZHONG";
    //学期-第一学期
    public static final String XUEQI_DYXQ = "XQ_DYXQ";
    //学期-第二学期
    public static final String XUEQI_DEXQ = "XQ_DEXQ";
    //学级-一年级
    public static final String XUEJI_YINIANJI = "XJ_YINIANJI";
    //学级-二年级
    public static final String XUEJI_ERNIANJI = "XJ_ERNIANJI";
    //学级-三年级
    public static final String XUEJI_SANNIANJI = "XJ_SANNIANJI";
    //学级-四年级
    public static final String XUEJI_SINIANJI = "XJ_SINIANJI";
    //学级-五年级
    public static final String XUEJI_WUNIANJI = "XJ_WUNIANJI";
    //学级-六年级
    public static final String XUEJI_LIUNIANJI = "XJ_LIUNIANJI";


    //人员类型-教师
    public static final String PERSON_TEACHER = "PT_TEACHER";
    //人员类型-家长
    public static final String PERSON_PATRIARCH = "PT_PATRIARCH";
    //人员类型-学生
    public static final String PERSON_STUDENT = "PT_STUDENT";
    //人员类型-局管理员
    public static final String PERSON_OFFICEADMIN = "PT_OFFICEADMIN";

    //session-人员
    public static final String MEMBER_USER_PERSON = "MEMBER_USER_PERSON";

    //登录人的login_id
    public static final String MEMBER_LOGIN_KEY = "MEMBER_LOGIN_KEY";

    //校级管理员
    public static final String ROLE_SHCOOLADMIN = "ROLE_SHCOOLADMIN";
    //超级管理员
    public static final String ROLE_SUPERADMIN = "ROLE_SUPERADMIN";
    //机构管理员
    public static final String ROLE_ORGADMIN = "ROLE_ORGADMIN";

    //系统token标识
    public static final String TOKEN_SYSTEM = "1";
    //个人token标识
    public static final String TOKEN_PERSONAL = "0";
    //所有类型token
    public static final String TOKEN_ALL = "-1";

    //对外接口机构范围标识(全部)
    public static final String FLAG_ORG = "all";

    //家长关系父节点
    public static final String PATRIARCH_RELATION ="WE_RELATION";

    //假期
    public static final boolean CALENDAR_TYPE_HOLIDAY = false;
    //工作日
    public static final boolean CALENDAR_TYPE_WORKDAY = true;

    //检查表类型
    public static final String CHECK_TYPE_DAY = "日治理记录";
    public static final String CHECK_TYPE_WEEK = "周排查记录";
    public static final String CHECK_TYPE_MONTH = "月排查记录";
    public static final String CHECK_TYPE_SPECIAL = "专项检查";
    public static final String CHECK_TYPE_HOLIDAY = "综合检查(节假日、复产前)";
    public static final String CHECK_TYPE_SEASON = "综合检查(季节性)";
}
