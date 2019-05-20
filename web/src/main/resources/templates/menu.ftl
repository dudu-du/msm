<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <@shiro.hasRole name="ROLE_SUPERADMIN">
            <li class="layui-nav-item">
                <a href="javascript:;">教学管理</a>
                <dl class="layui-nav-child">
                    <dd class=""><a href="${springMacroRequestContext.contextPath}/View/listSemester">学期管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listSubjectByOrgId">学科管理</a></dd>
                </dl>
            </li>
        </@shiro.hasRole>
        <@shiro.hasRole name="ROLE_SHCOOLADMIN">
            <li class="layui-nav-item">
                <a href="javascript:;">教务设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">校历管理</a></dd>
                    <dd><a href="javascript:;">课时管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listClass">班级管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listMaster">班主任管理</a></dd>
                    <dd><a href="javascript:;">任课管理</a></dd>
                    <dd><a href="javascript:;">课表管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listSubjectByOrgId">学科管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">设备管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">设备管理</a></dd>
                    <dd><a href="javascript:;">设备控制</a></dd>
                    <dd><a href="javascript:;">设备分配</a></dd>
                </dl>
            </li>
        </@shiro.hasRole>
        <@shiro.hasRole name="ROLE_SUPERADMIN">
            <li class="layui-nav-item">
                <a class="" href="${springMacroRequestContext.contextPath}/View/orgList">
                    <#--<i class="layui-icon layui-icon-template"></i>-->
                    <cite>机构管理</cite>
                </a>
            </li>
        </@shiro.hasRole>
            <li class="layui-nav-item">
                <a class="" href="javascript:;">
                <#--<i class="layui-icon layui-icon-template"></i>-->
                    <cite>人员管理</cite>
                </a>
                <dl class="layui-nav-child">
                <@shiro.hasRole name="ROLE_SUPERADMIN">
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listOfficeAdmin">局管理员</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listSchoolAdmin">校管理员</a></dd>
                </@shiro.hasRole>
                <@shiro.hasAnyRoles name="ROLE_SHCOOLADMIN,ROLE_SUPERADMIN">
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listTeacher">教师管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listStudent">学生管理</a></dd>
                    <dd><a href="javascript:;">家长管理</a></dd>
                </@shiro.hasAnyRoles>
                </dl>
            </li>
        <@shiro.hasRole name="ROLE_SUPERADMIN">
            <li class="layui-nav-item">
                <a class="" href="javascript:;">
                <#--<i class="layui-icon layui-icon-template"></i>-->
                    <cite>角色权限</cite>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listRole">角色管理</a></dd>
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listPermission"">权限管理</a></dd>
                </dl>
            </li>
            <#--<li class="layui-nav-item">-->
                <#--<a href="javascript:;">系统设置</a>-->
                <#--<dl class="layui-nav-child">-->
                    <#--<dd><a href="javascript:;">系统字典</a></dd>-->
                <#--</dl>-->
            <#--</li>-->
        </@shiro.hasRole>
        <@shiro.hasRole name="ROLE_SHCOOLADMIN">
            <li class="layui-nav-item">
                <a href="javascript:;">平台设置</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">学校信息</a></dd>
                </dl>
            </li>
        </@shiro.hasRole>
        <@shiro.hasRole name="ROLE_SUPERADMIN">
            <li class="layui-nav-item">
                <a class="" href="javascript:;">
                <#--<i class="layui-icon layui-icon-template"></i>-->
                    <cite>授权管理</cite>
                </a>
                <dl class="layui-nav-child">
                    <#--<dd><a href="javascript:;">校授权</a></dd>-->
                    <#--<dd><a href="javascript:;">局授权</a></dd>-->
                    <dd><a href="${springMacroRequestContext.contextPath}/View/listClient">第三方应用授权</a></dd>
                </dl>
            </li>
            <#--<li class="layui-nav-item">-->
                <#--<a class="" href="javascript:;">-->
                <#--&lt;#&ndash;<i class="layui-icon layui-icon-template"></i>&ndash;&gt;-->
                    <#--<cite>开发者中心</cite>-->
                <#--</a>-->
                <#--<dl class="layui-nav-child">-->
                    <#--<dd><a href="javascript:;">应用管理</a></dd>-->
                <#--</dl>-->
            <#--</li>-->
            <#--<li class="layui-nav-item">-->
                <#--<a href="javascript:;">日志管理</a>-->
            <#--</li>-->
        </@shiro.hasRole>
        </ul>
    </div>
</div>
<script>
    var pathname = window.location.pathname;
    window.onload=function(){
        $('a[href="'+pathname+'"]').parent('dd').addClass('layui-this');
        $('a[href="'+pathname+'"]').parents('li').addClass('layui-nav-itemed');
    }
</script>