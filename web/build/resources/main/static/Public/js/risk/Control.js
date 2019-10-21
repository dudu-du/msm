var Control = function(obj) {
	
	if(!obj) {
		this.postName = '';
		this.harmfulFactors = '';
		this.measure = '';
		this.levelName = '';
		this.departmentName = '';
		this.personName = '';
	}else{
		if(obj.index){
			this.index = obj.index;
		}
		if(obj.union){
			this.union = obj.union;
		}
		this.id = obj.id;
		this.postName = obj.postName;
		this.harmfulFactors = obj.harmfulFactors;
		this.measure = obj.measure;
		this.levelName = obj.levelName;
		this.departmentName = obj.departmentName;
		this.personName = obj.personName;
		this.riskControlFk = obj.riskControlFk;
		this.orgFk = obj.orgFk;
	}
};
Control.prototype = {
		toJava:function(){
			if(this.postName &&this.postName.split(',').length==2){
				this.postFk = this.postName.split(',')[0];
				this.postName = this.postName.split(',')[1];
			}
			if(this.harmfulFactors &&this.harmfulFactors.split(',').length==2){
				this.harmfulFactorsFk = this.harmfulFactors.split(',')[0];
				this.harmfulFactors = this.harmfulFactors.split(',')[1];
			}
			if(this.levelName &&this.levelName.split(',').length==2){
				this.levelFk = this.levelName.split(',')[0];
				this.levelName = this.levelName.split(',')[1];
			}
			return this;
		}
};


new Vue({
	el: '#app',
	components: {axios},
	created:function(){
		var that = this;
		axios.get('/View/allOrgList',{params:{parentId:'0'}}).then(response=>{
			if(response.data.success === true){
				if(response.data.data.length>0){
					that.$data.topselect.orgs.value = response.data.data[0].id;
				}
				response.data.data.forEach(e=>that.$data.topselect.orgs.data.push(e));
				that.search();
			}else{
				that.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
	},
	data: function() {
		return {
			dialogFormVisible: false,
			role:'${MEMBER_ROLE}',
			curData:{},
			activeNames:['1'],
			form: new Control(),
			post_options: [],
			facotrs:[],
			levels:[],
			tableData: [],
			topselect:{
				orgs:{
					value:'',
					data:[]
				},
				date:'2019'
			}
		}
	},
	methods: {
		arraySpanMethod({
			row,
			column,
			rowIndex,
			columnIndex
		}) {

			if(columnIndex === 0 || columnIndex === 1) {
				if(row.union) {
					return {
						rowspan: row.union,
						colspan: 1
					};
				} else {
					return {
						rowspan: 0,
						colspan: 0
					};
				}

			}
		},
		submitForm(formName){
			this.$data.dialogFormVisible = false;
			var formData = JSON.parse(JSON.stringify(this.$data.form.toJava()));
			formData.riskControlFk = this.$data.curData.id;
			formData.orgFk = this.$data.topselect.orgs.value;
			this.$refs[formName].resetFields();
			var isNew=true,index,isAdd=false,unionIndex=0;
			if(formData.id){
				delete formData.index;
				delete formData.union;
				axios.put('/safety/riskControlList/riskControlList',formData).then(response=>{
					if(response.data.success === true){
						this.$message.success(response.data.msg);
						this.search();
					}else{
						this.$message.warning(response.data.msg);
					}
				}).catch(err=>{
					this.$message.error('服务器异常，请稍后再试！');
				});
			}else{

				axios.post('/safety/riskControlList/riskControlList',formData).then(response=>{
					if(response.data.success === true){
						this.$message.success(response.data.msg);
						this.search();
					}else{
						this.$message.warning(response.data.msg);
					}
				}).catch(err=>{
					this.$message.error('服务器异常，请稍后再试！');
				});
			}
			
			
			
			
		},edit(row,formName){
			this.$data.form = new Control(row);
			this.$data.dialogFormVisible = true;
		},del(row){
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	        	axios.delete('/safety/riskControlList/riskControlList',{params:{id:row.id}}).then(response=>{
	        		if(response.data.success === true){
						this.$message.success(response.data.msg);
						this.search();
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
		cellClassMethod({row, column, rowIndex, columnIndex}){//表格单元格class触发方法
			if(columnIndex==3){//风险等级
				if(row.levelName === '重大风险'){
					return 'danger-row';
				}
				if(row.levelName === '较大风险'){
					return 'warning-row';
				}
				if(row.levelName === '一般风险'){
					return 'common-row';
				}
				if(row.levelName === '低风险'){
					return 'success-row';
				}
			}
		},
		search(){//搜索
			axios.get('/safety/riskControl/riskControl',{params:{year:this.$data.topselect.date,orgId:this.$data.topselect.orgs.value}}).then(response=>{
				if(response.data.success === true){
					this.$data.curData.id = response.data.data.id;
					this.$data.curData.state = response.data.data.state;
					this.$data.tableData = [];
					response.data.data.riskControlList.forEach(e=>this.$data.tableData.push(new Control(e)));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		},print(){
			console.log(this.$data.topselect.date,this.$data.topselect.orgs.value);
			var url = "/safety/riskControl/riskControlPrint?year="+this.$data.topselect.date+"&orgId="+this.$data.topselect.orgs.value+"";
			var encodeUrl = encodeURI(encodeURI(url));
			window.open(encodeUrl);
		},
		dialogFormOpen(){
			axios.get('/safety/riskDict/riskDictList',{params:{code:'postlist'}}).then(response=>{
				if(response.data.success === true){
					this.$data.post_options = [];
					response.data.data.forEach(e=>this.$data.post_options.push(e));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
			axios.get('/safety/riskDict/riskDictList',{params:{code:'levellist'}}).then(response=>{
				if(response.data.success === true){
					this.$data.levels = [];
					response.data.data.forEach(e=>this.$data.levels.push(e));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		},
		dialogClose(formName){
			this.$data.form = new Control();
			this.$refs[formName].resetFields();
		},
		headerStyle({row, rowIndex}){
			return 'background:#F5F7FA;';
		}
	}
});