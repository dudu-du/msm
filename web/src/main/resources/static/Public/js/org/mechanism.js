/**
 * Created by Administrator on 2018/11/9.
 */
layui.config({
    base: '/node_modules/layui-extends/'
}).extend({
    treeGrid:'treeGrid',
    authtree: 'atree'
});
var parentIdBM,treeDatas,parentInfoJG,editObj=null,ptable=null,treeGrid=null,tableId='treeTable',layer=null,bmPid,bmId,onloadType="index";
layui.use(['element','table','atree','layer','form','treeGrid','eleTree'], function(){
    var element = layui.element,
        authtree = layui.atree,
        eletree = layui.eleTree,
        treeGrid = layui.treeGrid,//很重要
        form = layui.form,
        layer = layui.layer;
    // 树结构初始化
    loadTree();
    function loadTree(){
        $.ajax({
            url: '/View/allOrgList?parentId=0',
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
                        $('.layui-atree-node').attr('data','0');
                        $(elem).attr('data','1');
                        $('button[name="department"]').show();
                        parentInfoJG = item;
                        console.log(parentInfoJG);
                        parentIdBM = item.id;
                        getTreeOrg(item.id,item)
                    },
                    addClick:function(item,elem,add){
                        addMaskJG('add',item,'ai',add);
                    },
                    editClick:function(item,elem,edit){
                        console.log(item,elem,edit);
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
    layui_card.on('click','.welsee-card-btn',function(){
        var thisName = $(this).attr('name');
        if(thisName == 'mechanism'){
            addMaskJG('add','','btn','');  //机构add
        }else if(thisName == 'mechanismsort'){
            treeSort();
        }
    });
    //tableTree初始化
    function getTreeOrg(id,datas){
        ptable=treeGrid.render({
            id:tableId
            ,elem: '#'+tableId
            ,idField:'id'
            ,url:origin+'/View/allDepartmentRow?parentId='+id
            ,method:'get'
            ,toolbar: '#toolbarDemo'
            ,parseData: function(res){ //res 即为原始返回的数据
                console.log(res);
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": 1, //解析数据长度
                    "data": res.data //解析数据列表
                };
            },
            response: {
                statusName:'status',
                statusCode: 200 //规定成功的状态码，默认：0
            }
            ,skin:'line'
            ,treeId:'id'//树形id字段名称
            ,treeUpId:'parentId'//树形父id字段名称
            ,treeShowName:'name'//以树形式显示的字段
            ,cols: [[
                {field:'name', title: '学校部门'},
                {field:'sort', title: '排序'},
                {title: '操作',width:190,toolbar: '#barDemo'}
            ]]
            ,page:false,
            done:function(res){
                $("#orgcontent").html("<em>"+datas.name+"</em><em>机构编码:"+datas.code+"</em><em>域名:"+(datas.domainName==null?"未设置":datas.domainName)+"</em>");
            }
        });
        treeGrid.on('tool('+tableId+')',function (obj) {
            var data = obj.data;
            console.log(data);
            if(obj.event === 'del'){
                del(data);
            }else if(obj.event==="add"){
                addMaskBM('add',data,'line');
            }else if(obj.event==="edit"){
                addMaskBM('edit',data,'edit');
            }
        });
        $('button[lay-event="adddepar"]').on('click',function(){
            addMaskBM('add','','top');
        });
        function del(obj) {
            layer.confirm('确认要删除吗？', function(index){
                layer.close(index);
                $.ajax({
                    url: '/View/org?orgId='+obj.id,
                    type:'DELETE',
                    data:{},
                    dataType: 'json',
                    success: function(data){
                        if(data.success){
                            getTreeOrg(parentIdBM,parentInfoJG);
                            console.log(data);
                        }else{
                            layer.msg(data.msg);
                        }
                    }
                });
            });
        }
    }
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
            url: origin+"/View/allOrgList?parentId=0",
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
    function delMaskJG(item,eve){
        layer.confirm('确认要删除该数据吗？', function(index){
            $.ajax({
                url: '/View/org?orgId='+item.id,
                type:'DELETE',
                data:{},
                dataType: 'json',
                success: function(data){
                    console.log(data);
                    if(data.success){
                        layer.msg(data.msg);
                        eve();
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
            layer.close(index);
        });
    }
    var thisCode,addMaskBMH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">上级部门</label>'+
        '<div class="layui-input-block departree">' +
        '<input type="hidden" name="topmech" lay-verify="topmechs">'+
        '<ul id="LAY-auth-tree-index-new"></ul>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">部门名称</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入部门名称" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">排序</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="sort" lay-verify="sort" autocomplete="off" placeholder="请输入排序序号" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskBM(tag,datas,addtype){
        var tilT;
        if(addtype == 'top'){
            tilT = '添加部门';
        }else if(addtype == 'line'){
            tilT = '新增子部门';
        }else{
            tilT = '编辑部门';
        }
        layer.open({
            type:1,
            area:'484px',
            title:tilT,
            content:addMaskBMH,
            btn:['确定','取消'],
            success:function(layero, index){
                getTreeListData(addtype,datas);
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                form.render();
                form.verify({
                    topmechs:function(value){
                        if(value == ''){
                            return '请选择上级部门'
                        }
                    },
                    name:function(value){
                        if(value == ''){
                            return '部门名称不能为空'
                        }
                    },
                    sort:function(value){
                        if(value == ''){
                            return '排序序号不能为空'
                        }
                    }
                });
                if(tag == 'edit'){
                    form.val('fromInput', {
                        "name": datas.name,
                        "sort":datas.sort
                    })
                }
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    console.log(data,data.field);
                    if(tag == 'add'){
                        addBM(data.field);
                    }else{
                        editBM(data.field,datas);
                    }
                });
            }
        })
    }
    function addBM(res){
        console.log(res);
        $.ajax({
            url: '/View/org',
            type:'POST',
            data:{parentId:res.topmech,orgType:'OT_DEPARTMENT',code:'1',name:res.name,sort:res.sort},
            dataType: 'json',
            success: function(data){
                console.log(data);
                if(data.success){
                    layer.closeAll();
                    layer.msg('添加成功');
                    getTreeOrg(parentIdBM,parentInfoJG);
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function editBM(res,lineData){
        console.log(res,lineData);
        $.ajax({
            url: '/View/org',
            type:'PUT',
            data:{orgId:lineData.id,parentId:res.topmech,orgType:'OT_DEPARTMENT',code:'1',name:res.name,sort:res.sort},
            dataType: 'json',
            success: function(data){
                console.log(data);
                if(data.success){
                    layer.closeAll();
                    layer.msg('修改成功');
                    getTreeOrg(parentIdBM,parentInfoJG);
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function getTreeListData(tagss,thisData){
        onloadType = 'layer';
        $.ajax({
            url:origin+'/View/allDepartmentList?parentId='+parentIdBM,
            type:'GET',
            dataType:'json',
            success:function(data){
                console.log(data);
                treeDatas = data.data;
                console.log(thisData);
                if(tagss == 'line'){
                    bmPid = thisData.id;
                    bmId = thisData.id;
                }else if(tagss == 'edit'){
                    bmId = thisData.id;
                    bmPid = thisData.parentId;
                }else{
                    bmId = thisData.id;
                    bmPid = parentIdBM;
                }
                $('input[name="topmech"]').val(bmPid);
                var tree = authtree({
                    elem: '#LAY-auth-tree-index-new', //指定元素
                    check: 'checkbox', //勾选风格
                    skin: 'as', //设定皮肤
                    spreadAll:true,
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
                        console.log(item);
                        if(tagss == 'edit'){
                            if(bmId == item.id){
                                layer.msg('请勿选择自身部门');
                                return false;
                            }else if(item.orgIds.indexOf(bmId)>0){
                                layer.msg('请勿选择自身部门的子部门');
                                return false;
                            }else{
                                $('.departree .layui-atree-node').attr('data','0');
                                $(elem).attr('data','1');
                            }
                        }else{
                            $('.departree .layui-atree-node').attr('data','0');
                            $(elem).attr('data','1');
                        }
                        $('input[name="topmech"]').val(item.id);
                    },
                    nodes: treeDatas
                });
            }
        })
    }

    $('.lay-auth-tree-box').on('mouseover','.auth-status',function(){
        $(this).find('.tree-hide-btn').show();
    }).on('mouseout','.auth-status',function(){
        $(this).find('.tree-hide-btn').hide();
    });
    var addMaskJGH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item typeJG">'+
        '<label class="layui-form-label">机构类型</label>'+
        '<div class="layui-input-block">'+
        '<input type="radio" name="sex" lay-filter="type" value="OT_BUREAU" title="局级单位" checked="">'+
        '<input type="radio" name="sex" lay-filter="type" value="OT_SCHOOL" title="校级单位">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">机构名称</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="name" lay-verify="name" autocomplete="off" placeholder="请输入机构名称" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">机构编码</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="code" lay-verify="code" autocomplete="off" placeholder="请输入机构编码" class="layui-input">'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item layui-form-hide">'+
        '<label class="layui-form-label">学校类型</label>'+
        '<div class="layui-input-block">'+
        '<select name="interest" lay-verify="school" lay-filter="aihao">'+
        '<option value="" name="none"></option>'+
        '</select>'+
        '</div>'+
        '</div>'+
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">绑定域名</label>'+
        '<div class="layui-input-block">'+
        '<input type="text" name="domain" lay-verify="domain" autocomplete="off" placeholder="请输入绑定域名" class="layui-input">'+
        '</div>'+
        '</div>'+
        '</div>';
    function addMaskJG(tag,datas,domt,eve){
        var tilT;
        if(tag == 'add'){
            tilT = '添加机构';
        }else{
            tilT = '编辑机构';
        }
        layer.open({
            type:1,
            area:'484px',
            title:tilT,
            content:addMaskJGH,
            btn:['确定','取消'],
            success:function(layero, index){
                if(domt == 'btn'|| tag == 'edit'){
                    $('.typeJG').addClass('layui-hide');
                }
                layero.addClass('layui-form').attr('lay-filter','fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                $('select[name="interest"] .schoolInfo').remove();
                $.ajax({
                    url: '/View/getDictList',
                    type:'GET',
                    async: false,
                    data:{parentCode:'WE_SCHOOLTYPE'},
                    dataType: 'json',
                    success: function(res){
                        var schoolData = res.data;
                        for (var i = 0; i < schoolData.length; i++) {
                            var options = '<option class="schoolInfo" value="'+schoolData[i].code+'">' + schoolData[i].name + '</option>';
                            $(options).appendTo('select[name="interest"]');
                        }
                        form.render('select', 'fromInput');
                    }
                });
                form.render();
                if(tag == 'edit'){
                    if(datas.orgType == 'OT_BUREAU'){
                        form.val('fromInput', {
                            "name": datas.name ,
                            "code": datas.code,
                            "sex": datas.orgType,
                            "domain":datas.domainName
                        })
                    }else{
                        $('.lay-mask-box .layui-form-hide').show();
                        form.val('fromInput', {
                            "name": datas.name ,
                            "code": datas.code,
                            "sex": datas.orgType,
                            "interest":datas.remarksType,
                            "domain":datas.domainName
                        });
                    }
                }
                form.verify({
                    name:function(value){
                        if(value == ''){
                            return '机构名称不能为空'
                        }
                    },
                    code:function(value){
                        if(value == ''){
                            return '机构编码不能为空'
                        }else{
                            if(!/^[0-9]*$/.test(value)){
                                return '只能填写数字'
                            }
                        }
                    },
                    school:function(value){
                        if(value == '0'){
                            return '学校类型不能为空'
                        }
                    }
                });
                //单选切换
                form.on('radio(type)',function(data){
                    if(data.value == 'OT_SCHOOL'){
                        $('option[name="none"]').val('0');
                        $('.lay-mask-box .layui-form-hide').show();
                    }else{
                        $('option[name="none"]').val('');
                        $('.lay-mask-box .layui-form-hide').hide();
                    }
                    form.render('select', 'fromInput');
                });
            },
            yes:function(){
                form.on('submit(fromContent)', function (data) {
                    console.log(data);
                    layer.closeAll();
                    var parentId,addparentId;
                    parentId = domt == 'ai' ? datas.parentId : 0;
                    addparentId = domt == 'ai' ? datas.id : 0;
                    if(tag == 'add'){
                        $.ajax({
                            url: '/View/org',
                            type:'POST',
                            data:{parentId:addparentId,orgType:data.field.sex,code:data.field.code,name:data.field.name,remarksType:data.field.interest,domainName:data.field.domain},
                            dataType: 'json',
                            success: function(dataA){
                                console.log(dataA);
                                if(dataA.success){
                                    layer.msg('新建成功');
                                    var itemAdd = {
                                        name: dataA.data.name,
                                        id: dataA.data.id,
                                        code:dataA.data.code,
                                        orgType:dataA.data.orgType,
                                        parentId:dataA.data.parentId,
                                        remarksType:dataA.data.remarksType,
                                        domainName:dataA.data.domainName
                                    };
                                    if(domt == 'ai'){
                                        eve(itemAdd);
                                    }else{
                                        $('#LAY-auth-tree-index li').remove();
                                        loadTree();
                                    }
                                }else{
                                    layer.msg(dataA.msg);
                                }
                            }
                        });
                    }else{
                        $.ajax({
                            url: '/View/org',
                            type:'PUT',
                            data:{orgId:datas.id,orgType:data.field.sex,code:data.field.code,name:data.field.name,parentId:parentId,remarksType:data.field.interest,domainName:data.field.domain},
                            dataType: 'json',
                            success: function(dataE){
                                console.log(dataE);
                                if(dataE.success){
                                    layer.msg('修改成功');
                                    datas.name=data.field.name;
                                    datas.code=data.field.code;
                                    datas.orgType=data.field.sex;
                                    datas.remarksType=data.field.interest;
                                    datas.domainName=data.field.domain;
                                    eve(data.field.name)
                                }else{
                                    layer.msg(dataE.msg);
                                }
                            }
                        });
                    }
                });
            }
        })
    }
});

