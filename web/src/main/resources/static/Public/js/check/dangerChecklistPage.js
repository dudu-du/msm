

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:1,pageSize:2}}).then(function(res){
            that.data = res.data.data;
            console.log(that.data);
            that.data.list.forEach(function(data,index){
                that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+"月";
            });
        }).catch(err=>{
            this.$message.error('服务器异常，请稍后再试！');
    });
    },
    data:function(){
        return {
            data: []
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+"月";
                });
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].createTime=that.data.list[index].createTime[0]+"年"+that.data.list[index].createTime[1]+"月";
                });
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        }
}
});