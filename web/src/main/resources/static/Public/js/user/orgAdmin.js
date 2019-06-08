/**
 * Created by Administrator on 2018/11/13.
 */
var imageData = '';
layui.use(['element','table','layer','form', 'upload'], function(){
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    var upload = layui.upload;
    var device = layui.device();
    if(device.id){
        confirm('请不要使用ie浏览器查看');
    }
    var url = origin + '/View/listOrgAdminInfo';
    var getData = {};
    tableLoad(url,getData);
    //表格加载
    function tableLoad(url, data){
        table.render({
            elem: '#test'
            ,url: url
            ,request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,where : data
            ,method:'GET'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,skin:'line'
            ,response: {
                statusCode: '200'
            }
            ,parseData: function (res) {
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            }
            ,done:function(res){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
            }
            ,id:'table'
            ,defaultToolbar:['']
            ,cols: [[
                {type: 'checkbox'}
                // ,{field:'orgName', title:'所属机构'}
                ,{field:'loginName', title:'用户名',  sort: true}
                ,{field:'realname', title:'姓名',  unresize: true, sort: true}
                // ,{field:'tel', title:'手机号'}
                // ,{field:'email', title:'邮箱', templet: function(res){
                //         return res.email
                //     }}
                ,{title:'操作', toolbar: '#barDemo', width:230}
            ]],
            text: {
                none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            },
            // ,page: true
            page: {
                limit: 30,
                limits: [30, 50, 100, 200]
            }
        });
        //点击回车搜索
        $('.table-search-box input').keydown(function(e){
            if (event.keyCode == "13") {//keyCode=13是回车键
                $('i[lay-event="search"]').trigger('click');
            }
        });
    }
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addOrg':
                addOrgAdmin('add');
                break;
            case 'del':
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                layer.confirm('确认要删除该数据吗？',function(index){
                    $.ajax({
                        url: origin + "/View/orgAdmin?id="+checkedData,
                        type: "DELETE",
                        success: function(data){
                            if(data.success){
                                layer.close(index);
                                table.reload('table');
                            }else{
                                layer.close(index);
                                table.reload('table');
                                layer.msg(data.msg);
                            }
                        }
                    })
                });
                break;
            case 'search':
                var searchVal = $('.table-search-box input').val();
                var getData = {orgName: searchVal};
                searchText = searchVal;
                tableLoad(url,getData);
                // layer.msg('搜索'+searchVal);
                break;
        };
    });
    //选中状态
    table.on('checkbox(test)',function(obj){
        var checkStatus = table.checkStatus('table')
            ,data = checkStatus.data;
        if(data.length>0){
            $('.table-btns-box').hide();
            $('.table-btns-num').show();
            $('.table-cho-num').html('<i class="iconfont icon-tanhao"></i>已选择'+data.length+'名人员');
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
            layer.confirm('确认要删除该数据吗？', function(index){
                $.ajax({
                    url: origin + "/View/orgAdmin?id="+data.id,
                    type: "DELETE",
                    success: function (data) {
                        if(data.success){
                            console.log(data);
                            obj.del();
                            layer.close(index);
                            table.reload('table');
                        }else{
                            console.log(data);
                            obj.del();
                            layer.close(index);
                            table.reload('table');
                            layer.msg(data.msg)
                        }

                    }
                })
            });
        } else if(obj.event === 'edit'){
            addOrgAdmin('edit',data);
        }else if(obj.event === 'password'){
            layer.confirm('确定重置密码吗？', function(index){
                $.ajax({
                    url: origin + "/password?id="+data.loginId,
                    type: "PUT",
                    success: function (data) {
                        if(data.success){
                            layer.close(index);
                            table.reload('table');
                        }else{
                            layer.close(index);
                            table.reload('table');
                            layer.msg(data.msg);
                        }
                    }
                })
            });
        }else if(obj.event === 'disable'){
            layer.confirm('确定要禁用吗', function(index){
                $.ajax({
                    url: origin + "/login?id="+data.loginId,
                    type: "DELETE",
                    success: function (data) {
                        if(data.success){
                            layer.close(index);
                            table.reload('table');
                        }else{
                            layer.close(index);
                            table.reload('table');
                            layer.msg(data.msg);
                        }
                    }
                })
            });
        }else if(obj.event === 'enable'){
            layer.confirm('确定要启用吗', function(index){
                $.ajax({
                    url: origin + "/login/enable?id="+data.loginId,
                    type: "PUT",
                    success: function (data) {
                        if(data.success){
                            layer.close(index);
                            table.reload('table');
                        }else{
                            layer.close(index);
                            table.reload('table');
                            layer.msg(data.msg);
                        }
                    }
                })
            });
        }
    });
    
	var addOrgAdminHtml = '<div class="lay-mask-box">' +
            '<input type="hidden" name="id">'+
            '<input type="hidden" name="loginId">'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">用户名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="loginName" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input"><span class="must">*</span>'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">姓名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="realname" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input"><span class="must">*</span>'+
	        '</div>'+
	        '</div>'+
	        '</div>';
	var sele = [];
    
    
    $.ajax({
        url: "/View/allOrgList?parentId=0",
        type: "GET",
        success: function (data) {
            if(data.success){
            	sele.push('<div class="layui-form-item">');
                sele.push('<label class="layui-form-label">机构</label>');
                sele.push('<div class="layui-input-block">');
                sele.push('<select name="orgId" lay-verify="required">');
            	data.data.forEach(e=>{
            		sele.push('<option value="'+e.id+'">'+e.name+'</option>');
            	});
                    
              sele.push('</select>');
              sele.push('</div>');
              sele.push('</div>');
              
            }else{
                
            }
        }
    });
	    function addOrgAdmin(tag,data){
	        var title;
	        var html = '';
	        if(tag == 'add'){
	            title = '添加管理员';
	            html = '<div class="lay-mask-box">' +
	              '<input type="hidden" name="id">'+
	              '<input type="hidden" name="loginId">'+
	  	        '<div class="layui-form-item">'+
	  	        '<label class="layui-form-label">用户名</label>'+
	  	        '<div class="layui-input-block">'+
	  	        '<input type="text" name="loginName" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input"><span class="must">*</span>'+
	  	        '</div>'+
	  	        '</div>'+
	  	        '<div class="layui-form-item">'+
	  	        '<label class="layui-form-label">姓名</label>'+
	  	        '<div class="layui-input-block">'+
	  	        '<input type="text" name="realname" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input"><span class="must">*</span>'+
	  	        '</div>'+
	  	        '</div>'+
	  	        sele.join('')+
	  	        '</div>';
	        }else{
	            title = '编辑管理员';
	            html = addOrgAdminHtml;
	        }
	 
	        layer.open({
	            type:1,
	            area:'484px',
	            title:title,
	            content:html,
	            btn:['确定','取消'],
	            success:function(layero, index){
	                $('.lay-mask-box .layui-form-hide').show();
	                layero.addClass('layui-form').attr('lay-filter','fromInput');
	                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
	                form.render();

	                form.verify({
	                    name:function(value){
	                        if(value == ''){
	                            return '姓名不能为空'
	                        }
	                    },
	                    username:function(value){
	                        if(value == ''){
	                            return '用户名不能为空'
	                        }
	                    },
	                });
	                if(tag == 'edit'){
                        $('#password-div').hide();
	                    form.val('fromInput', {
	                        "id":data.id,
                            "loginId":data.loginId,
	                        "loginName":data.loginName,
	                        "realname":data.realname
	                    })
	                }else{
                        $('#password-div').show();
                    }
	            },
	            yes:function(){
	                form.on('submit(fromContent)', function (data) {
                        var objData = data.field;
                        
                        if(tag == 'edit'){
                            $.ajax({
                                url: origin + '/View/orgAdmin',
                                type: 'PUT',
                                data: {
                                    id: objData.id,
                                    loginId:objData.loginId,
                                    loginName: objData.loginName,
                                    realname: objData.realname
                                },
                                dataType: 'json',
                                success: function (data) {
                                    layer.closeAll();
                                    if (data.success) {
                                        table.reload('table');
                                    } else {
                                        layer.msg(data.msg)
                                    }
                                }
                            })
                        }else{

                            $.post(origin + '/View/orgAdmin',
                                {
                                    loginName: objData.loginName,
                                    orgId: objData.orgId,
                                    realname: objData.realname,
                                    password:objData.password
                                },
                                function (data) {
                                    if (data.success) {
                                        layer.closeAll();
                                        table.reload('table');
                                    } else {
                                        return layer.msg(data.msg);
                                    }
                                }, 'json');
                        }
	                });
	            }
	        })
	    }
	
});