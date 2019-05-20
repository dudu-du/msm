<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>局管理员</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/user/officeAdmin.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="safety-table layui-col-md12">
            <div class="layui-card">
                <table class="layui-hide" id="test" lay-filter="test"></table>
                <script type="text/html" id="toolbarDemo">
                    <div class="layui-btn-container">
                        <div class="table-btns-box">
                            <button class="layui-btn layui-btn-sm  layui-btn-normal" lay-event="addOffice"><i class="iconfont icon-renyuanzengjia"></i>添加局管理员</button>
                        </div>
                        <div class="table-btns-num">
                            <span class="table-cho-num"></span>
                            <button class="layui-btn layui-btn-sm safety-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>删除</button>
                        </div>
                        <div class="table-btns-right">
                            <div class="table-search-box clearfix">
                                <!--<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">-->
                                <input type="text" placeholder="请输入机构进行查询" value="{{ searchText }}">
                                <i class="layui-icon layui-icon-search" lay-event="search"></i>
                            </div>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="barDemo">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-xs" {{ d.disable=='1' ? 'layui-btn-disabled' : ''}}" lay-event="password" {{(d.disable=='1') ? 'disabled' : ''}}>密码重置</a>
                    <#--<a class="layui-btn layui-btn-danger layui-btn-xs" name="{{ d.disable=='1' ? 'hidden' : ''}}" lay-event="disable">禁用</a>-->
                    <#--<a class="layui-btn layui-btn-xs" name="{{ d.disable=='0' ? 'hidden' : ''}}" lay-event="enable">启用</a>-->
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                </script>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/user/office.js"></script>
</body>
</html>