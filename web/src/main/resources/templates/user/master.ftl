<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>班主任管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="safety-table safety-table-onlynew layui-col-md12">
            <div class="safety-table-child">
                <div class="layui-card">
                    <table class="layui-hide" id="test" lay-filter="test"></table>
                    <script type="text/html" id="toolbarDemo">
                        <div class="layui-btn-container">
                            <div class="table-btns-box">
                                <button class="layui-btn layui-btn-sm" lay-event="addTeacher"><i class="iconfont icon-renyuanzengjia"></i>添加班主任</button>
                            </div>
                            <div class="table-btns-num">
                                <span class="table-cho-num"></span>
                                <button class="layui-btn layui-btn-sm safety-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>删除</button>
                            </div>
                            <div class="table-btns-right2">
                                <div class="table-search-box clearfix">
                                    <input type="text" placeholder="搜索用户姓名进行查询" value="{{ searchText }}">
                                    <i class="layui-icon layui-icon-search" lay-event="search"></i>
                                </div>
                                <div class="layui-inline" title="表格筛选" lay-event="screenON"><i class="layui-icon layui-icon-spread-left"></i></div>
                            </div>
                        </div>
                    </script>
                    <script type="text/html" id="barDemo">
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
        <div class="safety-screen layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header safety-card-header2">
                    <span>筛查项目列表</span>
                    <a href="javascript:;" lay-submit="" lay-filter="cancel"><i class="layui-icon layui-icon-close"></i></a>
                </div>
                <div class="layui-card-body layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">学段</label>
                        <div class="layui-input-inline">
                            <select name="section" lay-filter="section" lay-verify="section" lay-search="">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">年级</label>
                        <div class="layui-input-inline">
                            <select name="grade" lay-filter="grade" lay-verify="grade" lay-search="">
                                <option value=""></option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">班级</label>
                        <div class="layui-input-inline">
                            <select name="class" lay-filter="class" lay-verify="class" lay-search="">
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="screen">筛查</button>
                        <button class="layui-btn layui-btn-sm layui-btn-normal" lay-submit="" lay-filter="reload">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/user/master.js"></script>
</body>
</html>