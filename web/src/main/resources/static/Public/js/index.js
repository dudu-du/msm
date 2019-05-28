/**
 * Created by Administrator on 2018/11/9.
 */
/**
 * base var
 */
/*var pageTil = sessionStorage.getItem('titl');
if(pageTil == null){
    $('title').html('基础平台-智慧教育服务平台');
}else{
    $('title').html(pageTil+'-智慧教育服务平台');
}*/

//退出账号
function logOut(){
    sessionStorage.clear();
    window.location = "/logout";
}

var nav = layui.sessionData('nav');
if(nav.obj){
    $('.layui-breadcrumb a').remove();
}
layui.use(['element','layer'], function(){
    var element = layui.element,
        layer = layui.layer;
    window.onload=function(){
        var userPic;
        if($('input[name="userPic"]').val() == ''){
            userPic = '/Public/image/user/schoolAdmin.png';
        }else{
            userPic = pictureOssUrl+$('input[name="userPic"]').val();
        }
        $('.layui-nav-img').attr('src',userPic);
        if(nav.obj){
            if(nav.obj.t == 1){
                $('.layui-nav-tree a[lay-href="'+nav.obj.h+'"]').parents('li').addClass('layui-this');
            }else{
                $('.layui-nav-tree a[lay-href="'+nav.obj.h+'"]').parents('li').addClass('layui-nav-itemed');
                $('.layui-nav-tree a[lay-href="'+nav.obj.h+'"]').parent('dd').addClass('layui-this');
            }
            $(nav.obj.map).appendTo('.layui-breadcrumb');
            $('#lay-iframe').attr('src',nav.obj.h);
            element.render('breadcrumb');
        }
    };
    getMenu();
    function getMenu(){
        $('.layui-nav-tree').remove();
        $.ajax({
            url:'/menu',
            type:'GET',
            async: false,
            dataType: 'json',
            success: function(res){
                var menuData = res.data;
                if(res.success){
                    $('.layui-side-scroll').append(eachMenu(menuData));
                    element.render();
                }
            }
        })
    }
    function eachMenu(Menu){
        var str = '<ul class="layui-nav layui-nav-tree" lay-filter="leftnav">';
        layui.each(Menu,function(index,item){
            var NameO,NameT;
            if(item.menuname == '设备管理'){
                NameO = 'devO';
            }else{
                NameO = '';
            }
            var hasChild = item.menus.length>0 ? 1 : 0,childMenu = item.menus,menuType = item.isThirdWeb;
            if(childMenu.length>0){
                var childMenuHtml = '<dl class="layui-nav-child">';
                layui.each(childMenu,function(index,childItem){
                    var thisUrl;
                    if(childItem.menuname == '设备管理'){
                        NameT = 'devT'
                    }else{
                        NameT = '';
                    }
                    var hasChildList = childItem.menus.length>0 ? 1 : 0,childMenuList = childItem.menus,menuTypeList = childItem.isThirdWeb;
                    if(childMenuList.length>0){
                        var childMenuListHtml = '<dl class="layui-nav-child-list">';
                        layui.each(childMenuList,function(index,childListItem){
                            var thisListUrl;
                            if(childListItem.url){
                                thisListUrl = childListItem.url;
                            }
                            childMenuListHtml += '<dd><a href="javascript:;" id="" data-parent="1" lay-href="'+thisListUrl+'"><cite>'+childListItem.menuname+'</cite></a></dd>';
                        });
                        childMenuListHtml += '</dl>';
                    }
                    if(childItem.url){
                        thisUrl = childItem.url;
                    }
                    childMenuHtml += '<dd><a href="javascript:;" id="'+NameT+'" data-parent="0" lay-tips="'+childItem.menuname+'" data="'+index+'" '+(hasChildList ? '': 'lay-href="'+childItem.url+'"')+' data-type="'+childItem.isThirdWeb+'"><cite>'+childItem.menuname+'</cite>'+(hasChildList ? '<span class="layui-nav-more"></span>' : '')+'</a>'+(hasChildList ? childMenuListHtml : '')+'</dd>';
                });
                childMenuHtml +='</dl>';
            }
            str += '<li class="layui-nav-item">' +
                '<a href="javascript:;" id="'+NameO+'" data-type="'+menuType+'" data-parent="0" lay-tips="'+item.menuname+'" data="'+index+'" '+(hasChild ? '': 'lay-href="'+item.url+'"')+'><i class="iconfont '+item.icon+'"></i><cite>'+item.menuname+'</cite>'+(hasChild ? '<span class="layui-nav-more"></span>' : '')+'</a>' +
                (hasChild ? childMenuHtml : '')+
                '</li>';
        });
        str += '</ul>';
        return str;
    }
    /*element.on('nav(topnav)',function(elem){
        console.log(elem);
        if($(elem).attr('href') != ''){

        }else{
            var h;
            if($(elem).attr('lay-href')){
                h = $(elem).attr('lay-href');
                $('#lay-iframe').attr('src',h);
            }
            layui.sessionData('nav',{
                'key':'obj',
                'value':{
                    'h':h,
                    't':0
                }
            });
        }
    });*/
    var lefTips;
    $('#lay_page_main').on('mouseenter','*[lay-tips]',function(){
        var thisTip = $(this).attr('lay-tips');
        var eDom = $(this);
        if($('#lay_page_main').hasClass('layadmin-side-shrink')){
            lefTips = layer.tips(thisTip, eDom,{
                tips: [2, '#000000'],
                time: 0
            });
            eDom.data('index',lefTips);
        }
    }).on('mouseleave','*[lay-tips]',function(){
        layer.close($(this).data('index'));
    });
    element.on('nav(topleft)',function(elem){
        var layEvent = $(elem).attr('lay-event');
        if(layEvent == 'refresh'){
            var iframi = document.getElementById('lay-iframe');
            console.log(iframi.src);
            if(iframi.src.indexOf('device')<0){
                iframi.contentWindow.location.reload(!0);
            }
        }else if(layEvent == 'flexible'){
            if($('#LAY_app_flexible').hasClass('layui-icon-shrink-right')){
                $('#lay_page_main').addClass('layadmin-side-shrink');
                $('#LAY_app_flexible').removeClass('layui-icon-shrink-right').addClass('layui-icon-spread-left');
            }else{
                $('#lay_page_main').removeClass('layadmin-side-shrink');
                $('#LAY_app_flexible').removeClass('layui-icon-spread-left').addClass('layui-icon-shrink-right');
            }
        }
    });
    element.on('nav(leftnav)', function(elem){
        var thisType = $(elem).attr('data-type');
        var parentY = $(elem).attr('data-parent');
        var thisName = $(elem).find('cite').text();
        if(thisType == '1545457373'){
            if(nav.obj){
                $('.layui-nav-tree a[lay-href="'+nav.obj.h+'"]').parent().addClass('layui-this');
            }
            var thisHref = $(elem).attr('lay-href');
            $(elem).parent().removeClass('layui-this');
            window.open(origin+'/oauth2/authorize?response_type=code&client_id='+thisType+'&redirect_uri='+thisHref);
        }else{
            $('#lay_page_main').removeClass('layadmin-side-shrink');
            $(elem).parent().siblings().removeClass('layui-nav-itemed');
            var h,t,mapH,nameTextC,nameTextP;
            if($(elem).attr('lay-href')){
                h = $(elem).attr('lay-href');
                $('#lay-iframe').attr('src',h);
                if($(elem).parent('li').length>0 && $(elem).siblings('dl').length==0){
                    t=1;
                    $(elem).parents('li').addClass('layui-this');
                    nameTextP = $(elem).find('cite').text();
                    mapH = '<a href="#">'+nameTextP+'</a>';
                }else{
                    t=0;
                    $(elem).parents('li').addClass('layui-nav-itemed');
                    $(elem).parent('dd').addClass('layui-this');
                    nameTextP = $(elem).parents('li').find('cite').text();
                    nameTextC = $(elem).text();
                    mapH = '<a href="#">'+nameTextP+'</a><a><cite>'+nameTextC+'</cite></a>';
                }
                layui.sessionData('nav',{
                    'key':'obj',
                    'value':{
                        'h':h,
                        't':t,
                        'map':mapH,
                        'menu':'base'
                    }
                });
                $('.layui-breadcrumb').html('');
                $(mapH).appendTo('.layui-breadcrumb');
                element.render('breadcrumb');
            }
            else{
                if($(elem).parent().prop("tagName") == "LI"){
                    if($(elem).parent().hasClass("layui-nav-itemed")){
                        $(elem).parent().addClass('layui-nav-itemed');
                        $(elem).parent().siblings("li").removeClass('layui-nav-itemed');
                    }
                    else{
                        $(elem).parent().removeClass('layui-nav-itemed');
                    }
                }
                else if($(elem).parent().prop("tagName")=="DD"){
                    $(elem).parent().removeClass("layui-this");
                    if(!$(elem).parent().hasClass("layui-nav-itemed")){
                        $(elem).parent().addClass('layui-nav-itemed');
                        $(elem).parent().siblings("dd").removeClass('layui-nav-itemed');
                    }
                    else{
                        $(elem).parent().removeClass('layui-nav-itemed');
                    }
                }
            }
        }
    });
});