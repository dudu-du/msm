<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>敏感词设置</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/sensitive/sensitive.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table welsee-table-onlynew layui-col-md12">
            <div class="welsee-table-child">
                <div class="layui-card">
                    <div class="layui-card-header">
                        <span>敏感词汇总</span>
                        <div class="table-btns-right2">
                            <div class="table-search-box clearfix">
                                <input type="text" name="search" placeholder="请输入敏感词进行搜索" value="">
                                <i class="layui-icon layui-icon-search" lay-event="search"></i>
                            </div>
                        </div>
                    </div>
                    <div class="layui-tab layui-tab-brief" lay-filter="devTab">
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">
                                <div class="layui-form" lay-filter="userInfo">
                                    <div class="layui-form-item addSensitive">
                                        <div class="layui-input-inline">
                                            <input type="text" name="name" lay-verify="name" placeholder="请输入" autocomplete="off" class="layui-input">
                                        </div>
                                        <button class="layui-form-label" lay-submit="" lay-filter="add">添加</button>
                                    </div>
                                    <div class="layui-form-item listSensitive">
                                        <ul class="flex">
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀自杀自杀自杀自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <span>自杀</span>
                                                    <i class="iconfont icon-delete_fill"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
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
<script src="/Public/js/sensitive/sensitive.js"></script>
</body>
</html>