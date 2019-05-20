<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>机构管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/org/org.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table layui-col-md12 welsee-tree-only">
            <div class="layui-card">
                <table class="layui-hide" id="treeTable" lay-filter="treeTable"></table>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <div class="table-btns-box">
                            <button class="layui-btn layui-btn-sm" lay-event="adddepar"><i class="iconfont icon-renyuanzengjia"></i>添加部门</button>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="add">添加子部门</a>
                    <a class="layui-btn layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="del">删除</a>
                </script>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/org/department.js"></script>
</body>
</html>