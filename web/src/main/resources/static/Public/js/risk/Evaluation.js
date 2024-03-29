var Evaluation = function(obj) {
	
	if(!obj) {
		this.cause = '';
		this.postName = '';
		this.harmfulFactors = '';
		this.troubleNameList = [];
		this.consequence = '';
		this.incidence = '';
		this.possibility = '';
		this.seriousness = '';
		this.measure = '';
		this.numL = '';
		this.numE = '';
		this.numC = '';
		this.numD = '';
		this.levelName = '';
	}else{
		if(obj.index){
			this.index = obj.index;
		}
		if(obj.union){
			this.union = obj.union;
		}
		this.id = obj.id;
		this.cause = obj.cause;
		this.postName = obj.postName;
		this.harmfulFactors = obj.harmfulFactors;
		this.consequence = obj.consequence;
		this.incidence = obj.incidence;
		this.possibility = obj.possibility;
		this.seriousness = obj.seriousness;
		this.measure = obj.measure;
		this.numL = obj.numL;
		this.numE = obj.numE;
		this.numC = obj.numC;
		this.numD = obj.numD;
		this.levelName = obj.levelName;
		this.riskEvaluationFk = obj.riskEvaluationFk;
		this.orgFk = obj.orgFk;
		this.troubleNameList = obj.troubleNameList;
		if(obj.troubleName){
			this.troubleNameList = obj.troubleName.split(',');			
		}
		if(obj.troubleFk){
			this.troubleFkList = obj.troubleFk.split(',');			
		}
	}
};
Evaluation.prototype = {
		toJava:function(){
			if(this.troubleNameList){
				var s = '';
				this.troubleName = '';
				this.troubleNameList.forEach(e=>{
					this.troubleName =this.troubleName + s + e;
					s=',';
				});
				
			}
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
					this.$data.topselect.orgs.value = response.data.data[0].id;
				}
				response.data.data.forEach(e=>this.$data.topselect.orgs.data.push(e));
				that.search();
			}else{
				this.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
	},
	data: function() {
		return {
			dialogFormVisible: false,
			curData:{},
			activeNames:['1'],
			form: new Evaluation(),
			post_options: [],
			facotrs:[],
			troubles: [],
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
			formData.riskEvaluationFk = this.$data.curData.id;
			formData.orgFk = this.$data.topselect.orgs.value;
			this.$refs[formName].resetFields();
			var isNew=true,index,isAdd=false,unionIndex=0;
			if(formData.id){
				delete formData.index;
				delete formData.union;
				axios.put('/safety/riskEvaluationList/riskEvaluationList',formData).then(response=>{
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
				axios.post('/safety/riskEvaluationList/riskEvaluationList',formData).then(response=>{
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
			this.$data.form = new Evaluation(row);
			this.$data.dialogFormVisible = true;
		},del(row){
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	        	axios.delete('/safety/riskEvaluationList/riskEvaluationList',{params:{id:row.id}}).then(response=>{
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
			if(columnIndex==14){//风险等级
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
			axios.get('/safety/riskEvaluation/riskEvaluation',{params:{year:this.$data.topselect.date,orgId:this.$data.topselect.orgs.value}}).then(response=>{
				if(response.data.success === true){
					this.$data.curData.id = response.data.data.id;
					this.$data.curData.state = response.data.data.state;
					this.$data.tableData = [];
					response.data.data.riskEvaluationList.forEach(e=>this.$data.tableData.push(new Evaluation(e)));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		},print(){
			console.log(this.$data.topselect.date,this.$data.topselect.orgs.value);
			var url = "/safety/riskEvaluation/riskEvaluationPrint?year="+this.$data.topselect.date+"&orgId="+this.$data.topselect.orgs.value+"";
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
			axios.get('/safety/riskDict/riskDictList',{params:{code:'harmfullist'}}).then(response=>{
				if(response.data.success === true){
					this.$data.facotrs = [];
					response.data.data.forEach(e=>this.$data.facotrs.push(e));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
			axios.get('/safety/riskDict/riskDictList',{params:{code:'troublelist'}}).then(response=>{
				if(response.data.success === true){
					this.$data.troubles = [];
					response.data.data.forEach(e=>this.$data.troubles.push(e));
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
			this.$data.form = new Evaluation();
			this.$refs[formName].resetFields();
		},
		changeDialogWidth(){
			return '20%';
		}
	}
});