<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>机构管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/iconfont/iconfont.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/node_modules/layui-extends/eleTree.css">
    <link rel="stylesheet" href="/Public/css/org/org.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4 jiaoyujigou">
            <div class="layui-card parent-position">
                <div class="layui-card-header safety-card-header">
                    <span>教育机构</span>
                    <div class="mechanismBtn">
                        <#--<button name="mechanismsort" class="layui-btn safety-btn-primary layui-btn-sm safety-card-btn"><i class="iconfont icon-yijijigou"></i>排序</button>-->
                        <button name="mechanism" class="layui-btn safety-btn-primary layui-btn-sm safety-card-btn"><i class="iconfont icon-yijijigou"></i>新建一级机构</button>
                    </div>
                </div>
                <div class="layui-card-body tree-body position-child top-57">
                    <form  class="layui-form">
                        <div class="lay-auth-tree-box">
                            <ul id="LAY-auth-tree-index"></ul>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md8 departUserList">
            <div class="safety-table layui-col-md12">
                <div class="layui-card">
                    <table class="layui-hide" id="treeTable" lay-filter="treeTable"></table>
                    <script type="text/html" id="toolbarDemo">
                        <div class="layui-btn-container">
                            <div class="table-btns-box">
                                <button class="layui-btn layui-btn-sm" lay-event="adddepar"><i class="iconfont icon-renyuanzengjia"></i>添加部门</button>
                                <span id="orgcontent"></span>
                            </div>

                        </div>
                    </script>
                    <script type="text/html" id="barDemo">
                        <a class="layui-btn layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="add">新增子部门</a>
                        <a class="layui-btn layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="edit">编辑</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs {{d.LAY_TABLE_INDEX == '0'? 'layui-hide':''}}" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/org/mechanism.js"></script>
</body>
</html>