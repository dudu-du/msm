<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
    <title>科目管理</title>
    <link rel="stylesheet" href="/node_modules/layui-src/dist/css/layui.css">
    <link rel="stylesheet" href="/node_modules/layui-extends/formSelects-v4.css" />
    <link rel="stylesheet" href="/Public/css/public-yun.css">
    <link rel="stylesheet" href="/Public/css/public-color.css">
    <link rel="stylesheet" href="/Public/css/school/schoolView.css">
</head>
<body>
<!-- 内容主体区域 -->
<div class="layui-body-main main-p-75">
    <div class="layui-row layui-col-space15">
        <div class="welsee-table layui-col-md12">
            <div class="layui-card">
                <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">基础信息</li>
                        <li class="">学校介绍</li>
                        <li class="">公众号</li>
                    </ul>
                    <div class="layui-tab-content">
                        <div class="layui-tab-item layui-show">
                            <div class="school-info-box layui-form" lay-filter="schoolInfo1">
                                <div class="layui-form-item schoolBadge">
                                    <a href="javascript:;" class="chooseBadgeImg">
                                        <span>点击上传校徽</span>
                                        <img src="" alt="">
                                        <input type="hidden" name="schoolBadge" value="">
                                    </a>
                                    <p>建议png透明格式图片小于500kb</p>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">学校名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="schoolname" readonly="" disabled="" lay-verify="schoolname" autocomplete="off"
                                               placeholder="请输入学校名称" class="layui-input input-no-line">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">校英文名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="englishname" lay-verify="englishname" autocomplete="off" placeholder="为保证显示效果，输入小写字母，默认转化为大写字母"
                                               class="layui-input noClearSpace">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">学校校训</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="motto1" lay-verify="motto1" autocomplete="off"
                                               placeholder="学校校训" class="layui-input">
                                    </div>
                                    <div class="layui-input-block">
                                        <input type="text" name="motto2" lay-verify="motto2" autocomplete="off"
                                               placeholder="学校校训第二行（可不填）" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item chooseSchool">
                                    <label class="layui-form-label">学校定位</label>
                                    <div class="layui-input-block">
                                        <#--<select name="localid" id="localid" xm-select="localSearch" lay-search xm-select-skin="normal" xm-select-search="" xm-select-radio>
                                            <option value="">请输入您的城市（用于显示实时天气）</option>
                                        </select>-->
                                            <input type="text" id="cityCn" name="cityCn" lay-verify="cityCn" autocomplete="off" placeholder="请输入您的城市（用于显示实时天气）"
                                                   class="layui-input" />
                                            <input type="hidden" id="cityId" name="cityId"/>
                                        <#--<p class="localTig">设备上的天气显示将依赖于您填写的地点来定位</p>-->
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">学校编码</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="schoolcode" lay-verify="schoolcode" autocomplete="off"
                                               placeholder="学校编码" class="layui-input" readonly>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">学校域名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="schoolweb" lay-verify="schoolweb" autocomplete="off"
                                               placeholder="学校域名" class="layui-input" readonly>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="schoolinfo-btn-box">
                                        <button class="layui-btn layui-btn-normal send"  lay-submit="" lay-filter="sendSave">确定</button>
                                        <#--<button class="layui-btn layui-btn-primary" name="return">取消</button>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item layui-form" lay-filter="schoolInfo2">
                            <div class="schoolBrief clearfix">
                                <a href="javascript:;" class="briefImage">
                                    <span>选择一张学校图片上传不超过500KB</span>
                                    <img src="" alt="">
                                    <input type="hidden" value="" name="schoolImageBig">
                                </a>
                                <!--编辑器-->
                                <script id="container1"  name="content" type="text/plain"></script>
                                <#--<textarea class="briefText noClearSpace" name="briefText" placeholder="介绍学校的历史，师资，学校特色等情况。" cols="10" wrap="hard"></textarea>-->
                            </div>
                            <div class="layui-form-item">
                                <div class="schoolinfo-btn-box">
                                    <button class="layui-btn layui-btn-normal send"  lay-submit="" lay-filter="sendSave2">确定</button>
                                    <#--<button class="layui-btn layui-btn-primary" name="return">取消</button>-->
                                </div>
                            </div>
                        </div>
                        <div class="layui-tab-item">
                            <div class="wechat-info-box layui-form" lay-filter="schoolInfo3">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">公众号名称</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="wechatname" lay-verify="wechatname" autocomplete="off"
                                               placeholder="输入您学校公众号名称" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">公众号biz标识</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="wechatbiz" lay-verify="wechatbiz" autocomplete="off"
                                               placeholder="用手机复制公众号文章链接，您可在该链接中找到biz标识" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">功能介绍</label>
                                    <div class="layui-input-block">
                                        <textarea class="briefText" maxlength="124" name="wechatcon" lay-verify="wechatcon" placeholder="功能介绍长度为4-120个字。" cols="10" wrap="hard"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="schoolinfo-btn-box">
                                        <button class="layui-btn layui-btn-normal send"  lay-submit="" lay-filter="sendSave3">确定</button>
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
<script src="/node_modules/ueditor/ueditor.configjj.js"></script>
<script src="/node_modules/ueditor/ueditor.all.js"></script>
<script src="/Public/js/public-base.js"></script>
<script src="/Public/js/school/schoolView.js"></script>
</body>
</html>