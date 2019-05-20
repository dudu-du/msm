/**
 * Created by Administrator on 2018/11/13.
 */
var imageData = '';
layui.use(['element','table','layer','form', 'upload'], function(){
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        upload = layui.upload,
        device = layui.device();
    if(device.id){
        confirm('请不要使用ie浏览器查看');
    }
    var schoolcol,tableWidthS = '';
    if(org_id == '0'){
        $('.schoolSelects').removeClass('layui-hide');
        schoolcol = [[
            {type: 'checkbox'}
            ,{field:'loginName', title:'用户名',sort: true}
            ,{field:'realname', title:'姓名',unresize: true, sort: true}
            ,{field:'sex', title:'性别'}
            ,{field:'tel', title:'手机号'}
            ,{field:'email', title:'邮箱'}
            ,{field:'orgName', title:'所属学校'}
            ,{field:'identityNo', title:'身份证号码'}
            ,{title:'操作', toolbar: '#barDemo',width:180}
        ]]
    }else{
        schoolcol = [[
            {type: 'checkbox'}
            ,{field:'loginName', title:'用户名',sort: true}
            ,{field:'realname', title:'姓名',unresize: true, sort: true}
            ,{field:'sex', title:'性别'}
            ,{field:'tel', title:'手机号'}
            ,{field:'email', title:'邮箱'}
            ,{title:'操作', toolbar: '#barDemo',width:180}
        ]]
    }
    getSearchSchool(form,'OT_SCHOOL');
    tableLoad();
    function tableLoad(data){
        var url = origin + '/View/listTeacherInfo';
        if(data){
            console.log("data==="+data);
            url = url +"?realName="+data+"&orgId="+org_id;
        }else{
            url = origin + '/View/listTeacherInfo?orgId='+org_id;
        }
        table.render({
            elem: '#test'
            ,url:url
            ,request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,method:'GET'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,skin:'line'
            ,id:'table'
            ,defaultToolbar:['']
            ,response: {
                statusCode: '200'
            }
            ,parseData: function(res){
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            }
            ,cols: schoolcol,
            done: function(res, page, count){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
                upload.render({
                    elem: '#uploadExcel'
                    ,url: origin + '/View/uploadTeacher'
                    ,accept: 'file' //普通文件
                    ,done: function(res){
                        console.log(res);
                        if(res.status == 200){
                            var datas = res.data;
                            var msg = "";
                            tableLoad();
                            if(datas.anomalousCauseList.length==0){
                                layer.msg('教师导入成功');
                            }else{
                                msg = msg +"导入["+datas.count+"]条,成功["+datas.succeedCount+"]条！";
                                if(datas.anomalousCauseList.length>0){
                                    $.each(datas.anomalousCauseList,function (i,item) {
                                        msg = msg+item.cause+"["+item.detail+"] "
                                    })
                                }
                                layer.open({
                                    type: 1,
                                    content: msg, //这里content是一个普通的String
                                    btn: ['确定'],
                                    yes:function () {
                                        layer.closeAll();
                                        tableLoad();
                                    }
                                });
                            }
                        }else{
                            layer.msg(res.msg);
                        }
                    }
                });
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
            case 'addTeacher':
                addMaskTeacher('add');
                break;
            case 'download':
                if(org_id == 0){
                    layer.open({
                        type:1,
                        area:'484px',
                        title:'下载教师模板',
                        content:downLoadTemplate,
                        btn:['确定','取消'],
                        success:function(layero, index){
                            $('.lay-mask-box .layui-form-hide').show();
                            layero.addClass('layui-form').attr('lay-filter','fromInput');
                            layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                            $.ajax({
                                url: origin + '/View/allTypeOrgList?orgType=OT_SCHOOL',
                                type: 'GET',
                                dataType: 'json',
                                success: function (data) {
                                    if (data.success) {
                                        var orgDatas = data.data;
                                        if(data.status == 200 && orgDatas.length>0){
                                            $('select[name="orgId"] option').remove();
                                            $.each(orgDatas,function (index,val) {
                                                $("#orgId").append("<option value="+val.id+">"+val.name+"</option>");
                                            })
                                        }
                                        form.render();//这里需要重新渲染一次，不然下拉框没有效果
                                    } else {
                                        console.log(data.msg)
                                    }
                                }
                            });
                            form.render();
                        },
                        yes:function(){
                            form.on('submit(fromContent)', function (data) {
                                var objData = data.field;
                                window.open(origin + '/View/teacherTemplate?orgId='+objData.orgId);
                                layer.closeAll();
                            })
                        }
                    });
                }else{
                    window.open(origin + '/View/teacherTemplate?orgId='+org_id);
                }
                break;
            case 'import':
                layer.msg( '导入');
                break;
            case 'export':
                layer.open({
                    type:1,
                    area:'484px',
                    title:'导出教师',
                    content:downLoadTemplate,
                    btn:['确定','取消'],
                    success:function(layero, index){
                        $('.lay-mask-box .layui-form-hide').show();
                        layero.addClass('layui-form').attr('lay-filter','fromInput');
                        layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                        if(org_id && org_id!='0'){
                            $.ajax({
                                url: origin + '/View/org?orgId='+org_id,
                                type: 'GET',
                                dataType: 'json',
                                success: function (data) {
                                    if (data.success) {
                                        var orgDatas = data.data;
                                        $('select[name="orgId"] option').remove();
                                        if(data.status == 200 && orgDatas && orgDatas.orgType == 'OT_SCHOOL'){
                                            $("#orgId").append("<option value="+orgDatas.id+" selected='selected'>"+orgDatas.name+"</option>");
                                        }
                                        form.render();//这里需要重新渲染一次，不然下拉框没有效果
                                    } else {
                                        console.log(data.msg)
                                    }
                                }
                            });
                        }else{
                            $.ajax({
                                url: origin + '/View/allTypeOrgList?orgType=OT_SCHOOL',
                                type: 'GET',
                                dataType: 'json',
                                success: function (data){
                                    if(data.success){
                                        var orgDatas = data.data;
                                        if(data.status == 200 && orgDatas.length>0){
                                            $('select[name="orgId"] option').remove();
                                            $.each(orgDatas,function (index,val){
                                                $("#orgId").append("<option value="+val.id+">"+val.name+"</option>");
                                            })
                                        }
                                        form.render();//这里需要重新渲染一次，不然下拉框没有效果
                                    }else{
                                        console.log(data.msg)
                                    }
                                }
                            });
                        }
                        form.render();
                    },
                    yes:function () {
                        form.on('submit(fromContent)', function (data) {
                            var objData = data.field;
                            window.open(origin + '/View/exportExcel?orgId='+objData.orgId);
                            layer.closeAll();
                        })

                    }
                });
                break;
            case 'del':
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                layer.confirm('确认要删除该数据吗？', function(index){
                    console.log("removeByIdStr:"+checkedData);
                    $.ajax({
                        url: origin + "/View/teacher?id="+checkedData,
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
                break;
            case 'search':
                var searchVal = $('.table-search-box input').val();
                searchText = searchVal;
                tableLoad(searchVal);
                // layer.msg('搜索'+searchVal);
                break;
            case 'screenON':
                $('.welsee-table').removeClass('layui-col-md12').addClass('layui-col-md9');
                $('.welsee-screen').show();
                tableWidthS = $('.layui-body-main').width()-20;
                // console.log(tableWidthS);
                table.resize('table');
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

    //加载所有学校
    function getAllSchool(org_id,editOrgId){
        $.ajax({
            url: origin + '/View/allTypeOrgList?orgType=OT_SCHOOL',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var orgDatas = data.data;
                    if(data.status == 200 && orgDatas.length>0){
                        $('select[name="orgId"] option[name="thisonly"]').remove();
                        $.each(orgDatas,function (index,val) {
                            console.log(val.id+":"+val.name);
                            if(editOrgId == val.id){
                                $("#orgId").append("<option value="+val.id+" selected='selected' name='thisonly'>"+val.name+"</option>");
                            }else{
                                $("#orgId").append("<option value="+val.id+" name='thisonly'>"+val.name+"</option>");
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
    //加载当前机构的学校
    function getCurrentSchool(org_id,editOrgId) {
        $.ajax({
            url: origin + '/View/org?orgId='+org_id,
            type: 'GET',
            dataType: 'json',
            success: function(data){
                if(data.success){
                    var orgDatas = data.data;
                    $('select[name="orgId"] option').remove();
                    if(data.status == 200 && orgDatas && orgDatas.orgType == 'OT_SCHOOL' && orgDatas.id == editOrgId){
                        $("#orgId").append("<option value="+orgDatas.id+" selected='selected'>"+orgDatas.name+"</option>");
                    }
                    form.render();//这里需要重新渲染一次，不然下拉框没有效果
                } else {
                    console.log(data.msg)
                }
            }
        });
    }
    function getOrg(org_id,editOrgId){
        if(org_id && org_id!='0'){
            getCurrentSchool(org_id,editOrgId);
        }else{
            getAllSchool(org_id,editOrgId);
        }
    }
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认要删除该数据吗？', function(index){
                $.ajax({
                    url: origin + "/View/teacher?id="+data.id,
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
            addMaskTeacher('edit',data)
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
    //筛选
    form.on('submit(screen)',function(data){
        console.log(data);
        if(org_id == 0){
            orgName = data.field.selectSearch;
        }
        var sex = data.field.sex;
        var search_url =  origin + '/View/listTeacherInfo?orgName='+orgName+'&sex='+sex;
        table.render({
            elem: '#test'
            ,url:search_url
            ,request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,method:'GET'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,skin:'line'
            ,id:'table'
            // ,width:tableWidthS
            ,defaultToolbar:['']
            ,response: {
                statusCode: '200'
            }
            ,text: {
                none: '暂无相关数据'
            }
            ,done:function(res){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
            }
            ,parseData: function (res) {
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            }
            ,cols: schoolcol,
            done: function(res, page, count){
            },
            // ,page: true
            page: {
                limit: 30,
                limits: [30, 50, 100, 200]
            }
        });

    });
    //重置
    form.on('submit(reload)',function(){
        $('select,input').val("");
        form.render();
        tableLoad();
    });
    //取消
    form.on('submit(cancel)',function(){
        $('.welsee-table').removeClass('layui-col-md9').addClass('layui-col-md12');
        $('.welsee-screen').hide();
        $('.layui-inline[lay-event="screenOFF"]').attr('lay-event','screenON');
        table.resize('table');
    });
    if(org_id != '0'){
        var stuHide = 'layui-hide';
    }
    var addTeacherHtml = '<div class="lay-mask-box">' +
        '<input type="hidden" name="id">'+
        '<input type="hidden" name="loginId">'+
        '<input type="hidden" name="pic" id="person_pic">'+
        '<div class="layui-form-item layui-form-hide '+stuHide+'">'+
        '<label class="layui-form-label">所属学校</label>'+
        '<div class="layui-input-block">'+
        '<select id ="orgId" name="orgId" lay-filter="orgId_name" lay-verify="school" lay-search="">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">用户名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="loginName" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input"><span class="must">*</span>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">密码</label>'+
        '<div class="layui-input-block">'+
        '<input type="new-password" name="password" lay-verify="password" autocomplete="new-password" placeholder="不填写则为默认密码" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">姓名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="realname" autocomplete="off" placeholder="请输入姓名" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">性别</label>'+
        '<div class="layui-input-block">'+
        '<input type="radio" name="sex" lay-filter="type" value="男" title="男" checked="">'+
        '<input type="radio" name="sex" lay-filter="type" value="女" title="女">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">手机号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="tel" autocomplete="off" placeholder="请输入手机号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">邮箱</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">身份证号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="identityNo" autocomplete="off" placeholder="请输入身份证号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    var downLoadTemplate = '<div class="lay-mask-box">' +
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">所属学校</label>'+
        '<div class="layui-input-block">'+
        '<select id ="orgId" name="orgId" lay-filter="orgId_name" lay-verify="school" lay-search="">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskTeacher(tag,datas){
        var title;
        if(tag == 'add'){
            title = '添加教师'
        }else{
            title = '编辑教师'
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
                $.ajax({
                    url: origin + '/View/allTypeOrgList?orgType=OT_SCHOOL',
                    type: 'GET',
                    async:false,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            var orgDatas = data.data;
                            if(data.status == 200 && orgDatas.length>0){
                                $('select[name="orgId"] option[name="thisonly"]').remove();
                                $.each(orgDatas,function (index,val) {
                                    $("#orgId").append("<option value="+val.id+" name='thisonly'>"+val.name+"</option>");
                                })
                            }
                            form.render();//这里需要重新渲染一次，不然下拉框没有效果
                        } else {
                            console.log(data.msg)
                        }
                    }
                });
                if(org_id == '0'){
                    form.verify({
                        school:function(value){
                            if(value == ''){
                                return '请选择所属学校'
                            }
                        }
                    });
                }
                form.verify({
                    username:function(value){
                        if(value == ''){
                            return '用户名不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    getOrg(org_id,datas.orgId);
                    if(datas.pic){
                        $('#demo1').attr('src', pictureOssUrl + "/" + datas.pic);
                        imageData = datas.pic;
                    }
                    form.val('fromInput', {
                        "id":datas.id,
                        "orgId":datas.orgId,
                        "interest":datas.sign,
                        "loginId":datas.loginId,
                        "loginName":datas.loginName,
                        "realname": datas.realname ,
                        "password":datas.password,
                        "tel": datas.tel,
                        "sex": datas.sex,
                        "email":datas.email,
                        "identityNo":datas.identityNo,
                        "pic":datas.pic
                    })
                }else{
                    //获取当前机构相关信息
                    getOrg(org_id);
                }
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    var objData = data.field,schoolID;
                    console.log("objData.pic="+objData.pic);
                    if(org_id == '0'){
                        schoolID = objData.orgId
                    }else{
                        schoolID = org_id;
                    }
                    if(tag == 'edit'){
                        $.ajax({
                            url: origin + '/View/teacher?id='+objData.id+'&loginId='+objData.loginId,
                            type: 'PUT',
                            data: {
                                loginName: objData.loginName,
                                orgId: schoolID,
                                realname: objData.realname,
                                password:objData.password,
                                tel:objData.tel,
                                email:objData.email,
                                identityNo:objData.identityNo,
                                sex:objData.sex,
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
                        $.post(origin + '/View/teacher',
                            {	loginName: objData.loginName,
                                orgId: schoolID,
                                realname: objData.realname,
                                password:objData.password,
                                tel:objData.tel,
                                email:objData.email,
                                identityNo:objData.identityNo,
                                sex:objData.sex,
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