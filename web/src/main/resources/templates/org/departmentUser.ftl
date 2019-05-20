<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>部门人员</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/node_modules/layui-extends/formSelects-v4.css" />
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
                    <span>学校部门</span>
                    <div class="mechanismBtn">
                        <#--<button name="mechanismsort" class="layui-btn safety-btn-primary layui-btn-sm safety-card-btn"><i class="iconfont icon-yijijigou"></i>排序</button>-->
                    </div>
                </div>
                <div class="layui-card-body tree-body position-child top-57">
                    <form  class="layui-form">
                        <div class="lay-auth-tree-box depart-tree-box">
                            <ul id="LAY-auth-tree-index"></ul>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md8 departUserList">
            <div class="safety-table layui-col-md12">
                <div class="layui-card">
                    <table class="layui-hide" id="test" lay-filter="test"></table>
                    <script type="text/html" id="toolbarDemo">
                        <div class="layui-btn-container">
                            <div class="table-btns-box">
                                {{# if(butType == 'OT_SCHOOL'){ }}
                                <button class="layui-btn layui-btn-sm" lay-event="adddepar"><i class="iconfont icon-renyuanzengjia"></i>重置部门</button>
                                {{# }else{ }}
                                <button class="layui-btn layui-btn-sm" lay-event="adduser"><i class="iconfont icon-renyuanzengjia"></i>添加人员</button>
                                {{# } }}
                            </div>
                            <div class="table-btns-num">
                                <span class="table-cho-num"></span>
                                {{# if(butType == 'OT_SCHOOL'){ }}
                                <button class="layui-btn layui-btn-sm" lay-event="addToDepart"><i class="iconfont icon-shanchu1"></i>添加到部门</button>
                                {{# }else{ }}
                                <button class="layui-btn layui-btn-sm safety-btn-danger" lay-event="removeUser"><i class="iconfont icon-shanchu1"></i>移除人员</button>
                                {{# } }}
                            </div>
                        </div>
                    </script>
                    <script type="text/html" id="barDemo">
                        <a class="layui-btn layui-btn-xs {{d.isDepartment == '0' ? '' : 'layui-hide'}}" lay-event="edit">编辑部门</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs {{d.isDepartment == '0' ? 'layui-hide' : ''}}" lay-event="del">移除</a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/org/departmentUser.js"></script>
</body>
</html>