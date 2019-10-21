

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

			axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize}}).then(function(response){
        		if(response.data.success === true){
        			that.$data.tableData = [];
        			that.$data.page.total = response.data.data.total;
        			response.data.data.list.forEach(e=>{
        				that.$data.data.push(e);
        			});
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        },
        openPrint(row){
            window.open("/safety/checkRectificationReceipt/checkRectificationReceiptPrint?"+row.id);
        }
}
});