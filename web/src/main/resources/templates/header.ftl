<div class="layui-header">
    <div class="layui-logo">
        <img src="/Public/image/login/logo.jpg" style="width:30px;height:30px;position:relative;top:-3px;">
        <span>双重预防机制</span>
    </div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                <#if Session["MEMBER_USER_PERSON"]?exists> ${Session.MEMBER_USER_PERSON.realname}</#if>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="">基本资料</a></dd>
                <dd><a href="">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href=${springMacroRequestContext.contextPath}/logout>退了</a></li>
    </ul>
</div>