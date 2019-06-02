

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        this.search();
    },
    data:function(){
        return {
        	curPage:1,
			page:{
				total:0,
				pageSize:10
			},
            data: []
        }
    },
    methods:{
        search(){
        	var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize}}).then(function(res){
            	if(res.data.success === true){
            		that.$data.data = [];
            		that.$data.page.total = res.data.data.total;
                	res.data.data.list.forEach(e=>{
                		that.$data.data.push(e);
                	});
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