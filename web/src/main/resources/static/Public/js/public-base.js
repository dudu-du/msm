/**
 * Created by Administrator on 2018/11/9.
 */
/**
 * 获取页面地址端口origin
 * oss图片地址前缀pictureOssUrl
 * orgid,loginid----baseParam
 * @type {string}
 */
var origin = window.location.origin;
var hostname = window.location.hostname;
var websocketurl = "wss://"+hostname+"/ws";
// var pictureOssUrl = "https://welsee-edu.oss-cn-beijing.aliyuncs.com/";
var pictureOssUrl = "";
// var pictureOssUrl = "https://oss-pics-huabei5.oss-cn-huhehaote.aliyuncs.com/";
var orgId = $('input[name="orgId"]').val(),loginId = $('input[name="loginId"]').val();
if(orgId != ''&&orgId != undefined&&orgId != null){
    layui.sessionData('base',{
        key:'paramObject',
        value:{
            oid:orgId,
            lid:loginId
        }
    });
}
var pathname = window.location.pathname;
var baseParam = layui.sessionData('base');
var org_id = baseParam.paramObject.oid,loginId = baseParam.paramObject.lid,pageTree,searchText='';
/*图标文字加载*/
layui.link('//at.alicdn.com/t/font_912670_1i8p2xstcq9.css');
//截取地址栏参数
function GetString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return unescape(r[2]); return null;
}
//生成uuid
function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}
function guid() {
    return (S4()+S4()+S4()+S4());
}
/**
 * 页面超市跳转
 */
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
        //通过XMLHttpRequest取得响应头，sessionstatus 
        var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
        var basePath = XMLHttpRequest.getResponseHeader('basePath');
        if(sessionstatus=="timeout"){
            //这里怎么处理在你，这里跳转的登录页面
            var top = getTopWinow();
            var yes = confirm('由于您长时间没有操作, 与服务器会话已过期, 请重新登录!');
            if (yes){top.location.href =basePath;}

        }
    }
});

/**
  * 在页面中任何嵌套层次的窗口中获取顶层窗口
  * @return 当前页面的顶层窗口对象
  */
function getTopWinow(){
    var p = window;
    while(p != p.parent){
        p = p.parent;
    }
    return p;
}
/**
 * base64转file
 */
function dataURLtoFile(dataurl, filename) {//将base64转换为文件
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

/**
 * layui Table 去除点击保留选中
 */
layui.use('table',function(){
    var table = layui.table;
    table.on('row(test)', function(obj){
        var tr = obj.tr;
        $(tr).removeClass('layui-table-click');
    });
    table.on('row(table)', function(obj){
        var tr = obj.tr;
        $(tr).removeClass('layui-table-click');
    });
})
/**
 * 输入框去掉空格
 */
$('body').on('blur','input:not(".noClearSpace"),textarea:not(".noClearSpace")',function(){
    var nowVal = $(this).val().replace(/\s/g,"");
    $(this).val(nowVal);
}).on('focus','input:not(".noClearSpace"),textarea:not(".noClearSpace")',function(){
    var nowVal = $(this).val().replace(/\s/g,"");
    $(this).val(nowVal);
});
