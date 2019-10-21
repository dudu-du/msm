/**
 * Created by Administrator on 2018/11/9.
 */
layui.config({
    base: '/node_modules/layui-extends/'
}).extend({
    authtree: 'atree',
    othertree:'atreeother',
    formSelects: 'formSelects-v4'
});
var parentIdBM,treeDatas,getUrl,butType,pageTree = 'departuser',userBmAll,onloadType="index";
layui.use(['element','table','atree','layer','form','atreeother','formSelects','eleTree'], function(){
    var element = layui.element,
        authtree = layui.atree,
        othertree = layui.atreeother,
        formSelects = layui.formSelects,
        eletree = layui.eleTree,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
    // 树结构初始化
    loadTree();
    function loadTree(){
        $.ajax({
            url: '/View/allDepartmentList?parentId='+org_id,
            dataType: 'json',
            success: function(data){
                treeDatas = data.data;
                // 渲染时传入渲染目标ID，树形结构数据（具体结构看样例，checked表示默认选中），以及input表单的名字
                var tree = authtree({
                    elem: '#LAY-auth-tree-index', //指定元素
                    check: 'checkbox', //勾选风格
                    skin: 'as', //设定皮肤
                    spreadAll:false,
                    props:{
                        addBtnLabel:'<i class="iconfont icon-zengjia1" title="添加"></i>',
                        deleteBtnLabel:'<i class="iconfont icon-shanchu1" title="删除"></i>',
                        editBtnLabel:'<i class="iconfont icon-bianji"  title="编辑"></i>',
                        name: 'name',
                        id: 'id',
                        children:'children',
                        checkbox:'checkbox',
                        spread:'spread'
                    },
                    click: function(item,elem) { //点击节点回调
                        $('#LAY-auth-tree-index .layui-atree-node').attr('data','0');
                        $(elem).attr('data','1');
                        $('button[name="department"]').show();
                        parentIdBM = item.id;
                        butType = item.orgType;
                        getListOrg(butType);
                    },
                    addClick:function(item,elem,add){
                        addMaskJG('add',item,'ai',add);
                    },
                    editClick:function(item,elem,edit){
                        console.log(item);
                        addMaskJG('edit',item,'ai',edit);
                    },
                    deleteClick:function(item,elem,del){
                        delMaskJG(item,del);
                    },
                    nodes: treeDatas
                });
            }
        });
    }
    var layui_card = $('.layui-card');
    //机构add
    layui_card.on('click','.safety-card-btn',function(){
        var thisName = $(this).attr('name');
        if(thisName == 'mechanismsort'){
            treeSort();  //机构add
        }
    });
    //排序
    var mechanisortH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">拖动排序</label>'+
        '<div class="layui-input-block departree treeSort">' +
        '<input type="hidden" name="topmech" lay-verify="topmechs">'+
        '<div id="eletree-box" class="eleTree" lay-filter="treeData"></div>'+
        '</div>'+
        '</div>'+
        '</div>';
    function treeSort(){
        layer.open({
            type:1,
            area:'514px',
            title:'排序',
            content:mechanisortH,
            btn:['确定','取消'],
            success:function(layero, index){
                getTreeSortData();
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    console.log(data,data.field);
                });
            }
        })
    }
    function getTreeSortData(){
        eletree.render({
            elem: '#eletree-box',
            url: origin+'/View/allDepartmentList?parentId='+org_id,
            draggable: true,
            response: {   // 对于后台数据重新定义名字
                statusName: "status",
                statusCode: 200,
                dataName: "data"
            },
            request: {     // 对后台返回的数据格式重新定义
                name: "name",
                key: "id",
                children: "children"
            },
            done: function() {
                // el2.remove(24);
            }
        });
        eletree.on("nodeDrag(treeData)",function(d) {
            console.log(d);
            // d.stop();           // 取消拖拽
            console.log(d.current);    // 起始节点对应的dom和数据
            console.log(d.target);   // 鼠标落点对应的dom和数据
            console.log(this);      // 鼠标落点对应的dom
        })
    }
    //table初始化
    function getListOrg(types){
        var datacols;
        if(types == 'OT_SCHOOL'){
            getUrl = origin + '/View/userWithDepartment?orgId='+parentIdBM;
            datacols = [[
                {type: 'checkbox'},
                {field:'realname', title:'姓名', sort: true},
                {field:'loginName', title:'用户名', sort: true},
                {field:'tel', title:'手机号', sort: true},
                {field:'sex', title:'性别', sort: true},
                {field:'email', title:'邮箱', sort: true},
                {field:'departmentName', title:'部门', sort: true},
                { title:'操作', toolbar: '#barDemo', width:150}
            ]]
        }else{
            getUrl = origin+'/View/departmentUser?departmentId='+parentIdBM;
            datacols = [[
                {type: 'checkbox'},
                {field:'realname', title:'姓名', sort: true},
                {field:'loginName', title:'用户名', sort: true},
                {field:'tel', title:'手机号', sort: true},
                {field:'sex', title:'性别', sort: true},
                {field:'email', title:'邮箱', sort: true},
                {field:'departmentName', title:'部门', sort: true},
                { title:'操作', toolbar: '#barDemo', width:150}
            ]]
        }
        table.render({
            elem: '#test',
            url:getUrl,
            parseData: function(res){ //res 即为原始返回的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.totalItems, //解析数据长度
                    "data": res.data.items //解析数据列表
                };
            },
            request:{
                pageName: 'page', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusCode: 200 //规定成功的状态码，默认：0
            },
            done:function(res){
                console.log(res);
                userBmAll = res.data.map(function(item,index){
                    return item.id
                }).join(',');
            },
            title: '下级部门',
            toolbar: '#toolbarDemo',
            defaultToolbar:[''],
            id:'table',
            skin:'line',
            cols: datacols,
            page: {
                limit: 10,
                limits: [10, 20, 30, 40,50,60,70,80,90,100]
            }
        });
    }
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            delUser(data.id,data.departmentId);
        } else if(obj.event === 'edit'){
            addToDeparts('edit',data);
        }
    });
    //监听头工具事件
    table.on('toolbar(test)',function(obj){
        var checkStatus = table.checkStatus('table')
        ,data = checkStatus.data;
       switch (obj.event) {
           case 'adddepar':
               layer.confirm('此操作会解除所有部门和人员的绑定关系，请谨慎操作。',{title:'重置部门'}, function(index){
                   layer.close(index);
                   resetBM();
               });
               break;
           case 'adduser':
               addUserToDepart();
               break;
           case 'addToDepart':
               addToDeparts('add',data);
               break;
           case 'removeUser':
               var removeUserId = data.map(function(item,index){
                   return item.id;
               }).join(',');
               var removedeId = data.map(function(item,index){
                   return item.departmentId;
               }).join(',');
               delUser(removeUserId,removedeId);
               break;
       }
    });
    //选中状态
    table.on('checkbox(test)', function(obj){
        console.log(obj.data.orgName)
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
    //重置部门
    function resetBM(){
        $.ajax({
            url: '/View/orgDepartmentUser?orgId='+org_id,
            type:'DELETE',
            data:{},
            dataType: 'json',
            success: function(data){
                if(data.success){
                    getListOrg(butType);
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    //移除人员
    function delUser(userid,deid){
        layer.confirm('确认移除吗？', function(index){
            $.ajax({
                url: '/View/departmentUser?userId='+userid+'&departmentId='+deid,
                type:'DELETE',
                data:{},
                dataType: 'json',
                success: function(data){
                    if(data.success){
                        getListOrg(butType);
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
            layer.close(index);
        });
    }
    $('.lay-auth-tree-box').on('mouseover','.auth-status',function(){
        $(this).find('.tree-hide-btn').show();
    }).on('mouseout','.auth-status',function(){
        $(this).find('.tree-hide-btn').hide();
    });
    //添加人员

    var addUserToDepartH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item typeJG">'+
        '<label class="layui-form-label">人员选择</label>'+
        '<div class="layui-input-block">'+
        '<select name="selectuser" xm-select="select1" lay-search xm-select-skin="normal" xm-select-search=""></select>' +
        '</div>'+
        '</div>'+
        '</div>';
    function addUserToDepart(types,objs){
        layer.open({
            type: 1,
            area: '512px',
            title: '添加人员',
            content: addUserToDepartH,
            btn: ['确定', '取消'],
            success: function (layero, index) {
                formSelects.config('select1', {
                    type: 'get', //请求方式: post, get, put, delete...
                    header: {}, //自定义请求头
                    data: {
                        page:1,
                        pageSize:100
                    }, //自定义除搜索内容外的其他数据
                    // searchUrl: origin + '/View/listTeacherInfo?orgId='+org_id, //搜索地址, 默认使用xm-select-search的值, 此参数优先级高
                    searchUrl: '', //搜索地址, 默认使用xm-select-search的值, 此参数优先级高
                    searchName: '', //自定义搜索内容的key值
                    searchVal: '', //自定义搜索内容, 搜素一次后失效, 优先级高于搜索框中的值
                    keyName: 'realname', //自定义返回数据中name的key, 默认 name
                    keyVal: 'id', //自定义返回数据中value的key, 默认 value
                    keySel: 'selected', //自定义返回数据中selected的key, 默认 selected
                    keyDis: 'disabled', //自定义返回数据中disabled的key, 默认 disabled
                    keyChildren: 'children', //联动多选自定义children
                    delay: 500, //搜索延迟时间, 默认停止输入500ms后开始搜索
                    direction: 'auto', //多选下拉方向, auto|up|down
                    response: {
                        statusCode: 0, //成功状态码
                        statusName: 'code', //code key
                        msgName: 'msg', //msg key
                        dataName: 'data' //data key
                    },
                    success: function(id, url, searchVal, result) { //使用远程方式的success回调
                    },
                    error: function(id, url, searchVal, err) { //使用远程方式的error回调
                        console.log(err); //err对象
                    },
                    beforeSuccess: function(id, url, searchVal, result) { //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
                        for(var i=0;i<result.data.items.length;i++){
                            var thisNowinfo = result.data.items[i];
                            if(userBmAll.indexOf(thisNowinfo.id)>-1){
                                result.data.items[i].disabled="disabled";
                            }
                        }
                        return result.data; //必须return一个结果, 这个结果要符合对应的数据结构
                    },
                    beforeSearch: function(id, url, searchVal) { //搜索前调用此方法, return true将触发搜索, 否则不触发
                        if (!searchVal) { //如果搜索内容为空,就不触发搜索
                            return false;
                        }
                        return true;
                    },
                    clearInput: true, //当有搜索内容时, 点击选项是否清空搜索内容, 默认不清空
                }, false);
                layero.addClass('layui-form').attr('lay-filter', 'fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                formSelects.btns('select1', []);
                $.ajax({
                    url:origin + '/View/listTeacherInfo?orgId='+org_id+'&page=1&pageSize=100',
                    type:'get',
                    dataType:'json',
                    success:function(res){
                        console.log(res);
                        if(res.success){
                            var thisInfo = res.data.items;
                            for(var i=0;i<thisInfo.length;i++){
                                thisInfo[i].realname = thisInfo[i].realname+' ('+thisInfo[i].loginName+')';
                            }
                            formSelects.data('select1', 'local', {
                                arr: thisInfo
                            });
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                });
                formSelects.value('select1', userBmAll);
                //监听下拉框的打开
                form.render();
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    console.log(data);
                    $.ajax({
                        url:origin+'/View/departmentUser',
                        type:'POST',
                        dataType:'json',
                        data:{
                            departmentId:parentIdBM,
                            userId:data.field.selectuser
                        },
                        success:function(data){
                            console.log(data);
                            if(data.success){
                                layer.closeAll();
                                getListOrg(butType);
                                layer.msg('添加成功');
                            }else{
                                layer.msg(data.msg);
                            }
                        }
                    })
                })
            }
        })
    }
    //添加到部门
    var addToDepartH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item typeJG">'+
        '<label class="layui-form-label">选择部门</label>'+
        '<div class="layui-input-block chooseDepart">'+
        '<input type="hidden" name="departs" lay-verify="departs">'+
        '<ul id="ADD-tree-depart"></ul>'+
        '</div>'+
        '</div>'+
        '</div>';
    function addToDeparts(types,objs){
        var titl;
        if(types == 'add'){
            titl = '添加到部门';
            var checkuserId = objs.map(function(item,index){
                return item.id;
            }).join(',');
        }else{
            titl = '编辑部门';
            var checkuserId = objs.id;
        }
        layer.open({
            type: 1,
            area: '512px',
            title: titl,
            content: addToDepartH,
            btn: ['确定', '取消'],
            success: function (layero, index) {
                getTreeListData(types);
                layero.addClass('layui-form').attr('lay-filter', 'fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
            },
            yes:function(){
                form.on('submit(fromContent)', function (res) {
                    $('input[name="departs"]').val('');
                    console.log(res);
                    $.ajax({
                        url:origin+'/View/departmentUser',
                        type:'POST',
                        dataType:'json',
                        data:{
                            departmentId:res.field.departs,
                            userId:checkuserId
                        },
                        success:function(data){
                            console.log(data);
                            if(data.success){
                                getListOrg(butType);
                                layer.closeAll();
                                layer.msg('添加成功');
                            }else{
                                layer.msg(data.msg);
                            }
                        }
                    })
                })
            }
        })
    }
    //人员绑定部门
    function postBindUser(res){
        $.ajax({
            url:origin+'/View/departmentUser',
            type:'POST',
            dataType:'json',
            data:{
                departmentId:res
            },
            success:function(data){
                if(data.success){
                    layer.msg('添加成功')
                }else{
                    layer.msg(data.msg);
                }
                console.log(data);
            }
        })
    }
    //获取部门树结构
    function getTreeListData(tagss){
        var checkId = [];
        $.ajax({
            url:origin+'/View/allDepartmentList?parentId='+org_id,
            type:'GET',
            dataType:'json',
            success:function(data){
                console.log(data);
                treeDatas = data.data[0].children;
                var checkbox;
                if(tagss == 'add'){
                    checkbox = '';
                }else{
                    checkbox = 'checkbox'
                }
                othertree({
                    elem: '#ADD-tree-depart', //指定元素
                    check: checkbox, //勾选风格
                    skin: 'as', //设定皮肤
                    spreadAll:true,
                    // drag: true,
                    props:{
                        addBtnLabel:'<i class="iconfont icon-zengjia1" title="添加"></i>',
                        deleteBtnLabel:'<i class="iconfont icon-shanchu1" title="删除"></i>',
                        editBtnLabel:'<i class="iconfont icon-bianji"  title="编辑"></i>',
                        name: 'name',
                        id: 'id',
                        children:'children',
                        checkbox:'checkbox',
                        spread:'spread'
                    },
                    change:function(val){
                        var checkidstr;
                         layui.each(val,function(index,item){
                             checkId.push(item.id)
                        });
                        checkidstr = checkId.join(',');
                        $('input[name="departs"]').val(checkidstr);
                    },
                    click: function(item,elem) { //点击节点回调
                        if(tagss == 'add'){
                            $('.typeJG .layui-atree-node').attr('data','0');
                            $(elem).attr('data','1');
                            $('input[name="departs"]').val(item.id);
                        }else{

                        }
                    },
                    nodes: treeDatas
                });
            }
        })
    }
});

