<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
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
		</style>
	</head>

	<body>
		<div id="app">
			<el-container>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-select placeholder="请选择" v-model="topselect.date">
						    <el-option
						      key="2019"
						      label="2019年"
						      value="2019">
						    </el-option>
						 </el-select>
						<el-select placeholder="请选择" v-model="topselect.orgs.value" @change="orgsChange" v-if="role=='ROLE_SUPERADMIN'">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-select placeholder="请选择" v-model="topselect.postNames.value" @change="orgsChange">
						    <el-option>全部</el-option>
						    <el-option
						      v-for="item in topselect.postNames.data"
						      :key="item.name"
						      :label="item.name"
						      :value="item.name">
						    </el-option>
						  </el-select>
						  <el-select placeholder="请选择" v-model="topselect.levelNames.value" @change="orgsChange">
						    <el-option>全部</el-option>
						    <el-option
						      v-for="item in topselect.levelNames.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.name">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                       		 <el-button type="primary" @click="openPrint()">打印</el-button>
					</el-row>
					<el-row style="margin:10px 0 5px 0">
						<el-col :span="2"><span style="background-color:red;padding:8px;border-radius:6%;font-family:cursive;">重大风险：{{countMap.vb}}</span></el-col>
						<el-col :span="2"><span style="background-color:#DAA520;padding:8px;border-radius:6%;font-family:cursive;">较大风险：{{countMap.b}}</span></el-col>
						<el-col :span="2"><span style="background-color:#FFFF00;padding:8px;border-radius:6%;font-family:cursive;">一般风险：{{countMap.c}}</span></el-col>
						<el-col :span="2"><span style="background-color:#4169E1;padding:8px;border-radius:6%;font-family:cursive;">低风险：{{countMap.l}}</span></el-col>
						<el-col :span="16" style="text-align:right;" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'"><el-button circle type="success" icon="el-icon-plus" @click="dialogFormVisible = true"></el-button></el-col>
					</el-row>
					<el-row style="margin:5px 0 10px 0">
						<el-col :span="2"><span style="background-color:#B9D3EE;padding:8px;border-radius:6%;font-family:cursive;">人的因素：{{harmfulCountMap.p}}</span></el-col>
						<el-col :span="2"><span style="background-color:#FFEFD5;padding:8px;border-radius:6%;font-family:cursive;">物的因素：{{harmfulCountMap.t}}</span></el-col>
						<el-col :span="2"><span style="background-color:#AEEEEE;padding:8px;border-radius:6%;font-family:cursive;">管理因素：{{harmfulCountMap.m}}</span></el-col>
						<el-col :span="2"><span style="background-color:#C1CDC1;padding:8px;border-radius:6%;font-family:cursive;">环境因素：{{harmfulCountMap.e}}</span></el-col>
					</el-row>
					<el-table :max-height="tableHeight" resizable border :data="tableData" style="width: 100%" :span-method="arraySpanMethod" :cell-class-name="cellClassMethod" ref="singleTable">
						<el-table-column fixed prop="index" label="序号" width="60" ></el-table-column>
						<el-table-column fixed prop="postName" label="岗位（设备设施/作业活动）单元" width="150" >
						</el-table-column>
						<el-table-column label="安全风险辨识" >
							<el-table-column prop="harmfulFactors" label="危险有害因素" width="120" >
							</el-table-column>
							<el-table-column prop="troubleNameList" label="事故类型" >
								<template slot-scope="scope">
							        <el-tag type="warning" disable-transitions v-for="item in scope.row.troubleNameList">{{item}}</el-tag>
							    </template>
							</el-table-column>
							<el-table-column prop="cause" label="原因" ></el-table-column>
							<el-table-column prop="consequence" label="后果" v-if="false"></el-table-column>
							<el-table-column prop="incidence" label="影响范围" v-if="false"></el-table-column>
						</el-table-column>
						<el-table-column label="安全风险分析">
							<el-table-column prop="possibility" label="可能性" v-if="false"></el-table-column>
							<el-table-column prop="seriousness" label="严重性" v-if="false"></el-table-column>
							<el-table-column prop="measure" label="现有措施有效性"></el-table-column>
						</el-table-column>
						<el-table-column label="LEC风险分析法">
							<el-table-column prop="numL" label="事故发生的可能性(L)"></el-table-column>
							<el-table-column prop="numE" label="暴露于危险环境的频繁程度(E)"></el-table-column>
							<el-table-column prop="numC" label="发生事故产生的后果(C)"></el-table-column>
							<el-table-column prop="numD" label="D值"></el-table-column>
						</el-table-column>
						<el-table-column prop="levelName" label="安全风险评价">
							
						</el-table-column>
						<el-table-column prop="personName" label="负责人">

						</el-table-column>
						<el-table-column label="操作" width="140px" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'">
							<template slot-scope="scope">
								<el-popover
								  placement="top"
								  width="160" >
								  <div style="text-align: right; margin: 0">
								    <el-button size="mini" type="text" @click="addcheck(1,'checkForm',scope.row)">月检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(2,'checkForm',scope.row)">周检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(3,'checkForm',scope.row)">日检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(4,'checkForm',scope.row)">专项检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(5,'checkForm',scope.row)">综合检查(节假日、复产前)</el-button>
								    <el-button type="text" size="mini" @click="addcheck(6,'checkForm',scope.row)">综合检查(季节性)</el-button>
								  </div>
								  <el-button slot="reference"  type="info" size="mini" icon="el-icon-s-unfold" circle></el-button>
								</el-popover>
						        <el-button @click="edit(scope.row,'validateForm')" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
			</el-container>
			<el-dialog title="安全风险辨识项" :visible.sync="dialogFormVisible" @open="dialogFormOpen" @close="dialogClose('validateForm')">
			  <el-form :model="form" label-width="110px" label-position="right" ref="validateForm">
			    <el-form-item label="岗位单元" prop="postName">
			    	<el-select v-model="form.postName" placeholder="请选择">
					    <el-option
					      v-for="item in topselect.postNames.data"
					      :key="item.name"
					      :label="item.name"
					      :value="item.name">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item label="危险有害因素" prop="harmfulFactors">
			    	<el-select v-model="form.harmfulFactors" placeholder="请选择">
					    <el-option
					      v-for="item in facotrs"
					      :key="item.name"
					      :label="item.name"
					      :value="item.name">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item prop="troubleNameList">
			    	<el-transfer v-model="form.troubleNameList" :props="{key:'name',label:'name'}" :data="troubles" :titles=["事故类型","已选择"]></el-transfer>			    	
			    </el-form-item>
			    <el-form-item label="原因" prop="cause">
			      <el-input v-model="form.cause" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="后果" prop="consequence">
			      <el-input v-model="form.consequence" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="影响范围" prop="incidence">
			      <el-input v-model="form.incidence" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="可能性" prop="possibility">
			      <el-input v-model="form.possibility" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="严重性" prop="seriousness">
			      <el-input v-model="form.seriousness" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="现有措施有效性" prop="measure">
			      <el-input v-model="form.measure" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="L" prop="numL">
			      <el-input v-model="form.numL" type="number" step="0.01" autocomplete="off" placeholder="事故发生的可能性(L)"></el-input>
			    </el-form-item>
			    <el-form-item label="E" prop="numE">
			      <el-input v-model="form.numE" type="number" step="0.01" autocomplete="off" placeholder="暴露于危险环境的频繁程度(E)"></el-input>
			    </el-form-item>
			    <el-form-item label="C" prop="numC">
			      <el-input v-model="form.numC" type="number" step="0.01" autocomplete="off" placeholder="发生事故产生的后果(C)"></el-input>
			    </el-form-item>
			    <el-form-item label="D值" prop="numD">
			      <el-input v-model="form.numD" type="number" step="0.01" autocomplete="off"></el-input>
			    </el-form-item>
				  <el-form-item label="负责人" prop="personName">
					  <el-input v-model="form.personName" autocomplete="off"></el-input>
				  </el-form-item>
			    <el-form-item label="安全风险评价" prop="levelName">
				    <el-select v-model="form.levelFk" placeholder="请选择风险等级">
				      <el-option v-for="item in topselect.levelNames.data" :label="item.name" :key="item.id" :value="item.id"></el-option>
				    </el-select>
			  	</el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('validateForm')">确 定</el-button>
			  </div>
			</el-dialog>
			<el-dialog title="隐患排查治理" :visible.sync="checkFormVisible" @open="checkFormOpen" @close="dialogClose('checkForm')">
				<el-form  :model="checkForm" ref="checkForm" label-width="66px">
				  <el-form-item
				    v-for="(domain, index) in checkForm.domains"
				    :key="domain.key"
				  >
				  <el-col :span="4">
				  	<el-form-item>
					  	<el-select  v-model="domain.checkTypeName" placeholder="类型" v-if="checks.checktype == 1 || checks.checktype == 2 || checks.checktype == 4">
					      <el-option v-for="item in checkForm.typeList" :label="item.name" :value="item.name"></el-option>
					    </el-select>
				    </el-form-item>
				  </el-col>
				  <el-col :span="10">
				  	<el-form-item>
				    	<el-input v-model="domain.checkContent"  placeholder="检查项目及相关要求"></el-input>
				  	</el-form-item>
				  </el-col>
				  <el-col :span="6">
				  	<el-form-item>
				    	<el-input v-model="domain.checkMethod" v-if="checks.checktype == 1 || checks.checktype == 2 || checks.checktype == 3" placeholder="检查方法"></el-input>
				    </el-form-item>
				  </el-col>
				  <el-col :span="4">
				  	<el-button @click.prevent="removeDomain(domain)">删除</el-button>
				  </el-col>
				  </el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
				   <el-button type="primary" @click="submitCheckForm('checkForm')">提交</el-button>
				    <el-button @click="addDomain('checkForm')">新增</el-button>
				    <el-button @click="resetCheckForm('checkForm')">重置</el-button>
				 </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript">
