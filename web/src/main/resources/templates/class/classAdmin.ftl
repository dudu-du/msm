<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>班级管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table welsee-table-onlynew layui-col-md12">
            <div class="welsee-table-child">
                <div class="layui-card">
                    <table class="layui-hide" id="test" lay-filter="test"></table>
                    <script type="text/html" id="toolbarDemo">
                        <div class="layui-btn-container">
                            <div class="table-btns-box">
                                <button class="layui-btn layui-btn-sm" lay-event="addTeacher"><i class="iconfont icon-shanchu1"></i>批量建班</button>
                            </div>
                            <div class="table-btns-num">
                                <span class="table-cho-num"></span>
                                <button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>删除</button>
                            </div>
                            <div class="table-btns-right2">
                                <#--<div class="table-search-box clearfix">-->
                                <#--<input type="text" placeholder="搜索班主任名称等信息进行查询">-->
                                <#--<i class="layui-icon layui-icon-search" lay-event="search"></i>-->
                                <#--</div>-->
                                <div class="layui-inline" title="表格筛选" lay-event="screenON"><i
                                            class="layui-icon layui-icon-spread-left"></i></div>
                            </div>
                        </div>
                    </script>
                    <script type="text/html" id="barDemo">
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
                    </script>
                </div>
            </div>
        </div>
        <div class="welsee-screen layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-header welsee-card-header2">
                    <span>筛查项目列表</span>
                    <a href="javascript:;" lay-submit="" lay-filter="cancel"><i
                            class="layui-icon layui-icon-close"></i></a>
                </div>
                <div class="layui-card-body layui-form" lay-filter="screenON">
                    <div class="layui-form-item">
                        <label class="layui-form-label">学段</label>
                        <div class="layui-input-inline">
                            <select name="section" lay-filter="sectionCode">
                                <option value="">请选择学段</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">年级</label>
                        <div class="layui-input-inline">
                            <select name="grade" lay-filter="gradeCode">
                                <option value="">请选择年级</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="screen">筛查</button>
                        <button class="layui-btn layui-btn-sm layui-btn-primary" lay-submit="" lay-filter="reload">
                            重置
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/ajax-base.js"></script>
<script src="/Public/js/class/class.js"></script>
</body>
</html>