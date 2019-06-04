


new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        this.search();
    },
    data:function(){
        return{
            curPage:1,
            page:{
                total:0,
                pageSize:10
            },
            data:[]
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/safetyNotificationCard/safetyNotificationCardByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/safetyNotificationCard/safetyNotificationCardByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },openPrint(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardPrint?"+row.id);
        },edit(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardEdit?"+row.id);
        },add(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardAdd");
        },del(row){
            var that=this;
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('/safety/safetyNotificationCard/safetyNotificationCard',{params:{id:row.id}}).then(response=>{
                    if(response.data.success === true){
                        this.$message.success(response.data.msg);
            }else{
                this.$message.warning(response.data.msg);
            }
        }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
        });
        },
        search(){
            var that = this;
            axios.get('/safety/safetyNotificationCard/safetyNotificationCardByPage',{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize}}).then(function(res){
                if(res.data.success === true){
                    that.$data.data = [];
                    that.data = res.data.data;
                    that.$data.page.total = res.data.data.total;
                }else{
                    that.$message.warning(res.data.msg);
                }
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        }
    },
    watch:{
        curPage(val){
            this.search();
        }
    }

});