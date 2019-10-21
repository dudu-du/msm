layui.use(['element','form','layer','upload'],function(){
    var form = layui.form,
        element = layui.element,
        upload = layui.upload,
        layer = layui.layer;
    getUserInfo(loginId);
    element.on('tab(devTab)', function(data) {
        var gid = $(this).attr('data-id');
        if(gid == '0'){
            form.verify({
                nickname:function(value){
                    if(value == ''){
                        return '昵称不能为空'
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
                        return '邮箱地址不能为空'
                    }else{
                        if(!/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(value)){
                            return '邮箱格式不正确'
                        }
                    }
                }*/
            });
        }else{
            form.verify({
                nowPassWord:function(value){
                    if(value == ''){
                        return '当前密码不能为空'
                    }
                },
                newPassWord:function(value){
                    if(value == ''){
                        return '新密码不能为空'
                    }
                },
                newPassWordYew:function(value){
                    if(value == ''){
                        return '请重复新密码'
                    }
                    var newpassword = $('input[name="newPassWord"]').val();
                    if(value!=newpassword){
                        return '密码输入不一致'
                    }
                }
            });
        }
    })
    var uploaduserImageH = '<div class="lay-user-screen">' +
        '<div class="ScreenBtn">' +
        '<div class="table-btns-box">'+
        '<button class="layui-btn layui-btn-sm" lay-event="chose" id="upload"><i class="iconfont icon-touxiang1"></i>选择头像</button>'+
        '<button class="layui-btn layui-btn-sm layui-btn-disabled" name="rotate" data-option="-45" lay-event="addTeacher"><i class="iconfont icon-youxuan"></i>右旋转</button>'+
        '<button class="layui-btn layui-btn-sm layui-btn-disabled" name="rotate" data-option="45" lay-event="addTeacher"><i class="iconfont icon-zuoxuan"></i>左旋转</button>'+
        '</div>'+
        '</div>'+
        '<div class="imgScreen clearfix">' +
        '<div class="ScreenLeft"><img src="" id="image" alt=""></div>' +
        '<div class="ScreenRight">' +
        '<div class="preview"><div class="previewBox img144"><img src="/Public/image/144.png" alt=""></div><span>大尺寸头像（144*144px）</span></div>'+
        '<div class="preview"><div class="previewBox img83"><img src="/Public/image/83.png" alt=""></div><span>中等尺寸头像（83*83px）</span></div>'+
        '<div class="preview"><div class="previewBox img30"><img src="/Public/image/30.png" alt=""></div><span>小尺寸头像（30*30px）</span></div>'+
        '</div>' +
        '</div>'+
        '</div>';
    $('a[lay-filter="choseImg"]').on('click',function(){
        var oldImage = $(this).find('img').attr('src');
        var userLoad;
        layer.open({
            type: 1,
            area: ['824px','563px'],
            title: '头像上传',
            content: uploaduserImageH,
            btn: ['确定', '取消'],
            success: function (layero, index) {
                if($('.ScreenLeft div').length == 0){
                    $('button[name="rotate"]').prop('disabled',true);
                }
                var $image = $('#image');
                $image.attr('src',oldImage);
                if(oldImage == ''){
                    oldImage = '/Public/image/user/schoolAdmin.png';
                }
                $('.previewBox img').attr('src',oldImage);
                layero.addClass('layui-form').attr('lay-filter', 'fromInput');
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');
                userLoad = upload.render({
                    elem: '#upload'
                    ,auto:false
                    ,url: origin + '/View/file'
                    ,data: {
                        path: "headPortrait/"+org_id+"/"
                    }
                    ,field: 'file'
                    ,choose: function(obj){
                        $('button[name="rotate"]').prop('disabled',false).removeClass('layui-btn-disabled');
                        obj.preview(function(index, file, result){
                            console.log(file);
                            $image.cropper('destroy').attr('src', result).cropper({
                                aspectRatio: 1 / 1,
                                viewMode:1,
                                center:true,
                                modal:false,
                                autoCropArea:0.5,
                                minContainerHeight:364,
                                preview:".img144,.img83,.img30",
                                background:false,
                                crop: function(e){}
                            });
                        });
                        $('button[name="rotate"]').on('click',function(){
                            var rotateS = $(this).attr('data-option');
                            $image.cropper('rotate',rotateS);
                        });
                    },
                });
            },
            yes: function(){
                form.on('submit(fromContent)', function (res) {
                    var newImage = $('#image').attr('src');
                    if(oldImage == newImage){
                        layer.closeAll();
                    }else{
                        var cas=$('#image').cropper('getCroppedCanvas',{
                            width:144,
                            height:144
                        });
                        var base64url=cas.toDataURL('image/png');
                        $.ajax({
                            url: origin + '/View/fileBase64',
                            type: 'POST',
                            data: {
                                fileBase64:base64url,
                                path:"headPortrait/"+org_id+"/"
                            },
                            dataType: 'json',
                            success: function (data) {
                                console.log(data);
                                console.log(1);
                                layer.closeAll();
                                if(data.success){
                                    layer.msg('头像上传成功');
                                    $('a[lay-filter="choseImg"] img').attr('src',pictureOssUrl+data.data);
                                    $('input[name="userImg"]').val(data.data);
                                }else{
                                    layer.msg(data.msg);
                                }
                            }
                        })
                    }
                });
            }
        })
    });
    form.on('submit(userInfo)',function(res){
        console.log(res);
        var postData = res.field;
        $.ajax({
            type:'PUT',
            dataType:'json',
            url:origin+'/View/userInfo',
            data:{
                loginId:postData.loginId,
                loginName:postData.username,
                realname:postData.nickname,
                sex:postData.sex,
                pic:postData.userImg,
                tel:postData.cellphone,
                email:postData.email
            },
            success:function(data){
                if(data.success){
                    layer.msg('保存成功');
                    getUserInfo(loginId);
                    parent.location.reload();
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    })
    //修改密码
    form.on('submit(changePassWord)',function(res){
        console.log(res);
        var postData = res.field;
        $.ajax({
            type:'post',
            dataType:'json',
            url:origin+'/updatePassword',
            data:{
                oldPassword:postData.nowPassWord,
                password:postData.newPassWord
            },
            success:function(data){
                if(data.success){
                    layer.msg('修改成功');
                    form.val('changePassWord',{
                        nowPassWord:'',
                        newPassWord:'',
                        newPassWordYew:''
                    })
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    })
    function getUserInfo(id){
        $.ajax({
            type:'GET',
            dataType:'json',
            url:origin+'/View/userInfo',
            data:{
                loginId:id,
            },
            success:function(data){
                console.log(data);
                if(data.success){
                    var userInfoData = data.data;
                    var roleUser = userInfoData.roleList.join('');
                    $('a[lay-filter="choseImg"] img').attr('src',pictureOssUrl+userInfoData.pic);
                    $('input[name="userImg"]').val(userInfoData.pic);
                    form.val('userInfo',{
                        username:userInfoData.loginName,
                        nickname:userInfoData.realname,
                        sex:userInfoData.sex,
                        userImg:userInfoData.pic,
                        cellphone:userInfoData.tel,
                        email:userInfoData.email,
                    })
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    }
});