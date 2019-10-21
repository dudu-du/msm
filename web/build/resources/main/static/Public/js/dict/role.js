/**
 * Created by Administrator on 2018/11/13.
 */
layui.use(['element','table','layer','form'], function(){
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    var device = layui.device();
    if(device.id){
        confirm('请不要使用ie浏览器查看');
    }
    var url = origin + '/View/getDictListPage';
    var getData = {parentCode: "WE_ROLE",name:""};
    tableLoad(url,getData);
    function tableLoad(url,data){
        table.render({
            elem: '#test'
            ,url:url
            , request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            , where: data
            ,toolbar: '#toolbarDemo'
            ,title: '角色数据表'
            ,skin:'line'
            ,id:'table'
            ,defaultToolbar:['']
            , response: {
                statusCode: '200'
            }
            ,text: {
                none: '暂无相关数据'
            }
            ,done:function(res){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
            }
            , parseData: function (res) {
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            }
            , cols: [
                [
                    {
                        type: 'checkbox'
                    },
                    {
                        field: 'name', title: '角色名称'
                    },
                    {
                        field: 'code', title: '角色编号'
                    },
                    {
                        title: '操作', toolbar: '#barDemo', width: 210
                    }
                ]],
            page: {
                limit: 30,
                limits: [30, 50, 100, 200]
            }
        });
    }
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addTeacher':
                addMaskTeacher('add');
                break;
            case 'del':
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                var checkedCode = data.map(function(index,elem){
                    return data[elem].code;
                }).join(',');
                console.log(checkedData);
                layer.confirm('真的删除行么', function(index){
                    $.ajax({
                        url: origin + "/View/dict?ids=" + checkedData+"&codes="+checkedCode,
                        type: "DELETE",
                        dataType: "json",
                        success: function (res) {
                            if (res.success) {
                                layer.msg("删除成功")
                                var url = origin + '/View/getDictListPage';
                                var getData = {parentCode: "WE_ROLE",name:""};
                                table.reload('table');
                            } else {
                                layer.msg(res.msg)
                            }
                        }
                    })
                });
                break;
            case 'search':
                var searchVal = $('.table-search-box input').val();
                var url = origin + '/View/getDictListPage';
                var getData = {parentCode: "WE_ROLE",name:searchVal};
                tableLoad(url,getData);
                break;
        };
    });
    //选中状态
    table.on('checkbox(test)', function(obj){
        var checkStatus = table.checkStatus('table')
            ,data = checkStatus.data;
        if(data.length>0){
            $('.table-btns-box').hide();
            $('.table-btns-num').show();
            $('.table-cho-num').html('<i class="iconfont icon-tanhao"></i>已选择'+data.length+'个角色');
        }else{
            $('.table-btns-box').show();
            $('.table-btns-num').hide();
        }
    });
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                $.ajax({
                    url: origin + "/View/dict?ids=" + data.id+"&codes="+data.code,
                    type: "DELETE",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            layer.msg("删除成功")
                            var url = origin + '/View/getDictListPage';
                            var getData = {parentCode: "WE_ROLE",name:""};
                            table.reload('table');
                        } else {
                            layer.msg(res.msg);
                        }
                    }

                })
            });
        } else if(obj.event === 'edit'){
            addMaskTeacher('edit',data)
        }else if(obj.event === 'password'){
            setPowerTeacher(data)
        }
    });
    var addTeacherHtml = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">角色名称</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="rolename" lay-verify="rolename" autocomplete="off" placeholder="请输入角色名称" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">角色编号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="rolecode" lay-verify="rolecode" autocomplete="off" placeholder="请输入角色编号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskTeacher(tag,datas){
        var title;
        var id;
        if(tag == 'add'){
            title = '新增角色';
            id = "-1";
        }else{
            title = '编辑角色';
            id = datas.id;
        }
        layer.open({
            type:1,
            area:'484px',
            title:title,
            content:addTeacherHtml,
            btn:['确定','取消'],
            success:function(layero, index){
                $('.lay-mask-box .layui-form-hide').show();
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
                form.verify({
                    rolename:function(value){
                        if(value == ''){
                            return '角色名称不能为空'
                        }
                    },
                    rolecode:function(value){
                        if(value == ''){
                            return '角色编码不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    form.val('fromInput', {
                        "rolename":datas.name,
                        "rolecode": datas.code ,
                    })
                }
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    $.ajax({
                        url: origin + "/View/updateSaveDict",
                        type: "POST",
                        data: {
                                id: id,
                                sort:0,
                                code:data.field.rolecode,
                                name:data.field.rolename,
                                parentCode:"WE_ROLE"
                                },
                        dataType: "json",
                        success: function (res) {
                            if (res.success) {
                                layer.closeAll();
                                var url = origin + '/View/getDictListPage';
                                var getData = {parentCode: "WE_ROLE",name:""};
                                table.reload('table');
                            } else {
                                layer.msg(res.msg);
                            }

                        }


                    })
                });
            }
        })
    }
    var setPowerHtml = '<div class="lay-mask-box layui-mask-check">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">角色名称</label>'+
        '<div class="layui-input-block layui-input-noLine">'+
        '<input type="text" name="rolename" lay-verify="rolename" autocomplete="off"  class="layui-input safety-input-line" disabled>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<div class="allCheck">' +
        '<input type="checkbox" name="check[all]" title="全选" lay-filter="allChecked">'+
        '</div>'+
        '<div class="layui-input-block mask-check-block">'+
        '</div>'+
        '</div>'+
        '</div>';
    function setPowerTeacher(datas){
        layer.open({
            type:1,
            area:['780px','488px'],
            title:'配置权限',
            skin:'mask-checkeds',
            content:setPowerHtml,
            btn:['确定','取消'],
            success:function(layero, index){

                $('.lay-mask-box .layui-form-hide').show();
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                $.ajax({
                    url: origin + "/View/getAllPermissionList",
                    type: "GET",
                    data: {
                        roleId:datas.id
                    },
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            console.log(res);
                            var apendhtml='';
                            var permissiondata = res.data;
                            for(var i=0;i<permissiondata.length;i++){
                                var name = permissiondata[i].name;
                                var type = permissiondata[i].type==1?"checked":"";
                                var id = permissiondata[i].id;
                                apendhtml += '<input type="checkbox" name="like"' +type+ ' lay-filter="aa" value="'+id+'" title="'+name+'">';
                            }
                            console.log(apendhtml);
                            $(".mask-check-block").append(apendhtml);
                            form.render();
                        } else {
                            layer.msg(res.msg)
                        }
                    }
                })

                form.verify({
                    rolename:function(value){
                        if(value == ''){
                            return '角色名称不能为空'
                        }
                    }
                });
                form.val('fromInput', {
                    "rolename":datas.name
                })
                form.on('checkbox(aa)', function(data){
                    console.log(data);
                });
                form.on('checkbox(allChecked)',function(data){
                   if(data.elem.checked){
                       $('.mask-check-block input').prop('checked',true);
                       form.render();
                   }else{
                       $('.mask-check-block input').prop('checked',false);
                       form.render();
                   }
                });
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    var checkedVal = $('.mask-check-block input:checked').map(function(index,elem){
                        return $(elem).val();
                    }).get().join(',');
                    console.log(data,checkedVal);
                    $.ajax({
                        url: origin + "/View/addPermissionByRole",
                        type: "POST",
                        data: {
                            roleId:datas.id,
                            permissions:checkedVal
                        },
                        dataType: "json",
                        success: function (res) {
                            layer.msg(res.msg);
                            if (res.success) {
                                layer.closeAll();
                            }else{
                                layer.msg(res.msg)
                            }
                        }
                    })
                });
            }
        })
    }
});