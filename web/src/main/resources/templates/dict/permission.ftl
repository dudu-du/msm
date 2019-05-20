<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>权限管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table layui-col-md12">
            <div class="layui-card">
                <table class="layui-hide" id="test" lay-filter="test"></table>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <div class="table-btns-box">
                            <button class="layui-btn layui-btn-sm" lay-event="addTeacher"><i class="iconfont icon-zidian"></i>添加权限</button>
                        </div>
                        <div class="table-btns-num">
                            <span class="table-cho-num"></span>
                            <button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>删除</button>
                        </div>
                        <div class="table-btns-right2">
                            <div class="table-search-box clearfix">
                                <input type="text" placeholder="请输入名称进行搜索">
                                <i class="layui-icon layui-icon-search" lay-event="search"></i>
                            </div>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <#--<a class="layui-btn layui-btn-xs" lay-event="child">子项</a>-->
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/dict/permission.js"></script>
</body>
</html>