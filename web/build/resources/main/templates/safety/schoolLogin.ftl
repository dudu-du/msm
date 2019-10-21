<!DOCTYPE html>
<html lang="ch"  style="background: #fff;">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
		<title>登录</title>
		<link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
		<link rel="stylesheet" href="/Public/css/public-color.css">
		<link rel="stylesheet" href="/Public/css/public-yun.css">
		<link rel="stylesheet" href="/Public/css/login/schoolLogin.css">
		<link rel="icon" type="image/x-icon" href="/Public/image/bitbug_favicon.ico" sizes="48x48">
        <style>
            .sLogin-top{
                background: url(https://oss-pics-huabei5.oss-cn-huhehaote.aliyuncs.com/${school_pic}) no-repeat center center;
            }
            .schoolName{
                color: ${font_colour};
            }
            .schoolName .ch{
                font-family: ${school_font};
            }
		</style>
	</head>
	<body style="background: #fff;">
		<div class="sLogin-top" >
			<div class="layui-container">
				<div class="sLogo-box">
					<img class="sLogo" src="https://oss-pics-huabei5.oss-cn-huhehaote.aliyuncs.com/${school_icon}" alt="">
					<div class="schoolName">
						<p class="ch">${school_name}</p>
						<p class="en">${school_name}</p>
					</div>
				</div>
				<div class="sLogin-box layui-form">
					<h3>密码登录</h3>
					<input type="text" placeholder="请输入账号" name="userName" lay-verify="required" autocomplete="off" class="layui-input sLoginName">
					<input type="password" placeholder="请输入密码" name="password" lay-verify="required" autocomplete="off" class="layui-input sLoginPassword">
                    <input type="hidden" name="client_id" value="${client_id}">
                    <input type="hidden" name="backurl" value="${redirect_uri}">
					<button class="layui-btn layui-btn-lg layui-btn-normal sLoginBtn" lay-filter="fromContent" lay-submit="">登录</button>
				</div>
			</div>
		</div>
		<div class="layui-container sLogin-bottom">
			<div class="layui-row layui-col-space30">
				<div class="layui-col-md4">
					<div class="schoolData">
						<fieldset class="layui-elem-field layui-field-title">
							<legend>带标题的横线</legend>
						</fieldset>
						<p>字在记录口语的时候，必然随着口语的词的顺序进行。但是把口语唯一的时间顺序转换成空间顺序的时候会遇到多种选择。早期文字例如埃及圣书体书写方向不确定。一般是写到哪儿就从哪儿继续写，叫做牛耕式写法。从一个水平方向开始，然后在一行结尾的位置直接到下一行开始反方向书写</p>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="schoolData">
						<fieldset class="layui-elem-field layui-field-title">
							<legend>带标题的横线</legend>
						</fieldset>
						<p>字在记录口语的时候，必然随着口语的词的顺序进行。但是把口语唯一的时间顺序转换成空间顺序的时候会遇到多种选择。早期文字例如埃及圣书体书写方向不确定。一般是写到哪儿就从哪儿继续写，叫做牛耕式写法。从一个水平方向开始，然后在一行结尾的位置直接到下一行开始反方向书写</p>
					</div>
				</div>
				<div class="layui-col-md4">
					<div class="schoolData">
						<fieldset class="layui-elem-field layui-field-title">
							<legend>带标题的横线</legend>
						</fieldset>
						<p>早期文字例如埃及圣书体书写方向不确定。一般是写到哪儿就从哪儿继续写，叫做牛耕式写法。从一个水平方向开始，然后在一行结尾的位置直接到下一行开始反方向书写</p>
					</div>
				</div>
			</div>
		</div>
		<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
		<script src="/node_modules/layui-src/dist/layui.js"></script>
        <script src="/Public/js/login/schoolLogin.js"></script>
	</body>
</html>
