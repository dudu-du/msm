<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>应用管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/client/app.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table layui-col-md12">
            <div class="tableBox">
                <table class="layui-hide layui-table" id="test" lay-filter="test"></table>
                <!-- 应用图片 -->
                <script type="text/html" id="appImg">
                            <img src="{{ d.logo }}" style="height:64px;width:64px" alt="">
                        </script>
                <!-- 应用状态 -->
                <script type="text/html" id="appState">
                    {{#  if(d.sex === '女'){ }}
                    <span style="color: #ea3d3c;">{{ d.sex }}</span>
                    {{#  } else { }}
                    {{ d.sex }}
                    {{#  } }}
                </script>
                <!-- 添加应用按钮 -->
                <script type="text/html" id="addApp">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-sm" lay-event="getCheckData"><i class="iconfont icon-yingyong"></i>添加应用</button>
                    </div>
                </script>
                <!-- 操作按钮 -->
                <script type="text/html" id="operation">
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
<script src="/Public/js/client/appList.js"></script>
</body>
</html>
