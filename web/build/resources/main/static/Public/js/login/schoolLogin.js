var origin = window.location.origin;
layui.use(['layer', 'form'], function() {
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $(".sLoginBtn").trigger("click");
        }
    });

    var form = layui.form,
        layer = layui.layer;
    form.on('submit(fromContent)', function(data) {
        var objData = data.field;
        var txtUserName = data.field.userName;
        var txtPassword = data.field.password;
        var bakeurl = objData.backurl;
        var client_id = objData.client_id;
        $.post(origin + '/schoolLogin', {
            txtUserName: txtUserName,
            txtPassword: txtPassword
        }, function(data) {
            console.log(data);
            layui.sessionData('nav', null);
            if (data.success) {
                if (bakeurl == "") {
                    window.location.href = origin;
                } else {
                    window.location.href = origin + '/oauth2/authorize?response_type=code&client_id=' + client_id +'&redirect_uri=' + bakeurl;
                }
            }
        }, 'json')
    });
})