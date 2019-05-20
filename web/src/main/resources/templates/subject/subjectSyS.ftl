<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>科目管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
</head>
<body>
<div class="page-map layui-hide">
    <span class="layui-breadcrumb">
        <a href="/View/listSubjectByOrgId"><i class="layui-icon layui-icon-left"></i> 返回</a>
    </span>
</div>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75 childPage" id="subjectListPage" data-code="${section}" data-name="${sectionName}">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table layui-col-md12">
            <div class="layui-card">
                <table class="layui-hide" id="test1" lay-filter="test1"></table>
                <script type="text/html" id="toolbarDemo1">
                    <div class="layui-btn-container">
                        <div class="table-btns-box">
                            <button class="layui-btn layui-btn-sm  layui-btn-normal" lay-event="addOffice"><i class="iconfont icon-xueke"></i>添加学科</button>
                        </div>
                        <div class="table-btns-num">
                            <span class="table-cho-num"></span>
                            <button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i class="iconfont icon-shanchu1"></i>删除</button>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="barDemo">
                    <button class="layui-btn layui-btn-xs {{ d.schoolId=='0' && d.oi != '0' ? 'layui-btn-disabled' : ''}}" lay-event="edit" {{(d.schoolId=='0') && d.oi != '0' ? 'disabled' : ''}}>编辑</button>
                    <button class="layui-btn layui-btn-danger layui-btn-xs {{ d.schoolId=='0' && d.oi != '0' ? 'layui-btn-disabled' : ''}}" lay-event="del" {{(d.schoolId=='0') && d.oi != '0' ? 'disabled' : ''}}>删除</button>
                </script>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/subject/subjectSyS.js"></script>
</body>
</html>