var Incidentfication = function(obj) {
	
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
		this.personName = '';
		this.levelFk = '';
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
		this.levelFk = obj.levelFk;
		this.personName = obj.personName;
		this.riskIdentificationFk = obj.riskIdentificationFk;
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
Incidentfication.prototype = {
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

var Check = function(obj){
	if(!obj){
		this.checkTypeName = '';
		this.checkContent = '';
		this.checkMethod = '';
		this.key = Date.now();
		this.checkMonthFk = '';
	}else{
		this.checkTypeName = obj.checkTypeName;
		this.checkContent = obj.checkContent;
		this.checkMethod = obj.checkMethod;
		this.checkMonthFk = obj.checkMonthFk;
	}
}

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
		axios.get('/safety/riskDict/riskDictList',{params:{code:'postlist'}}).then(response=>{
			if(response.data.success === true){
				this.$data.topselect.postNames.data = [];
				response.data.data.forEach(e=>this.$data.topselect.postNames.data.push(e));
			}else{
				this.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
		axios.get('/safety/riskDict/riskDictList',{params:{code:'levellist'}}).then(response=>{
			if(response.data.success === true){
				this.$data.topselect.levelNames.data = [];
				response.data.data.forEach(e=>this.$data.topselect.levelNames.data.push(e));
			}else{
				this.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
	},
	data: function() {
		return {
			curRow:{},
			firstcol: '',
			dialogFormVisible: false,
			checkFormVisible: false,
			role:'${MEMBER_ROLE}',
			curData:{state:0},
			activeNames:['1'],
			form: new Incidentfication(),
			post_options: [],
			facotrs:[],
			troubles: [],
			levels:[],
			tableData: [],
			countMap:{
				vb:0,
				b:0,
				c:0,
				l:0
			},
			harmfulCountMap:{
				p:0,
				t:0,
				e:0,
				m:0
			},
			topselect:{
				orgs:{
					value:'${MEMBER_ORGID}',
					data:[]
				},
				date:'2019',
				postNames:{
					data:[],
					value:'全部'
				},
				levelNames:{
					data:[],
					value:'全部'
				}
			},
			checks:{
				checktype:0,
				width:'50%'
			},
			checkForm:{
	        	domains:[new Check()],
	        	typeList:[],
	        	id:''
	        },
	        tableHeight: window.innerHeight - 190
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
			formData.riskIdentificationFk = this.$data.curData.id;
			formData.orgFk = this.$data.topselect.orgs.value;
			this.$refs[formName].resetFields();
			var isNew=true,index,isAdd=false,unionIndex=0;
			if(formData.id){
				delete formData.index;
				delete formData.union;
				axios.put('/safety/riskIdentificationList/riskIdentificationList',formData).then(response=>{
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
				axios.post('/safety/riskIdentificationList/riskIdentificationList',formData).then(response=>{
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
			this.$data.form = new Incidentfication(row);
			this.$data.dialogFormVisible = true;
		},del(row){
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	        	axios.delete('/safety/riskIdentificationList/riskIdentificationList',{params:{id:row.id}}).then(response=>{
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
		addcheck(type,formName,row){
			this.$data.checks.checktype = type;
			this.$data.curRow = row;
			//this.$refs.singleTable.setCurrentRow(row);
			this.$data.checkFormVisible = true;
		},
		orgsChange(val){
//			alert(val);
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
			const loading = this.$loading({
	          lock: true,
	          text: 'Loading',
	          spinner: 'el-icon-loading',
	          background: 'rgba(0, 0, 0, 0.7)'
	        });
			var postName='',levelName='';
			if(this.$data.topselect.postNames.value !== '全部'){
				postName = this.$data.topselect.postNames.value;
			}
			if(this.$data.topselect.levelNames.value !== '全部'){
				levelName = this.$data.topselect.levelNames.value;
			}
			axios.get('/safety/riskIdentification/riskIdentification',{params:{year:this.$data.topselect.date,orgId:this.$data.topselect.orgs.value,postName:postName,levelName:levelName}}).then(response=>{
				loading.close();
				if(response.data.success === true){
					this.$data.curData.id = response.data.data.id;
					this.$data.curData.state = response.data.data.state;
					this.$data.tableData = [];
					this.$data.countMap = response.data.data.countMap;
					this.$data.harmfulCountMap = response.data.data.harmfulCountMap;
					response.data.data.riskIdentificationList.forEach(e=>this.$data.tableData.push(new Incidentfication(e)));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		},
		dialogFormOpen(){
			
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
			
		},
		dialogClose(formName){
			this.$data.form = new Incidentfication();
			this.$refs[formName].resetFields();
			this.resetCheckForm(formName);
		},
		changeDialogWidth(){
			return '20%';
		},
		checkFormOpen(){
			var that = this;
			axios.get('/safety/riskDict/riskDictList',{params:{code:'typelist'}}).then(response=>{
				if(response.data.success === true){
					that.$data.checkForm.typeList = [];
					response.data.data.forEach(e=>this.$data.checkForm.typeList.push(e));
				}else{
					that.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				that.$message.error('服务器异常，请稍后再试！');
			});
			var url= '';
	    	if(this.$data.checks.checktype == 1){
	    		url = '/safety/checkMonth/checkMonth';
	    	}
	    	if(this.$data.checks.checktype == 2){
	    		url = '/safety/checkWeek/checkWeek';
	    	}
	    	if(this.$data.checks.checktype == 3){
	    		url = '/safety/checkDay/checkDay';
	    	}
	    	if(this.$data.checks.checktype == 4){
	    		url = '/safety/checkSpecial/checkSpecial';
	    	}
	    	if(this.$data.checks.checktype == 5){
	    		url = '/safety/checkComprehensiveHoliday/checkComprehensiveHoliday';
	    	}
	    	if(this.$data.checks.checktype == 6){
	    		url = '/safety/checkComprehensiveSeason/checkComprehensiveSeason';
	    	}
			axios.get(url,{params:{year:new Date().getFullYear(),orgId:this.$data.topselect.orgs.value}}).then(response=>{
				if(response.data.success === true){
	    			that.$data.checkForm.id = response.data.data.id;
	    			
				}else{
					that.$message.warning(response.data.msg);
					that.$data.checkFormVisible =false;
				}
			}).catch(err=>{
				that.$message.warning(response.data.msg);
				that.$data.checkFormVisible =false;
			});
		},
		addDomain(formName) {
			this.$data.checkForm.domains.push({
				checkTypeName: '',
				checkContent:'',
				checkMethod:'',
				key: Date.now()
	        });
	    },
	    removeDomain(domain){
	    	var index = this.$data.checkForm.domains.indexOf(domain)
	        if (index !== -1) {
	          this.$data.checkForm.domains.splice(index, 1)
	        }
	    },
	    resetCheckForm(formName){
	    	this.$data.checkForm.domains = [];
	    	this.$data.checkForm.domains.push(new Check());
	    },
	    submitCheckForm(formName){
	    	var check ={orgFk:this.$data.topselect.orgs.value};
	    	var list = [];
	    	this.$data.checkForm.domains.forEach(e=>{
	    		var c = new Check(e);
	    		c.levelName = this.$data.curRow.levelName;
	    		if(this.$data.checks.checktype == 1){
	    			c.checkMonthFk = this.$data.checkForm.id;
		    	}
		    	if(this.$data.checks.checktype == 2){
		    		c.checkWeekFk = this.$data.checkForm.id;
		   
		    	}
		    	if(this.$data.checks.checktype == 3){
		    		c.checkDayFk = this.$data.checkForm.id;
		    	}
		    	if(this.$data.checks.checktype == 4){
		    		c.checkSpecialFk = this.$data.checkForm.id;
		    	}
		    	if(this.$data.checks.checktype == 5){
		    		c.content = c.checkContent;
		    		c.checkComprehensiveHolidayFk = this.$data.checkForm.id;
		    	}
		    	if(this.$data.checks.checktype == 6){
		    		c.content = c.checkContent;
		    		c.checkComprehensiveSeasonFk = this.$data.checkForm.id;
		    	}
	    		c.riskIdentificationListId = this.$data.curRow.id;
	    		list.push(c);
	    	});
	    	this.$refs[formName].resetFields();
	    	var url= '';
	    	if(this.$data.checks.checktype == 1){
	    		check.checkMonthList = list;
	    		url = '/safety/checkMonth/checkMonth';
	    	}
	    	if(this.$data.checks.checktype == 2){
	    		check.checkWeekList = list;
	    		url = '/safety/checkWeek/checkWeek';
	    	}
	    	if(this.$data.checks.checktype == 3){
	    		check.checkDayList = list;
	    		url = '/safety/checkDay/checkDay';
	    	}
	    	if(this.$data.checks.checktype == 4){
	    		check.checkSpecialList = list;
	    		url = '/safety/checkSpecial/checkSpecial';
	    	}
	    	if(this.$data.checks.checktype == 5){
	    		check.checkComprehensiveHolidayList = list;
	    		url = '/safety/checkComprehensiveHoliday/checkComprehensiveHoliday';
	    	}
	    	if(this.$data.checks.checktype == 6){
	    		check.checkComprehensiveSeasonList = list;
	    		url = '/safety/checkComprehensiveSeason/checkComprehensiveSeason';
	    	}
	    	var that = this;

			axios.post(url,check).then(response=>{
	    		if(response.data.success === true){
	    			that.checkFormVisible = false;
	    			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
	    	}).catch(err=>{
	    		that.$message.error('服务器异常，请稍后再试！');
	    	});
				
	    	
	    },openPrint(){
			var postName='',levelName='';
			if(this.$data.topselect.postNames.value !== '全部'){
				postName = this.$data.topselect.postNames.value;
			}
			if(this.$data.topselect.levelNames.value !== '全部'){
				levelName = this.$data.topselect.levelNames.value;
			}
			var url = "/safety/riskIdentification/riskIdentificationPrint?year="+this.$data.topselect.date+"&orgId="+this.$data.topselect.orgs.value+"&postName="+postName+"&levelName="+levelName+"";
			var enCodeUrl = encodeURI(encodeURI(url));
            window.open(enCodeUrl);
        }
	}
});
</script>
    <script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
    <script src="/node_modules/jquery/jquery.PrintArea.js"></script>
    <script>
        $(document).ready(function(){
            $("#print").click(function(){
                $("#divprint").printArea();
            });
        });
    </script>
</html>