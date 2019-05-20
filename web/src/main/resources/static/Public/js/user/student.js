/**
 * Created by Administrator on 2018/11/13.
 */
var imageData = '';
var tempOrgCode="",tempSectionCode="",tempGradeCode="",selcTag="other";
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
    var url = origin + '/View/listStudentInfo',studentcol;
    if(org_id != '0'){
        orgId = org_id;
        $('.choseSch').addClass('layui-hide');
        studentcol = [[
            {type: 'checkbox'}
            ,{field:'realname', title:'姓名',unresize: true, sort: true}
            ,{field:'loginName', title:'用户名',sort: true}
            ,{field:'sex', title:'性别'}
            ,{field:'gradeName', title:'年级'}
            ,{field:'className', title:'班级', sort: true}
            ,{field:'sectionName', title:'学段'}
            ,{field:'cardNo', title:'卡号',
                templet: function(res){
                    if(res.cardNo == ''){
                        return '--'
                    }else{
                        return res.cardNo
                    }
                }
            }
            ,{field:'startYear', title:'入学年份'}
            ,{title:'操作', toolbar: '#barDemo',width:180}
        ]]
    }else{
        studentcol = [[
            {type: 'checkbox'}
            ,{field:'realname', title:'姓名',unresize: true, sort: true}
            ,{field:'loginName', title:'用户名',sort: true}
            ,{field:'sex', title:'性别'}
            ,{field:'gradeName', title:'年级'}
            ,{field:'className', title:'班级', sort: true}
            ,{field:'sectionName', title:'学段'}
            ,{field:'orgName', title:'所属学校'}
            ,{field:'startYear', title:'入学年份'}
            ,{title:'操作', toolbar: '#barDemo',width:180}
        ]]
    }
    var getData = {orgId: org_id};
    tableLoad(url,getData);
    function tableLoad(url, data){
        var dataPost;
        // if(data.orgId == '0'){
            dataPost = ''
        // }else{
            dataPost = data;
        // }
        table.render({
            elem: '#test'
            ,url:url
            ,request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,method:'GET'
            , where: dataPost
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,skin:'line'
            ,id:'table'
            ,defaultToolbar:['']
            ,response: {
                statusCode: '200'
            }
            ,done:function(res){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
            }
            ,parseData: function(res){
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            },
            done:function(){
                $('.table-btns-box').show();
                $('.table-btns-num').hide();
                upload.render({
                    elem: '#uploadExcel'
                    ,url: origin + '/View/uploadStudent'
                    ,accept: 'file' //普通文件
                    ,done: function(res){
                        console.log(res);
                        if(res.status == 200){
                            var datas = res.data;
                            var msg = "";
                            if(datas.anomalousCauseList.length==0){
                                layer.msg('学生导入成功');
                                tableLoad(url,getData);
                            }else{
                                msg = msg +"导入["+datas.count+"]条,成功["+datas.succeedCount+"]条！";
                                if(datas.anomalousCauseList.length>0){
                                    $.each(datas.anomalousCauseList,function (i,item){
                                        msg = msg+item.cause+"["+item.detail+"]"
                                    })
                                }
                                layer.open({
                                    type: 1,
                                    content: msg, //这里content是一个普通的String
                                    btn: ['确定'],
                                    yes:function () {
                                        layer.closeAll();
                                        tableLoad(url,getData);
                                    }
                                });
                            }
                        }else{
                            layer.msg(res.msg);
                        }
                    }
                });
            }
            ,text: {
                none: '返回无数据'
            }
            ,cols: studentcol,
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
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addTeacher':
                addMaskStudent('add');
                break;
            case 'download':
                var data = checkStatus.data;
                if(org_id == 0){
                    layer.open({
                        type:1,
                        area:'484px',
                        title:'下载学生模板',
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
                                            $.each(orgDatas,function (index,val){
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
                            form.on('submit(fromContent)', function (data){
                                var objData = data.field;
                                getSchoolClass(objData.orgId);
                                if(schoolClassNum == 0){
                                    layer.msg('请先在班级管理中添加班级！');
                                }else{
                                    window.open(origin + '/View/exportStudent?schoolId='+objData.orgId);
                                    layer.closeAll();
                                }
                            })
                        }
                    });
                }else{
                    getSchoolClass(org_id);
                    if(schoolClassNum == 0){
                        layer.msg('请先在班级管理中添加班级！');
                    }else{
                        window.open(origin + '/View/exportStudent?schoolId='+org_id);
                    }
                }
                break;
            case 'import':
                layer.msg('导入');
                break;
            case 'export':
                layer.open({
                    type:1,
                    area:'484px',
                    title:'导出学生',
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
                                success: function (data) {
                                    if (data.success) {
                                        var orgDatas = data.data;
                                        if(data.status == 200 && orgDatas.length>0){
                                            $('select[name="orgId"] option').remove();
                                            $.each(orgDatas,function (index,val){
                                                $("#orgId").append("<option value="+val.id+">"+val.name+"</option>");
                                            })
                                        }
                                        form.render();//这里需要重新渲染一次，不然下拉框没有效果
                                    } else {
                                        console.log(data.msg)
                                    }
                                }
                            });
                        }
                        form.render();
                    },
                    yes:function(){
                        form.on('submit(fromContent)', function (data){
                            var objData = data.field;
                            window.open(origin + '/View/exportStudent?schoolId='+objData.orgId+'&hasHistory=1');
                            layer.closeAll();
                        })
                    }
                });
                break;
            case 'del':
                var getData = {orgId:org_id};
                var checkStatus = table.checkStatus('table')
                    ,data = checkStatus.data;
                var checkedData = data.map(function(index,elem){
                    return data[elem].id;
                }).join(',');
                layer.confirm('确认要删除该数据吗？', function(index){
                    console.log("removeByIdStr:"+checkedData);
                    $.ajax({
                        url: origin + "/View/student?id="+checkedData,
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
                var getData = {orgId:org_id,realName:searchVal};
                searchText = searchVal;
                tableLoad(url,getData);
                break;
            case 'screenON':
                selcTag = 'screen';
                if(org_id == 0){
                    getAllSchool();
                }else{
                    getSection(org_id);
                }
                $('.welsee-table').removeClass('layui-col-md12').addClass('layui-col-md9');
                $('.welsee-screen').show();
                $(this).attr('lay-event','screenOFF');
                table.resize('table');
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
            $('.table-cho-num').html('<i class="iconfont icon-tanhao"></i>已选择'+data.length+'名人员');
        }else{
            $('.table-btns-box').show();
            $('.table-btns-num').hide();
        }
    });
    //加载所有学校
    function getAllSchool(){
        if(selcTag=='add'){
            $('.layui-layer select[name="orgId"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('select[name="orgId"]');
        }else{
            $('.welsee-screen select[name="orgId"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('select[name="orgId"]');
        }
        $.ajax({
            url: origin + '/View/allTypeOrgList?orgType=OT_SCHOOL',
            type: 'GET',
            async:false,
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var orgDatas = data.data;
                    if(data.status == 200 && orgDatas.length>0){
                        var dataArr = data.data;
                        $.each(dataArr,function (index,val) {
                            options = "<option value="+val.id+">"+val.name+"</option>";
                            if(selcTag=='add'){
                                $(options).appendTo('.layui-layer select[name="orgId"]');
                            }else{
                                $(options).appendTo('.welsee-screen select[name="orgId"]');
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
    //获取学段
    function getSection(orgId) {
        $.ajaxSetup({
            async:false
        });
        if(selcTag == 'add'){
            $('.layui-layer select[name="section"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.layui-layer select[name="section"]');
        }else{
            $('.welsee-screen select[name="section"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.welsee-screen select[name="section"]');
        }
        $.get(origin + '/View/section', {orgId: orgId}, function (data) {
            if (data.success) {
                var dataArr = data.data;
                $.each(dataArr,function (index,val) {
                    options = '<option value="' + val.code + '">' + val.name + '</option>';
                    if(selcTag == 'add'){
                        $(options).appendTo('.layui-layer select[name="section"]');
                    }else{
                        $(options).appendTo('.welsee-screen select[name="section"]');
                    }
                });
                form.render();
            } else {
                layer.msg(data.msg)
            }
        }, 'json')
    }
    var gardeArr;

    /**
     * 获取年级
     * @param codeId
     */
    function getGrade(codeId) {
        $.ajaxSetup({
            async:false
        });
        if(selcTag == 'add'){
            $('.layui-layer select[name="grade"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.layui-layer select[name="grade"]');
        }else{
            $('.welsee-screen select[name="grade"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.welsee-screen select[name="grade"]');
        }
        $.get(origin + '/View/grade', {sectionCode: codeId}, function (data) {
            console.log(data);
            if (data.success) {
                var dataArr = data.data;
                for (var i = 0; i < dataArr.length; i++) {
                    options = '<option value="' + dataArr[i].code + '">' + dataArr[i].name + '</option>';
                    if(selcTag == 'add'){
                        $(options).appendTo('.layui-layer select[name="grade"]');
                    }else{
                        $(options).appendTo('.welsee-screen select[name="grade"]');
                    }
                }
                form.render();
            } else {
                layer.msg(data.msg)
            }
        }, 'json')
    }
    /**
     * 获取班级
     * @param orgId
     */
    function getClass(orgId,sectionCode,gradeCode) {
        $.ajaxSetup({
            async:false
        });
        if(selcTag == 'add'){
            $('.layui-layer select[name="class"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.layui-layer select[name="class"]');
        }else{
            $('.welsee-screen select[name="class"] option').remove();
            var options = '<option value=""></option>';
            $(options).appendTo('.welsee-screen select[name="class"]');
        }
        $.get(origin + '/View/listClassLikeNoPage', {orgId: orgId,sectionCode:sectionCode,gradeCode:gradeCode},
            function (data) {
            if (data.success) {
                var classArr = data.data;
                for(var i = 0; i < classArr.length; i++){
                    options = '<option value="' + classArr[i].classNumber + '">' + classArr[i].className + '</option>';
                    if(selcTag == 'add'){
                        $(options).appendTo('.layui-layer select[name="class"]');
                    }else{
                        $(options).appendTo('.welsee-screen select[name="class"]');
                    }
                }
                form.render();
            }else{
                layer.msg(data.msg);
            }
        }, 'json')
    }
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认要删除该数据吗？', function(index){
                $.ajax({
                    url: origin + "/View/student?id="+data.id,
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
        } else if(obj.event === 'edit'){
            addMaskStudent('edit',data)
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
    //机构下拉监听
    form.on('select(orgId)',function(obj){
        console.log("筛选"+obj);
        tempOrgCode = obj.value;
        getSection(obj.value);
    });
    //学段下拉监听
    form.on('select(section)',function(obj){
        console.log("选择学段"+obj);
        tempSectionCode=obj.value;
        getGrade(obj.value);
    });
    //年级下拉监听
    form.on('select(grade)',function(obj){
        console.log("选择班级"+obj);
        tempGradeCode=obj.value;
        if(selcTag == 'add'){
            tempSectionCode = $('.layui-layer select[name="section"]').val();
        }
        if(tempOrgCode == ''){
            getClass(org_id,tempSectionCode,tempGradeCode);
        }else{
            getClass(tempOrgCode,tempSectionCode,tempGradeCode);
        }
    });
    //筛选
    form.on('submit(screen)',function(){
        var selOrgid = $('.welsee-screen select[name="orgId"]').val();
        var classNumber = $('#classNumber').val();
        var sex = $('#student_sex').val();
        var getData = {orgId: selOrgid,classNumber:classNumber,sex:sex};
        tableLoad(url,getData);

    });
    //重置
    form.on('submit(reload)',function(){
        $('select,input').val("");
        form.render();
        var getData = {orgId: org_id};
        tableLoad(url,getData);
    });
    //取消
    form.on('submit(cancel)',function(){
        $('.welsee-table').removeClass('layui-col-md9').addClass('layui-col-md12');
        $('.welsee-screen').hide();
        $('.layui-inline[lay-event="screenOFF"]').attr('lay-event','screenON');
        table.resize('table');
    })
    if(org_id != '0'){
        var stuHide = 'layui-hide';
    }
    var addStudentHtml = '<div class="lay-mask-box">' +
        '<input type="hidden" name="id">'+
        '<input type="hidden" name="loginId">'+
        '<input type="hidden" name="pic" id="person_pic">'+
        '<div class="layui-form-item layui-form-hide '+stuHide+'">'+
        '<label class="layui-form-label">所属学校</label>'+
        '<div class="layui-input-block">'+
        '<select id="orgId" name="orgId" lay-filter="orgId" lay-verify="school" lay-search="">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">学段</label>'+
        '<div class="layui-input-block">'+
        '<select name="section" lay-filter="section" lay-verify="section">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">姓名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="realname" lay-verify="name" autocomplete="off" placeholder="请输入用户名" class="layui-input">'+
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
        '<label class="layui-form-label">用户名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="loginName" lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">年级</label>'+
        '<div class="layui-input-block">'+
        '<select name="grade" lay-filter="grade" lay-verify="grade">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">班级</label>'+
        '<div class="layui-input-block">'+
        '<select name="class" lay-filter="class" lay-verify="class">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">卡号</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="cardNo" lay-verify="numbers" autocomplete="off" placeholder="请输入学生卡号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskStudent(tag,datas){
        selcTag = "add";
        console.log(datas);
        var title;
        if(tag == 'add'){
            title = '添加学生'
        }else{
            title = '编辑学生'
        }
        layer.open({
            type:1,
            area:'484px',
            title:title,
            content:addStudentHtml,
            btn:['确定','取消'],
            success:function(layero, index){
                $('.lay-mask-box .layui-form-hide').show();
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
                if(org_id == '0'){
                    form.on('select(orgId)', function (obj) {
                        console.log("选择机构"+obj);
                        tempOrgCode = obj.value;
                        getSection(obj.value);
                    });
                    form.verify({
                        school:function(value){
                            if(value == ''){
                                return '请选择所属学校'
                            }
                        }
                    });
                }else{
                    getSection(org_id);
                }
                form.verify({
                    section:function(value){
                        if(value == ''){
                            return '请选择学段'
                        }
                    },
                    grade:function(value){
                        if(value == ''){
                            return '请选择年级'
                        }
                    },
                    class:function(value){
                        if(value == ''){
                            return '请选择班级'
                        }
                    },
                    username:function(value){
                        if(value == ''){
                            return '用户名不能为空'
                        }
                    },
                    name:function(value){
                        if(value == ''){
                            return '姓名不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    getAllSchool();
                    $.ajaxSetup({
                        async:false
                    });
                    $.get(origin + '/View/classInfo', {ids: datas.classId}, function (data) {
                        if (data.success) {
                            getSection(datas.orgId);
                            getGrade(datas.sectionCode,datas.gradeCode);
                            getClass(datas.orgId,datas.sectionCode,datas.gradeCode);
                            console.log(data);
                            form.render();
                        } else {
                            layer.msg(data.msg)
                        }
                    }, 'json');

                    if(datas.pic){
                        $('#demo1').attr('src', pictureOssUrl + "/" + datas.pic);
                    }
                    form.val('fromInput', {
                        "id":datas.id,
                        "loginId":datas.loginId,
                        "orgId":datas.orgId,
                        "section":datas.sectionCode,
                        "grade":datas.gradeCode,
                        "class":datas.classNumber,
                        "loginName": datas.loginName,
                        "classNumber": datas.classNumber,
                        "realname": datas.realname,
                        "sex":datas.sex,
                        "cardNo":datas.cardNo
                    })
                }else{
                    //获取当前机构相关信息
                    getAllSchool();
                }
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    var objData = data.field,schoolID;
                    if(org_id == '0'){
                        schoolID = objData.orgId
                    }else{
                        schoolID = org_id;
                    }
                    if(tag == 'edit'){
                        $.ajax({
                            url: origin + '/View/student?id='+objData.id+'&loginId='+objData.loginId,
                            type: 'PUT',
                            data: {
                                loginName: objData.loginName,
                                orgId: schoolID,
                                classNumber: objData.class,
                                realname: objData.realname,
                                password:objData.password,
                                tel:objData.tel,
                                email:objData.email,
                                identityNo:objData.identityNo,
                                sex:objData.sex,
                                pic:imageData,
                                cardNo:objData.cardNo
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
                        $.post(origin + '/View/student',
                            {	loginId: objData.loginId,
                                orgId: schoolID,
                                classNumber: objData.class,
                                loginName: objData.loginName,
                                realname: objData.realname,
                                password:objData.password,
                                tel:objData.tel,
                                email:objData.email,
                                identityNo:objData.identityNo,
                                sex:objData.sex,
                                pic:imageData,
                                cardNo:objData.cardNo
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