


new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkSeasonRecord/checkSeasonRecordByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
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
            axios.get('/safety/checkSeasonRecord/checkSeasonRecordByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkSeasonRecord/checkSeasonRecordByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        }
    }

});