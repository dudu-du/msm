<!DOCTYPE html>
<html lang="ch">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
	<title>绑定校牌</title>
	<link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
	<link rel="stylesheet" href="/node_modules/layui-extends/formSelects-v4.css" />
	<link rel="stylesheet" href="/Public/css/public-color.css">
	<link rel="stylesheet" href="/Public/css/public-yun.css">
	<style>
		.SdevBox{
			max-height:220px;
			overflow: auto;
		}
	</style>
</head>
<body>
<div class="layui-body-main main-p-75">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12 welsee-table">
			<div class="layui-card">
				<table class="layui-hide" id="test" lay-filter="test"></table>
				<script type="text/html" id="toolbarDemo">
					<div class="layui-btn-container">
						<div class="table-btns-box">
							<button class="layui-btn layui-btn-sm" lay-event="addSchoolDev">批量分配</button>
						</div>
						<div class="table-btns-num">
							<span class="table-cho-num"></span>
							<button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="layui-icon"></i>删除</button>
						</div>
						<#--<div class="table-btns-right2">
							<div class="table-search-box clearfix">
								<input type="text" class="schoolS" placeholder="搜索用户姓名进行查询">
								<i class="layui-icon layui-icon-search" lay-event="search"></i>
							</div>
						</div>-->
					</div>
				</script>
				<script type="text/html" id="barDemo">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
<script src="/Public/js/school/bindSchoolDev.js"></script>
</body>
</html>
