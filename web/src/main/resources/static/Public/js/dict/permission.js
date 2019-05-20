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
    var getData = {parentCode: "WE_PERMISSION",name:""};
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
            ,title: '权限数据表'
            ,skin:'line'
            ,id:'table'
            ,defaultToolbar:['']
            , response: {
                statusCode: '200'
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
            ,text: {
                none: '暂无相关数据'
            }
            , cols: [
                [
                    {
                        type: 'checkbox'
                    },
                    {
                        field: 'name', title: '权限名称'
                    },
                    {
                        field: 'code', title: '权限编号'
                    },
                    {
                        title: '操作', toolbar: '#barDemo', width: 120
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
                layer.confirm('确认要删除该数据吗？', function(index){
                    $.ajax({
                        url: origin + "/View/dict?ids=" + checkedData+"&codes="+checkedCode,
                        type: "DELETE",
                        dataType: "json",
                        success: function (res) {
                            if (res.success) {
                                layer.msg("删除成功")
                                var url = origin + '/View/getDictListPage';
                                var getData = {parentCode: "WE_PERMISSION",name:""};
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
                var getData = {parentCode: "WE_PERMISSION",name:searchVal};
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
            layer.confirm('确认要删除该数据吗？', function(index){
                $.ajax({
                    url: origin + "/View/dict?ids=" + data.id+"&codes="+data.code,
                    type: "DELETE",
                    dataType: "json",
                    success: function (res) {
                        if (res.success) {
                            layer.msg("删除成功")
                            var url = origin + '/View/getDictListPage';
                            var getData = {parentCode: "WE_PERMISSION",name:""};
                            table.reload('table');
                        } else {
                            layer.msg(res.msg)
                        }
                    }

                })
            });
        } else if(obj.event === 'edit'){
            addMaskTeacher('edit',data)
        }
    });
    var addTeacherHtml = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">权限名称</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="permissionname" lay-verify="permissionname" autocomplete="off" placeholder="请输入权限名称" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">权限编号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="permissioncode" lay-verify="permissioncode" autocomplete="off" placeholder="请输入权限编号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskTeacher(tag,datas){
        var title;
        var id;
        if(tag == 'add'){
            title = '新增权限';
            id = "-1";
        }else{
            title = '编辑权限';
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
                    permissionname:function(value){
                        if(value == ''){
                            return '权限名称不能为空'
                        }
                    },
                    permissioncode:function(value){
                        if(value == ''){
                            return '权限编码不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    form.val('fromInput', {
                        "permissionname":datas.name,
                        "permissioncode": datas.code ,
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
                                code:data.field.permissioncode,
                                name:data.field.permissionname,
                                parentCode:"WE_PERMISSION"
                                },
                        dataType: "json",
                        success: function (res) {
                            if (res.success) {
                                layer.closeAll();
                                var url = origin + '/View/getDictListPage';
                                var getData = {parentCode: "WE_PERMISSION",name:""};
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
});