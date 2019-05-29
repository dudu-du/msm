

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
        }).catch(err=>{
            this.$message.error('服务器异常，请稍后再试！');
    });
    },
    data:function(){
        return {
            data: [],
            dialogFormVisible:false,
            form:{
            	riskPosition:'',
            	harmfulFactors:'',
            	measure:'',
            	runawayPerformance:'',
            	controlOrgName:'',
            	controlOrgPersonName:'',
            	investigationOrgName:'',
            	investigationOrgPersonName:'',
            	investigationCount:'',
            	remark:''
            }
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },
        addBtn(formName){
        	this.$data.dialogFormVisible = true;
        	axios.post('/safety/checkDangerChecklist/checkDangerChecklistByPage',this.$data.form).then(function(res){
        		this.$refs[formName].resetFields();
        		if(response.data.success === true){
        			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        	
        },
        closedDialog(formName){
        	this.$refs[formName].resetFields();
        }
}
});