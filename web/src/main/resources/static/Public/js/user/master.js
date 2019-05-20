/**
 * Created by Administrator on 2018/11/13.
 */
var tempSectionCode="",tempGradeCode="";
layui.use(['element','table','layer','form'], function(){
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    var device = layui.device();
    if(device.id){
        confirm('请不要使用ie浏览器查看');
    }
    var url = origin + '/View/listMasterInfo';
    var getData = {orgId: org_id};
    tableLoad(url,getData);
    function tableLoad(url, data){
        table.render({
            elem: '#test'
            ,url:url
            ,request: {
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            }
            ,method:'GET'
            ,where: data
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
            ,parseData: function (res) {
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            }
            ,cols: [[
                {type: 'checkbox'}
                ,{field:'realname', title:'姓名',unresize: true, sort: true}
                ,{field:'sectionName', title:'学段'}
                ,{field:'gradeName', title:'年级'}
                ,{field:'className', title:'班级'}
                ,{title:'操作', toolbar: '#barDemo',width:65}
            ]]
            ,text: {
                none: '暂无相关数据'
            },
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

    //获取教师列表
    function getTeacher(orgId){
        $.ajax({
            url: origin + '/View/teacher?orgId='+org_id,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    var orgDatas = data.data;
                    $('select[name="teacher"] option').remove();
                    var options = '<option value=""></option>';
                    $(options).appendTo('select[name="teacher"]');
                    if(data.status == 200 && orgDatas){
                        for (var i = 0; i < orgDatas.length; i++) {
                            options = "<option value="+orgDatas[i].id+">"+orgDatas[i].realname+"（"+orgDatas[i].loginName+"）</option>";
                            $(options).appendTo('select[name="teacher"]');
                        }
                    }
                    form.render();//这里需要重新渲染一次，不然下拉框没有效果
                } else {
                    console.log(data.msg)
                }
            }
        });
    }
    function getSection(orgId){
        console.log(orgId);
        $.get(origin + '/View/section', {orgId: orgId}, function (data) {
            if (data.success) {
                $('select[name="section"] option').remove();
                var sectionDatas = data.data;
                if(data.status == 200 && sectionDatas){
                    var options = '<option value=""></option>';
                    $(options).appendTo('select[name="section"]');
                    for (var i = 0; i < sectionDatas.length; i++) {
                        options = '<option value="' + sectionDatas[i].code + '">' + sectionDatas[i].name + '</option>';
                        $(options).appendTo('select[name="section"]');
                    }
                }
                form.render();
            }else{
                layer.msg(data.msg)
            }
        }, 'json')
    }
    form.on('select(section)',function(obj){
        tempSectionCode=obj.value;
        getGrade(obj.value);
    });
    form.on('select(grade)', function (obj) {
        console.log("选择班级"+obj);
        tempGradeCode=obj.value;
        getClass(org_id,tempSectionCode,tempGradeCode);
    });
    var gardeArr;
    /**
     * 获取年级
     * @param codeId
     */
    function getGrade(codeId) {
        $.get(origin + '/View/grade', {sectionCode: codeId}, function (data) {
            console.log(data);
            if (data.success) {
                $('select[name="grade"] option').remove();
                var options = '<option value=""></option>';
                $(options).appendTo('select[name="grade"]');
                gardeArr = data.data;
                for (var i = 0; i < gardeArr.length; i++) {
                    options = '<option value="' + gardeArr[i].code + '">' + gardeArr[i].name + '</option>';
                    $(options).appendTo('select[name="grade"]');
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
        $.get(origin + '/View/listClassLikeNoPage', {orgId: orgId,sectionCode:sectionCode,gradeCode:gradeCode},
            function (data) {
                if (data.success) {
                    $('select[name="class"] option').remove();
                    var options = '<option value=""></option>';
                    $(options).appendTo('select[name="class"]');
                    var classArr = data.data;
                    for (var i = 0; i < classArr.length; i++) {
                        options = '<option value="' + classArr[i].id + '">' + classArr[i].className + '</option>';
                        $(options).appendTo('select[name="class"]');
                    }
                    form.render();
                } else {
                    layer.msg(data.msg)
                }
            }, 'json')
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
                    return data[elem].classId;
                }).join(',');
                layer.confirm('确认要删除该数据吗？', function(index){
                    console.log("removeByIdStr:"+checkedData);
                    $.ajax({
                        url: origin + "/View/master?id="+checkedData,
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
                $('.safety-table').removeClass('layui-col-md12').addClass('layui-col-md9');
                $('.safety-screen').show();
                $(this).attr('lay-event','screenOFF');
                getSection(org_id);
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
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('确认要删除该数据吗？', function(index){
                $.ajax({
                    url: origin + "/View/master?id="+data.classId,
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
            addMaskMaster('edit',data)
        }
    });
    //筛选
    form.on('submit(screen)',function(data){
        var objData = data.field;
        var getData = {orgId: org_id,classId:objData.class,sectionCode:objData.section,gradeCode:objData.grade};
        tableLoad(url,getData);
    });
    //重置
    form.on('submit(reload)',function(){
        tableLoad(url,getData);
    });
    //取消
    form.on('submit(cancel)',function(){
        $('.safety-table').removeClass('layui-col-md9').addClass('layui-col-md12');
        $('.safety-screen').hide();
        $('.layui-inline[lay-event="screenOFF"]').attr('lay-event','screenON');
        table.resize('table');
    })

    var addMasterHtml = '<div class="lay-mask-box">' +
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">教师</label>'+
        '<div class="layui-input-block">'+
        '<select id ="teacher" name="teacher" lay-filter="teacher" lay-verify="teacher" lay-search="">'+
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
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">年级</label>'+
        '<div class="layui-input-block">'+
        '<select name="grade" lay-filter="grade" lay-verify="grade" lay-search="">'+
        '<option value=""></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">班级</label>'+
        '<div class="layui-input-block">'+
        '<select name="class" lay-filter="class" lay-verify="class" lay-search="">'+
        '<option value=""></option>'+
        '</select>'+
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
                getTeacher(org_id);
                getSection(org_id);
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    var objData = data.field;
                    $.ajax({
                        url: origin + '/View/master?id='+objData.teacher+'&classId='+objData.class,
                        type: 'PUT',
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
                });
            }
        })
    }
});