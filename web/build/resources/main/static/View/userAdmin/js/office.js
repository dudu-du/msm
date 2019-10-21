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
    form.render();
    table.render({
        elem: '#test'
        ,url:'/fe/node_modules/test-json/table.json'
        ,toolbar: '#toolbarDemo'
        ,title: '用户数据表'
        ,skin:'line'
        ,id:'table'
        ,defaultToolbar:['filter']
        ,cols: [[
            {type: 'checkbox'}
            ,{field:'classify', title:'姓名',  unresize: true, sort: true}
            ,{field:'username', title:'用户名',  sort: true}
            ,{field:'wealth', title:'手机号'}
            ,{field:'email', title:'邮箱', templet: function(res){
                return '<em>'+ res.email +'</em>'
            }}
            ,{field:'sign', title:'所属机构'}
			// ,{field:'id', title:'机构编码'}
            ,{title:'操作', toolbar: '#barDemo', width:190,align:"center"}
        ]]
        ,page: true
    });

    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addOffice':
                addMaskOffice('add')
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
        // console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            addMaskOffice('edit','')
        }else if(obj.event === 'disable'){
            layer.confirm('确定要禁用吗', function(index){
                obj.del();
                layer.close(index);
            });
        }
    });
	var addOfficeHtml = '<div class="lay-mask-box">' +
	        '<div class="layui-form-item layui-form-hide">'+
	        '<label class="layui-form-label">所属机构</label>'+
	        '<div class="layui-input-block">'+
	        '<select name="interest" lay-filter="aihao" lay-verify="school" lay-search="">'+
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
	        '<label class="layui-form-label">用户名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="username" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">姓名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input">'+
	        '</div>'+
	        '</div>'+
			'<div class="layui-form-item">'+
			'<label class="layui-form-label">机构编码</label>'+
			'<div class="layui-input-block">'+
			'<input type="text" name="offCode" lay-verify="offCode" autocomplete="off" placeholder="请输入机构编码" class="layui-input">'+
			'</div>'+
			'</div>'+
			'<div class="layui-form-item">'+
			'<label class="layui-form-label">密码</label>'+
			'<div class="layui-input-block">'+
			'<input type="password" name="password" lay-verify="password" autocomplete="off" placeholder="不填写则为默认密码" class="layui-input">'+
			'</div>'+
			'</div>'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">手机号</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="phone" lay-verify="phone" autocomplete="off" placeholder="请输入手机号" class="layui-input">'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">邮箱</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="email" lay-verify="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input">'+
	        '</div>'+
	        '</div>'+
	        '</div>';
	    function addMaskOffice(tag,data){
	        var title;
	        if(tag == 'add'){
	            title = '添加局管理员'
	        }else{
	            title = '编辑局管理员'
	        }
	        layer.open({
	            type:1,
	            area:'484px',
	            title:title,
	            content:addOfficeHtml,
	            btn:['确定','取消'],
	            success:function(layero, index){
	                $('.lay-mask-box .layui-form-hide').show();
	                layero.addClass('layui-form').attr('lay-filter','fromInput');
	                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
	                form.render();
	                form.verify({
	                    school:function(value){
	                        if(value == ''){
	                            return '请选择所属学校'
	                        }
	                    },
	                    username:function(value){
	                        if(value == ''){
	                            return '用户名不能为空'
	                        }
	                    }
	                });
	                if(tag == 'edit'){
	                    datas = {"school":"复兴小学","name":"复兴小学","code":"123456","sex":"男","identity":"130916546532154513213","email":"122@qq.com"};
	                    form.val('fromInput', {
	                        "username":data.school,
	                        "name": datas.name ,
	                        "phone": datas.code,
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