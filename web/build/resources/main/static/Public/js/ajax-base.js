/**
 *模糊查询获取学校列表
 */
var orgName;
function getSearchSchool(laymodules,orgtype){
    $.ajax({
        url:origin+'/View/allTypeOrgList',
        type:'GET',
        async:false,
        dataType:'json',
        data:{
            orgType:orgtype
        },
        success:function(data){
            var selectData = data.data,optionH;
            layui.each(selectData,function(index,item){
                if(org_id == item.id){
                    orgName = item.name;
                }
                optionH = '<option value=' + item.name + ' name="search">' + item.name + '</option>';
                $('select[name="selectSearch"]').append(optionH);
            });
            laymodules.render();
        },
        error:function(){
        }
    })
}
/**
 * 根据学校id获取学校班级
 */
var schoolClassNum;
function getSchoolClass(orgidS){
    $.ajax({
        url:origin+'/View/classInfoNoPage?orgId='+orgidS,
        type:'get',
        async:false,
        dataType:'json',
        success:function(data){
            console.log(data);
            if(data.success){
                schoolClassNum = data.data.length;
            }
        }
    })
}

/**
 * 获取当前学期信息
 */
var nowSchoolYear;
function getSemesterNow(){
    $.ajax({
        url:origin+'/View/semesterCurrent',
        async:false,
        dataType:'json',
        success:function(res){
            console.log(res);
            if(res.success){
                nowSchoolYear = res.data.schoolYear;
            }else{
                nowSchoolYear = '';
            }
        }
    })
}
//根据设备id查找mac地址
var deviceMacS;
function searchMac(devid){
    var devArr = devid.split(',');
    $.ajax({
        url:origin+'/View/wisdomPlateList?orgId='+org_id,
        type:'GET',
        async:false,
        dataType:'json',
        success:function(data){
        var deviceMacArr = [];
            console.log(data);
            if(data.success){
                for(var i=0;i<data.data.length;i++){
                    var thisDevInfo = data.data[i];
                    for(var s=0;s<devArr.length;s++){
                        if(devArr[s] == thisDevInfo.deviceId){
                            deviceMacArr.push(thisDevInfo.deviceMac);
                        }
                    }
                }
                deviceMacS = deviceMacArr.join(',');
            }
        }
    })
}