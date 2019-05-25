<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>基础资料-双重预防机制运行信息管理平台</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_912670_72s4xsa6she.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="icon" type="image/x-icon" href="/Public/image/bitbug_favicon.ico" sizes="48x48">
</head>
<body>
<div class="layui-layout layui-layout-admin" id="lay_page_main">
    <div class="layui-header">
        <div class="layui-logo"><span>
            <#--<#if org_name=="">-->
                safety为石
            <#--<#else >
                ${org_name}
            </#if>-->
        </span></div>
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
                    <dd><a href="${springMacroRequestContext.contextPath}/logout">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black layui-side-menu">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="leftnav">
                <li class="layui-nav-item">
                    <a href="javascript:;" id="" data-type="0" data-parent="0" lay-tips="我的资料" lay-href="/View/user">
                        <i class="iconfont icon-jigouguanli"></i>
                        <cite>我的资料</cite>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" id="" data-type="0" data-parent="0" lay-tips="修改密码" lay-href="/View/changepassword">
                        <i class="iconfont icon-rizhiguanli"></i>
                        <cite>修改密码</cite>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 内容主体区域 -->
    <div class="layui-body">
        <div class="layadmin-tabsbody-item layui-show">
            <iframe id="lay-iframe" src="" frameborder="0" class="lay-iframe"></iframe>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/user/info.js"></script>
</body>
</html>