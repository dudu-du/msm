<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>双重预防机制运行信息管理平台</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
	<link rel="stylesheet" href="/Public/css/public-yun.css">
	<link rel="stylesheet" href="/Public/css/login/login.css">
	<link rel="icon" type="image/x-icon" href="/Public/image/bitbug_favicon.ico" sizes="48x48">
    <script language="JavaScript">
        //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
        //这样就可以让登陆窗口显示在整个窗口了
        function loadTopWindow(){
            if (self != top) {
                window.top.location= document.URL;
            }
        }
    </script>
</head>
<body onload="loadTopWindow()" style="background: url(/Public/image/login/bg_login.png) no-repeat;-webkit-background-size: cover;background-size: cover;" >
<div class="login-main flex">
	<div class="login-win">
		<div class="login-box-header">
			<img style="width:60px;height:60px;position:relative;top:-8px;right:12px;" src="/Public/image/login/logo.jpg" alt="">
			<span class="logo-text">双重预防机制运行信息管理平台</span>
		</div>
		<div class="login-box">
			<div class="login-left-box" style="width:600px;height: 375px;float: left;border-radius: 4px;background: url(/Public/image/login/login-left.jpg) no-repeat;background-size: cover;">
			</div>
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form" style="float:left;width: 300px;padding: 100px 50px 0 50px;position: relative;">
				<p class="login-tips" style="position: absolute;top: 0;left: 0;width: 400px;height: 90px;text-align: left;text-indent: 20px;line-height: 90px;background: #3993d6;font-size: 30px;color: #fff;">请登录</p>
				<p class="login-erro"></p>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
					<input type="text" name="username" autofocus="" id="LAY-user-login-username" lay-verify="required" placeholder="用户名/手机号/邮箱/身份证号登录" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
					<input type="password" name="password" autocomplete="new-password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class="layui-row">
						<div class="layui-col-xs7">
							<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
							<input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
						</div>
						<div class="layui-col-xs5">
							<a href="javascript:;" onclick="codeC()" class="code-box">
								<img src="/captcha?uuid=${picture_uuid}" layadmin-user-login-codeimg" id="LAY-user-get-vercode"/>
							</a>
						</div>
					</div>
				</div>
                <input type="hidden" name="client_id" value="${client_id}">
                <input type="hidden" name="backurl" value="${redirect_uri}">
                <input type="hidden" name="picture_uuid" value="${picture_uuid}">
				<div class="layui-form-item">
					<button class="layui-btn layui-btn-fluid login-btn" lay-submit="" lay-filter="LAY-user-login-submit">登 入</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/login/login.js"></script>
</body>
</html>
