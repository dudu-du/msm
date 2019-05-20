<!DOCTYPE html>
<html lang="ch">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<title>绑定班牌</title>
	<link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
	<link rel="stylesheet" href="/Public/css/public-color.css">
	<link rel="stylesheet" href="/Public/css/public-yun.css">
	<link rel="stylesheet" href="/Public/css/class/bindDev.css">
</head>
<body>
<div class="layui-body-main main-p-75">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12 welsee-table">
			<div class="layui-card">
				<table class="layui-hide" id="test1" lay-filter="test1"></table>
				<script type="text/html" id="toolbarDemo1">
					<div class="layui-btn-container">
						<div class="table-btns-box">
							<span class="table-title"><i class="iconfont icon-tanhao"></i>已经分配班牌的班级重新分配将覆盖原有选择</span>
						</div>
						<div class="table-btns-num">
							<span class="table-cho-num"></span>
							<button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>解绑</button>
						</div>
						<div class="table-btns-right2 layui-hide">
							<div class="table-search-box clearfix">
								<input type="text" class="classS" placeholder="输入班级名称或班牌名称搜索">
								<i class="layui-icon layui-icon-search" lay-event="search"></i>
							</div>
						</div>
					</div>
				</script>
				<script type="text/html" id="barDemo">
					<a class="layui-btn layui-btn-xs" lay-event="edit">分配班牌</a>
					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">解除绑定</a>
				</script>
			</div>
		</div>
	</div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/reconnecting-websocket.js"></script>
<script src="/Public/js/websocket.js"></script>
<script src="/Public/js/ajax-base.js"></script>
<script src="/Public/js/class/bindClassDev.js"></script>
</body>
</html>
