<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>基础平台-双重预防机制运行信息管理平台</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_912670_72s4xsa6she.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_1205992_fiuaabso1ki.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="icon" type="image/x-icon" href="/Public/image/bitbug_favicon.ico" sizes="48x48">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="lay_page_main">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="/Public/image/login/logo.jpg" height="40px" width="40px">
            <span>
            <#if org_name=="">
            双重预防机制
            <#else >
                ${org_name}
            </#if>
            </span>
        </div>
        <ul class="layui-nav layui-layout-left" lay-filter="topleft">
            <li class="layui-nav-item layadmin-flexible" lay-unselect="">
                <a href="javascript:;" lay-event="flexible" title="侧边伸缩">
                    <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                </a>
            </li>
            <li class="layui-nav-item" lay-unselect="">
                <a href="javascript:;" lay-event="refresh" title="刷新">
                    <i class="layui-icon layui-icon-refresh-3"></i>
                </a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="topnav">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="" class="layui-nav-img">
                    <#if Session["MEMBER_USER_PERSON"]?exists> ${Session.MEMBER_USER_PERSON.realname}</#if>
                    <input type="hidden" name="orgId" value=${Session.MEMBER_USER_PERSON.orgId}>
                    <input type="hidden" name="userPic" value=${Session.MEMBER_USER_PERSON.pic}>
                    <input type="hidden" name="loginId" value=${Session.MEMBER_USER_PERSON.loginId}>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/View/info" target="_Blank">基本资料</a></dd>
                    <#--<dd><a href="${springMacroRequestContext.contextPath}/logout">退出</a></dd>-->
                    <dd><a href="javascript:logOut();">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black layui-side-menu">
        <div class="layui-side-scroll"></div>
    </div>
    <#--<div class="page-map">
        <span class="layui-breadcrumb">
            <a href="#">首页</a>
        </span>
    </div>-->
    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layadmin-tabsbody-item layui-show">
            <iframe id="lay-iframe" src="" frameborder="0" class="lay-iframe"></iframe>
        </div>
    </div>
    <#--<div class="layui-footer">
        <!-- 底部固定区域 &ndash;&gt;
        版本:${version} © layui.com - 底部固定区域
    </div>-->
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/index.js"></script>
</body>
</html>