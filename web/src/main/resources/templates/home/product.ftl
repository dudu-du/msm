<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>首页-智慧教育服务平台</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/layui-app-page.css">
    <link rel="icon" type="image/x-icon" href="/Public/image/bitbug_favicon.ico" sizes="48x48">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">safety为石</div>
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
                    <dd><a href="${springMacroRequestContext.contextPath}/logout">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
    <div class="layui-app-body">
        <!-- 内容主体区域 -->
        <div class="layui-app-main">
            <h3 class="userInfoHeader">
                <span class="time">你好，</span>
                <span class="username"><#if Session["MEMBER_USER_PERSON"]?exists> ${Session.MEMBER_USER_PERSON.realname}</#if></span>
            </h3>
            <div class="layui-app-preview">
                <div class="layui-row layui-col-space15"></div>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/product.js"></script>
</body>
</html>