<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>基本资料</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/node_modules/cropper-master/dist/cropper.css">
    <link rel="stylesheet" href="/Public/css/user/userinfo.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-userinfo layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">修改密码</div>
                <div class="layui-tab layui-tab-brief" lay-filter="devTab">
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <div class="layui-form" lay-filter="changePassWord">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">当前密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="nowPassWord" lay-verify="nowPassWord" value="" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">新密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="newPassWord" value="" lay-verify="newPassWord" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">确认新密码</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="newPassWordYew" value="" lay-verify="newPassWordYew" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <input type="hidden" name="loginId" id="currentLoginId" value=${loginId}>
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn" lay-submit="" lay-filter="changePassWord">保存</button>
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
<script src="/node_modules/cropper-master/dist/cropper.js"></script>
<script src="/Public/js/user/userinfo.js"></script>
</body>
</html>