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
    tableLoad();
    function tableLoad(){
        table.render({
            elem: '#test'
            ,url:'/fe/node_modules/test-json/table.json'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,skin:'line'
            ,id:'table'
            ,defaultToolbar:['']
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'classify', title:'姓名',unresize: true, sort: true}
                ,{field:'username', title:'年级',sort: true}
                ,{field:'sex', title:'班级'}
                ,{field:'wealth', title:'班级编码'}
                ,{field:'email', title:'学段'}
                ,{title:'操作', toolbar: '#barDemo',width:210,align:"center"}
            ]]
            ,page: true
        });
    }
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addTeacher':
                addMaskMaster('add');
                break;
            case 'del':
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                console.log(checkedData);
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
                break;
            case 'search':
                var searchVal = $('.table-search-box input').val();
                layer.msg('搜索'+searchVal);
                break;
            case 'screenON':
                $('.safety-table').removeClass('layui-col-md12').addClass('layui-col-md9');
                $('.safety-screen').show();
                $(this).attr('lay-event','screenOFF');
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
            $('.table-cho-num').html('<i class="layui-icon layui-icon-tips"></i>已选择'+data.length+'名人员');
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
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            addMaskMaster('edit',data)
        }
    });
    //筛选
    form.on('submit(screen)',function(){
        layer.msg('筛选')
    });
    //重置
    form.on('submit(cancel)',function(){
        tableLoad();
    });
    //取消
    form.on('submit(cancel)',function(){
        $('.safety-table').removeClass('layui-col-md9').addClass('layui-col-md12');
        $('.safety-screen').hide();
        $('.layui-inline[lay-event="screenOFF"]').attr('lay-event','screenON');
    })

    var addMasterHtml = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">姓名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">年级</label>'+
        '<div class="layui-input-block">'+
        '<select name="grade" lay-filter="grade" lay-verify="grade" lay-search="">'+
        '<option value=""></option>'+
        '<option value="0">幼儿园</option>'+
        '<option value="1">小学</option>'+
        '<option value="2">初中</option>'+
        '<option value="3">高中</option>'+
        '<option value="4">职业中学</option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">班级</label>'+
        '<div class="layui-input-block">'+
        '<select name="classA" lay-filter="classA" lay-verify="classA" lay-search="">'+
        '<option value=""></option>'+
        '<option value="0">幼儿园</option>'+
        '<option value="1">小学</option>'+
        '<option value="2">初中</option>'+
        '<option value="3">高中</option>'+
        '<option value="4">职业中学</option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">班级编号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="classcode" lay-verify="classcode" autocomplete="off" placeholder="请输入班级编号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">学段</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="section" lay-verify="section" autocomplete="off" placeholder="请输入学段" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskMaster(tag,datas){
        var title;
        if(tag == 'add'){
            title = '添加班主任'
        }else{
            title = '编辑班主任'
        }
        layer.open({
            type:1,
            area:'484px',
            title:title,
            content:addMasterHtml,
            btn:['确定','取消'],
            success:function(layero, index){
                $('.lay-mask-box .layui-form-hide').show();
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
                form.verify({
                    grade:function(value){
                        if(value == ''){
                            return '请选择年级'
                        }
                    },
                    classA:function(value){
                        if(value == ''){
                            return '请选择班级'
                        }
                    },
                    name:function(value){
                        if(value == ''){
                            return '姓名不能为空'
                        }
                    },
                    classcode:function(value){
                        if(value == ''){
                            return '班级编号不能为空'
                        }
                    },
                    section:function(value){
                        if(value == ''){
                            return '学段不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    form.val('fromInput', {
                        "interest":datas.sign,
                        "username": datas.username ,
                        "phone": datas.wealth,
                        "sex": datas.sex,
                        "email":datas.email,
                        "identity":datas.identity
                    })
                }
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {

                });
            }
        })
    }
});