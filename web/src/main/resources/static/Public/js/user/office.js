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
    var url = origin + '/View/listOfficeAdminInfo';
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
                ,{field:'orgName', title:'所属机构'}
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
    //获取所有局机构
    function getOrg(editOrgId){
        $.ajax({
            url: origin + '/View/allTypeOrgList?orgType=OT_BUREAU',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var orgDatas = data.data;
                    if(data.status == 200 && orgDatas.length>0){
                        $.each(orgDatas,function (index,val){
                            if(editOrgId == val.id){
                                $("#orgId").append("<option value="+val.id+" selected='selected'>"+val.name+"</option>");
                            }else{
                                $("#orgId").append("<option value="+val.id+">"+val.name+"</option>");
                            }
                        })
                    }
                    form.render();//这里需要重新渲染一次，不然下拉框没有效果
                } else {
                    console.log(data.msg)
                }
            }
        });
    }
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addOffice':
                addMaskOffice('add');
                break;
            case 'del':
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                layer.confirm('确认要删除该数据吗？',function(index){
                    $.ajax({
                        url: origin + "/View/officeAdmin?id="+checkedData,
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
                    url: origin + "/View/officeAdmin?id="+data.id,
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
            addMaskOffice('edit',data);
        }else if(obj.event === 'password'){
            layer.confirm('真的重置么', function(index){
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
	var addOfficeHtml = '<div class="lay-mask-box">' +
            '<input type="hidden" name="id">'+
            '<input type="hidden" name="loginId">'+
	        '<div class="layui-form-item layui-form-hide">'+
	        '<label class="layui-form-label">所属机构</label>'+
	        '<div class="layui-input-block">'+
	        '<select id="orgId" name="orgId" lay-filter="aihao" lay-verify="school" lay-search="">'+
	        '<option value=""></option>'+
	        '</select><span class="must">*</span>'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item">'+
	        '<label class="layui-form-label">用户名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="loginName" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input"><span class="must">*</span>'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item layui-hide">'+
	        '<label class="layui-form-label">姓名</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="realname" lay-verify="name" autocomplete="off" placeholder="请输入姓名" class="layui-input">'+
	        '</div>'+
	        '</div>'+
			'<div class="layui-form-item" id="password-div">'+
			'<label class="layui-form-label">密码</label>'+
			'<div class="layui-input-block">'+
			'<input type="new-password" name="password" lay-verify="password" autocomplete="off" placeholder="不填写则为默认密码" class="layui-input">'+
			'</div>'+
			'</div>'+
	        '<div class="layui-form-item layui-hide">'+
	        '<label class="layui-form-label">手机号</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="tel" autocomplete="off" placeholder="请输入手机号" class="layui-input">'+
	        '</div>'+
	        '</div>'+
	        '<div class="layui-form-item layui-hide">'+
	        '<label class="layui-form-label">邮箱</label>'+
	        '<div class="layui-input-block">'+
	        '<input type="text" name="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input">'+
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
                    var uploadInst = upload.render({  //上传图片
                        elem: '#upIcon',
                        url: origin + '/View/picture?folderSecond=person/',
                        field: 'uploadPicture',
                        before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#demo1').attr('src', result); //图片链接（base64）
                            });
                        },
                        choose: function (obj) {
                            $('.layui-btn[lay-filter="fromContent"]').attr('disabled', true).text('上传中...')
                        },
                        done: function (res) {
                            $('.layui-btn[lay-filter="fromContent"]').attr('disabled', false).text('提交');
                            //如果上传失败
                            if (res.success) {
                                imageData=res.data;
                                return layer.msg('上传成功');
                            } else {
                                return layer.msg('上传失败');
                            }
                            //上传成功
                        },
                        error: function () {
                            //演示失败状态，并实现重传
                            var demoText = $('#demoText');
                            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                            demoText.find('.demo-reload').on('click', function () {
                                uploadInst.upload();
                            });
                        }
                    });
	                form.verify({
	                    school:function(value){
	                        if(value == ''){
	                            return '请选择所属机构'
	                        }
	                    },
	                    username:function(value){
	                        if(value == ''){
	                            return '用户名不能为空'
	                        }
	                    },
                        /*phone:function(value){
                            if(value == ''){
                                return '手机号码不能为空'
                            }else{
                                if(!/^1\d{10}$/.test(value)){
                                    return '请输入正确的手机号'
                                }
                            }
                        },
                        email:function(value){
                            if(value == ''){
                                return '邮箱不能为空'
                            }else{
                                if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value)){
                                    return '邮箱格式不正确'
                                }
                            }
                        }*/
	                });
	                if(tag == 'edit'){
                        if(data.pic){
                            $('#demo1').attr('src', pictureOssUrl + "/" + data.pic);
                            imageData = data.pic;
                        }
                        $('#password-div').hide();
                        getOrg(data.orgId);
                        console.log(data);
	                    form.val('fromInput', {
	                        "id":data.id,
                            "loginId":data.loginId,
	                        "orgId":data.orgId,
	                        "loginName":data.loginName,
	                        "realname":data.realname,
	                        "tel": data.tel,
	                        "email":data.email,
                            "pic":data.pic
	                    })
	                }else{
                        $('#password-div').show();
                        getOrg();
                    }
	            },
	            yes:function(){
	                form.on('submit(fromContent)', function (data) {
                        var objData = data.field;
                        if(tag == 'edit'){
                            $.ajax({
                                url: origin + '/View/officeAdmin',
                                type: 'PUT',
                                data: {
                                    id: objData.id,
                                    loginId:objData.loginId,
                                    loginName: objData.loginName,
                                    orgId: objData.orgId,
                                    realname: '局管理员',
                                    tel:objData.tel,
                                    email:objData.email,
                                    pic:imageData
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
                            $.post(origin + '/View/officeAdmin',
                                {
                                    loginName: objData.loginName,
                                    orgId: objData.orgId,
                                    realname: '局管理员',
                                    password:objData.password,
                                    tel:objData.tel,
                                    email:objData.email,
                                    pic:imageData
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