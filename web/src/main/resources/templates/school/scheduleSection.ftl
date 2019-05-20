<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>课表管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/school/schedule.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="scheduleSearch layui-form">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">学段</label>
            <div class="layui-input-inline">
                <select name="section" lay-filter="section">
                    <option value="">请选择学段</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">年级</label>
            <div class="layui-input-inline">
                <select name="grade" lay-filter="grade">
                    <option value="">请先选择学段</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">班级</label>
            <div class="layui-input-inline">
                <select name="class" lay-filter="class">
                    <option value="">请先选择学段年级</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <button class="layui-btn layui-btn-sm layui-btn-normal scheduleSearchBtn">查询</button>
            <div class="layui-btn-group">
                <button class="layui-btn layui-btn-primary layui-btn-sm" lay-event="download"><i class="iconfont icon-shuangsechangyongtubiao-"></i>下载模板</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" lay-event="import" id="uploadExcel"><i class="iconfont icon-daoru"></i>导入</button>
                <button class="layui-btn layui-btn-primary layui-btn-sm" lay-event="export"><i class="iconfont icon-daochu"></i>导出</button>
            </div>
            <button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>清空所有课表</button>
        </div>
    </div>
</div>
<div class="layui-body-main main-p-75 scheduleMain">
    <div class="scheduleDataNull layui-hide">
        <span></span>
        <p><i class="iconfont icon-tanhao"></i>请选择学段，年级，班级等相应的条件搜索课表</p>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/ajax-base.js"></script>
<script src="/Public/js/school/schedule.js"></script>
</body>
</html>