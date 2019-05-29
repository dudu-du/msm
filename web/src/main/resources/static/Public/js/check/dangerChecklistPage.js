

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        this.search(1,10);
    },
    data:function(){
        return {
            data: []
        }
    },
    methods:{
        next(currentPage,pageSize){
        	this.search(currentPage,pageSize);
        },prev(currentPage,pageSize){
        	this.search(currentPage,pageSize);
        },
        search(currentPage,pageSize){
        	var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
            	if(res.data.success === true){
            		that.$data.data = [];
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
}
});