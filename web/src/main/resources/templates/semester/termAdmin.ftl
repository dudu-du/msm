<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>学期管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/semester/term.css">
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
                            <button class="layui-btn layui-btn-sm  layui-btn-normal" lay-event="addOffice"><i class="iconfont icon-yijijigou"></i>添加学期</button>
                        </div>
                        <div class="table-btns-num">
                            <span class="table-cho-num"></span>
                            <button class="layui-btn layui-btn-sm welsee-btn-danger" lay-event="del"><i
                                    class="layui-icon"></i>删除
                            </button>
                        </div>
                    </div>
                </script>
                <script type="text/html" id="termState">
                    {{#  if(d.isCurrentSemester === 1){ }}
                    {{ d.semesterName }}<span style="height:22px;line-height:22px;display:inline-block;vertical-align: middle;margin-bottom: 3px;color:#fff;background:#0090ff;padding:0px 5px;margin-left: 5px;border-radius: 2px;font-size:12px">当前学年</span>
                    {{#  } else { }}
                    {{ d.semesterName }}
                    {{#  } }}
                </script>
                <script type="text/html" id="yes">
                    {{#  if(d.isCurrentSemester === 1){ }}
                    是
                    {{#  } else { }}
                    否
                    {{#  } }}
                </script>
                <script type="text/html" id="barDemo">
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
<script src="/Public/js/semester/term.js"></script>
</body>
</html>