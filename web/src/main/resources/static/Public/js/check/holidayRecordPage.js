


new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkHolidayRecord/checkHolidayRecordByPage',{params:{currentPage:1,pageSize:1}}).then(function(res){
            that.data = res.data.data;
            that.data.list.forEach(function(data,index){
                that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+that.data.list[index].createTime[2]+"月";
            });
            console.log(that.data);
        }).catch(err=>{
            this.$message.error('服务器异常，请稍后再试！');
    });
    },
    data:function(){
        return{
            data:[]
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkHolidayRecord/checkHolidayRecordByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+that.data.list[index].createTime[2]+"月";
                });
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkHolidayRecord/checkHolidayRecordByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+that.data.list[index].createTime[2]+"月";
                });
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        }
    }

});