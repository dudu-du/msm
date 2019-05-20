/**
 * Created by Administrator on 2018/11/9.
 */
layui.config({
    base: '/node_modules/layui-extends/'
}).extend({
    treeGrid:'treeGrid',
    authtree: 'atree'
});
var parentOrgId,treeDatas,editObj=null,ptable=null,treeGrid=null,tableId='treeTable',bmPid,bmId,layer=null,onloadType="layer";
layui.use(['element','layer','atree','form','treeGrid'], function(){
    var element = layui.element,
        form = layui.form,
        authtree = layui.atree,
        treeGrid = layui.treeGrid,//很重要
        layer = layui.layer;
    //tableTree初始化
    getTreeOrg();
    function getTreeOrg(){
        ptable=treeGrid.render({
            id:tableId
            ,elem: '#'+tableId
            ,idField:'id'
            ,url:origin+'/View/allDepartmentRow?parentId='+org_id
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
                {field:'name',width:400, title: '学校部门'},
                {field:'sort', title: '排序'},
                {title: '操作',width:190,toolbar: '#barDemo'}
            ]]
            ,page:false
        });
        treeGrid.on('tool('+tableId+')',function (obj) {
            var data = obj.data;
            console.log(data);
            if(obj.event === 'del'){
                del(data);
                // $('table tr').removeClass('layui-table-click');
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
                            getTreeOrg();
                            console.log(data);
                        }else{
                            layer.msg(data.msg)
                        }
                    }
                });
            });
        }
    }
    var thisCode,addMaskBMH = '<div class="lay-mask-box">' +
        '<div class="layui-form-item">'+
        '<label class="layui-form-label">上级部门</label>'+
        '<div class="layui-input-block departree">' +
        '<input type="hidden" name="topmech" lay-verify="topmechs">'+
        '<ul id="LAY-auth-tree-index"></ul>'+
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
        if(addtype == 'add'){
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
                if(addtype == 'top'){
                    getTreeListData(addtype);
                }else{
                    getTreeListData(addtype,datas);
                }
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
                }else{

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
                    getTreeOrg();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function editBM(res,lineData){
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
                    getTreeOrg();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
    function getTreeListData(tagss,thisData){
        $.ajax({
            url:origin+'/View/allDepartmentList?parentId='+org_id,
            type:'GET',
            dataType:'json',
            success:function(data){
                console.log(data);
                treeDatas = data.data;
                if(tagss == 'line'){
                    bmPid = thisData.id;
                    bmId = thisData.id;
                }else if(tagss == 'edit'){
                    bmId = thisData.id;
                    bmPid = thisData.parentId;
                }
                $('input[name="topmech"]').val(bmPid);
                var tree = authtree({
                    elem: '#LAY-auth-tree-index', //指定元素
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
                                $('.layui-atree-node').attr('data','0');
                                $(elem).attr('data','1');
                            }
                        }else{
                            $('.layui-atree-node').attr('data','0');
                            $(elem).attr('data','1');
                        }
                        $('input[name="codeJG"]').val(item.code);
                        $('input[name="topmech"]').val(item.id);
                    },
                    nodes: treeDatas
                });
            }
        })
    }
});

