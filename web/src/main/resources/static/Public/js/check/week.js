

new Vue({
	el:'#app',
	created:function(){
		var that = this;
		axios.get('/View/allOrgList',{params:{parentId:'0'}}).then(response=>{
			if(response.data.success === true){
				if(response.data.data.length>0){
					this.$data.topOptions.orgs.curId = response.data.data[0].id;
				}
				response.data.data.forEach(e=>this.$data.topOptions.orgs.data.push(e));
				that.search();
			}else{
				this.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
	},
	data:function(){
		return {
			topOptions:{
				year:'',
				orgs:{
					curId:'',
					data:[]
				}
			}
		};
	},
	methods:{
		search(){
			
		},
	}
});