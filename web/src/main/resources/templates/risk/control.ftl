<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="//at.alicdn.com/t/font_1205992_y6fcnyw4tpf.css">
		<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
		<link rel="stylesheet" href="/Public/css/common.css">

		<script src="/Public/js/vue.min.js"></script>
		<script src="/Public/js/axios.min.js"></script>
		<script src="https://unpkg.com/element-ui/lib/index.js"></script>
		<style>
			.el-table .warning-row {
			    background: #DAA520;
			    color:#000000;
			 }
			
			 .el-table .success-row {
			    background: #4169E1;
			    color:#000000;
			 }
			 .el-table .danger-row{
			 	background:#FF0000;
			 	color:#000000;
			 }
			 .el-table .common-row{
			 	background:#FFFF00;
			 	color:#000000;
			 }
			 .header-row{
			 	background:#DCDCDC !important;
			 }
		</style>
	</head>

	<body>
		<div id="app">
			<el-container>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-col :span="12">
							<el-select placeholder="请选择" v-model="topselect.date">
							    <el-option
							      key="2019"
							      label="2019年"
							      value="2019">
							    </el-option>
							 </el-select>
							<el-select placeholder="请选择" v-model="topselect.orgs.value" v-if="role=='ROLE_SUPERADMIN'">
							    <el-option
							      v-for="item in topselect.orgs.data"
							      :key="item.id"
							      :label="item.name"
							      :value="item.id">
							    </el-option>
							  </el-select>
							  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
							  <el-button type="primary" @click="print">打印</el-button>
						</el-col>
						<el-col :span="12" style="text-align:right;">
							<el-button circle type="success" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'" icon="el-icon-plus" @click="dialogFormVisible = true"></el-button>
						</el-col>
					</el-row>
					<el-table :max-height="tableHeight" border resizable :header-cell-style="headerStyle" :data="tableData" style="width: 100%" :span-method="arraySpanMethod" :cell-class-name="cellClassMethod" ref="singleTable">
						<el-table-column fixed prop="index" label="序号" width="60"></el-table-column>
						<el-table-column fixed prop="postName" label="岗位（设备设施/作业活动）单元" max-width="150">
						</el-table-column>
						<el-table-column prop="harmfulFactors" label="危险有害因素" >
						</el-table-column>
						<el-table-column prop="levelName" label="安全风险等级" width="120"></el-table-column>
						<el-table-column prop="measure" label="现有措施有效性"></el-table-column>
						<el-table-column prop="departmentName" label="责任部门"></el-table-column>
						<el-table-column prop="personName" label="责任人"></el-table-column>
						<el-table-column label="操作" width="140px" v-if="curData.state==1 && role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'">
							<template slot-scope="scope">
						        <el-button @click="edit(scope.row,'validateForm')" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>

			</el-container>
			<el-dialog title="安全风险分级管控" :visible.sync="dialogFormVisible" @open="dialogFormOpen" @close="dialogClose('validateForm')">
			  <el-form :model="form" label-width="110px" label-position="right" ref="validateForm">
			    <el-form-item label="岗位单元" prop="postName">
			    	<el-select v-model="form.postName" placeholder="请选择">
					    <el-option
					      v-for="item in post_options"
					      :key="item.name"
					      :label="item.name"
					      :value="item.name">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item label="危险有害因素" prop="harmfulFactors">
                    <el-input v-model="form.harmfulFactors" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="安全风险评价" prop="levelName">
				    <el-select v-model="form.levelName" placeholder="请选择风险等级">
				      <el-option v-for="item in levels" :label="item.name" :key="item.name" :value="item.name"></el-option>
				    </el-select>
			  	</el-form-item>
			    <el-form-item label="现有措施有效性" prop="measure">
			      <el-input v-model="form.measure" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="责任部门" prop="departmentName">
			      <el-input v-model="form.departmentName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任人" prop="personName">
			      <el-input v-model="form.personName" autocomplete="off"></el-input>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('validateForm')">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript">
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
		if(this.$data.role == 'ROLE_SUPERADMIN'){
			axios.get('/View/allOrgList',{params:{parentId:'0'}}).then(response=>{
				if(response.data.success === true){
					response.data.data.forEach(e=>that.$data.topselect.orgs.data.push(e));
					that.search();
				}else{
					that.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		}else{
			this.search();
		}
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
					value:'${MEMBER_ORGID}',
					data:[]
				},
				date:'2019'
			},
			tableHeight: window.innerHeight - 110
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
			const loading = this.$loading({
	          lock: true,
	          text: 'Loading',
	          spinner: 'el-icon-loading',
	          background: 'rgba(0, 0, 0, 0.7)'
	        });
			axios.get('/safety/riskControl/riskControl',{params:{year:this.$data.topselect.date,orgId:this.$data.topselect.orgs.value}}).then(response=>{
				loading.close();
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
</script>
    <script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
    <script src="/node_modules/jquery/jquery.PrintArea.js"></script>
    <script>
        // $(document).ready(function(){
        //     $("#print").click(function(){
        //         $("#divprint").printArea();
        //     });
        // });
    </script>
</html>