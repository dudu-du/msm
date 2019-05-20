<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>应用管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/client/app.css">
</head>
<body>
<div class="page-map">
    <span class="layui-breadcrumb">
        <a href="/View/listClient"><i class="layui-icon layui-icon-left"></i> 返回</a>
    </span>
</div>
<!-- 内容主体区域 -->
<div style="padding: 15px" class="childPage">
    <div class="setApp-box layui-form">
        <div class="form-box">
            <div lay-filter="example">
                <div class="layui-form-item">
                    <label class="layui-form-label">应用名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="title" autocomplete="off"
                               placeholder="请输入应用名称" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">应用首页</label>
                    <div class="layui-input-block">
                        <input type="tel" name="url" lay-verify="url" autocomplete="off" placeholder="请输入应用首页"
                               class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">应用图标</label>
                    <div class="layui-input-block">
                        <div class="appimg_box">
                            <div class="icon-box">
                                <div class="icon-left layui-upload">
                                    <div id="upIcon">
                                        <img src="/Public/image/client/imgs.png" class="layui-upload-img" id="demo1">
                                    </div>
                                    <H4>上传图标</H4>
                                </div>
                                <#--<div class="icon-right">
                                    <div>
                                        <img src="/Public/image/client/imgs.png" alt="">
                                    </div>
                                    <H4>示例图标</H4>
                                </div>-->
                            </div>
                            <p>200*200尺寸，小于500k，png，jpg格式</p>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">应用介绍</label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入应用介绍(最多输入70个字，包含标点符号等)" maxlength="70" lay-verify="appContent" name="appContent" class="layui-textarea"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="line"></div>
        <div class="save-box">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal layui-btn-sm send"  lay-submit="" lay-filter="sendSave">
                        提交
                    </button>
                    <button class="layui-btn layui-btn-sm layui-btn-primary" name="return">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" name="pictureOss" value=${pictureOss}>
<script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
<script src="/node_modules/layui-src/dist/layui.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/client/setApp.js"></script>
</body>
</html>
