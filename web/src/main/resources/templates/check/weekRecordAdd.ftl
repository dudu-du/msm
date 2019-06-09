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
	</head>

	<body>
		<div id="app">
			<el-container>
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患排查治理周排查记录</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
					<el-col :span="20">
						<el-select placeholder="请选择" v-model="topselect.orgs.value" v-if="role=='ROLE_SUPERADMIN'">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search" v-if="role=='ROLE_SUPERADMIN'">搜索</el-button>&nbsp;
					</el-col>
					<el-col :span="4" style="text-align:right;">
					   <el-button type="success" @click="submitForm">保存</el-button>
					</el-col>
					</el-row>
					<el-row>
						<el-col :span="18">
							<span>排查时间：</span>
							 <el-date-picker
						      type="date"
						      v-model="dateValue"
						      value-format="yyyy-MM-dd"
						      placeholder="选择日期">
						    </el-date-picker>
						</el-col>
						<el-col :span="6">
						<el-input placeholder="请输入内容" v-model="inputValue">
						    <template slot="prepend">排查人员：</template>
						  </el-input>
						</el-col>
					</el-row>
					<el-table border :max-height="tableHeight" header-align="center" :data="tableData" :span-method="arraySpanMethod"  style="width: 100%" ref="singleTable" :show-header="true">
						<el-table-column label="检查项目及相关要求" colspan="3">
							<el-table-column prop="checkTypeName" label="类型" v-show="false">
							</el-table-column>
							<el-table-column type="index">
							</el-table-column>
							<el-table-column prop="checkContent" label="内容">
							</el-table-column>
						</el-table-column>
						<el-table-column prop="checkMethod" label="检查方法">
						</el-table-column>
						<el-table-column prop="levelName" label="符合性">
							<template slot-scope="scope">
								<el-radio-group v-model="scope.row.result" @change="change(scope.row)">
							        <el-radio label="1">是</el-radio>
	  								<el-radio label="0">否</el-radio>
	  							</el-radio-group>
	  							<span v-if="scope.row.result==0" style="margin-left:5px;">
		  							<el-tooltip class="item" effect="dark" content="清单" placement="top-start">
							        <el-button style="margin-left:0" @click="addBtn(scope.$index,scope.row,'firstForm')" type="primary" size="mini" icon="el-icon-plus" circle></el-button>
							        </el-tooltip>
							        <el-tooltip class="item" effect="dark" content="台账" placement="top-start">
							        <el-button style="margin-left:0" @click="addDanger(scope.$index,scope.row,'secondForm')" type="warning" size="mini" icon="el-icon-plus" circle></el-button>
							        </el-tooltip>
							        <el-tooltip class="item" effect="dark" content="回执" placement="top-start">
							        <el-button style="margin-left:0" @click="addReceipt(scope.$index,scope.row,'threeForm')" type="danger" size="mini" icon="el-icon-plus" circle></el-button>
							        </el-tooltip>
	  							</span>
						     </template>
						</el-table-column>
						<el-table-column label="操作" width="100px" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'">
							<template slot-scope="scope">
						         <el-button type="danger" @click="del(scope.row)" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer>
				<div>
						注：周排查由安全管理副职组织并实施。
					</div>
				</el-footer>
			</el-container>
			<el-dialog title="隐患排查清单" :visible.sync="dialogFormVisible" ref="dialogForm" @closed="closedDialog('firstForm')">
			  <el-form :model="listForm" label-width="120px" ref="firstForm">
			    <el-form-item label="风险部位" prop="riskPosition">
			      <el-input v-model="listForm.riskPosition" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="风险因素" prop="harmfulFactors">
			      <el-input v-model="listForm.harmfulFactors" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="风险管控措施" prop="measure">
			      <el-input v-model="listForm.measure" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="措施失控表现" prop="runawayPerformance">
			      <el-input v-model="listForm.runawayPerformance" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="管控部门" prop="controlOrgName">
			      <el-input v-model="listForm.controlOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="管控责任人" prop="controlOrgPersonName">
			      <el-input v-model="listForm.controlOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查部门" prop="investigationOrgName">
			      <el-input v-model="listForm.investigationOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查责任人" prop="investigationOrgPersonName">
			      <el-input v-model="listForm.investigationOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查频次" prop="investigationCount">
			      <el-input v-model="listForm.investigationCount" type="number" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="备注" prop="remark">
			      <el-input v-model="listForm.remark" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitListForm('firstForm')">确 定</el-button>
			  </div>
			</el-dialog>
			<el-dialog title="隐患治理信息台账" :visible.sync="dangerLedgerVisible" ref="dangerLedger" @closed="closedDialog('secondForm')">
			  <el-form :model="dangerForm" label-width="120px" ref="secondForm">
			    <el-form-item label="排查时间" prop="investigationTime">
			      	<el-date-picker
				  	  value-format="yyyy-MM-dd"
				      type="date"
				      v-model="dangerForm.investigationTime"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="排查人" prop="investigationOrgPersonName">
			      <el-input v-model="dangerForm.investigationOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患部位" prop="rectificationPosition">
			    	<el-input v-model="dangerForm.rectificationPosition" autocomplete="off"></el-input>
			    	<el-upload
			    		v-model="dangerForm.rectificationPositionUrl"
			    		limit="1"
			    		ref="rectificationPositionUrl"
					  action=""
					  :auto-upload="false"
					  :on-change="rectificationChange"
					  list-type="picture-card">
					  <i class="el-icon-plus"></i>
					</el-upload>
			    </el-form-item>
			    <el-form-item label="隐患名称" prop="rectificationName">
			      <el-input v-model="dangerForm.rectificationName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患等级" prop="rectificationLevel">
			    	 <el-select placeholder="请选择" v-model="dangerForm.rectificationLevel">
					    <el-option key="1" value="一般" name="一般"></el-option>
					    <el-option key="2" value="重大" name="重大"></el-option>
					    <el-option key="3" value="其它" name="其它"></el-option>
					  </el-select>
			    </el-form-item>
			    <el-form-item label="治理措施" prop="governmentMeasure">
			      <el-input v-model="dangerForm.governmentMeasure" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="完成时限" prop="complateTime">
			      <el-input v-model="dangerForm.complateTime" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任部门" prop="controlOrgName">
			      <el-input v-model="dangerForm.controlOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任人" prop="controlOrgPersonName">
			      <el-input v-model="dangerForm.controlOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="复查时间" prop="reviewTime">
			      	<el-date-picker
				      type="date"
				      v-model="dangerForm.reviewTime"
				      value-format="yyyy-MM-dd"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="复查人" prop="reviewPersonName">
			      <el-input v-model="dangerForm.reviewPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="复查结果" prop="reviewResult">
			    	<el-input v-model="dangerForm.reviewResult" autocomplete="off"></el-input>
			    	<el-upload
			    		v-model="dangerForm.reviewResultUrl"
			    		limit="1"
			    		ref="reviewResultUrl"
					  action=""
					  :on-change="reviewChange"
					  :auto-upload="false"
					  list-type="picture-card">
					  <i class="el-icon-plus"></i>
					</el-upload>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dangerLedgerVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitDangerForm('secondForm')">确 定</el-button>
			  </div>
			</el-dialog>
			<el-dialog title="隐患整改回执单" :visible.sync="receiptVisible" ref="receipt" @closed="closedDialog('threeForm')">
			  <el-form :model="receiptForm" label-width="150px" ref="threeForm">
			    <el-form-item label="受检单位名称" prop="checkOrgName">
			      <el-input v-model="receiptForm.checkOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="填写时间" >
			      	<el-date-picker
				  	  value-format="yyyy-MM-dd"
				      type="date"
				      v-model="receiptForm.fillTime"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="检查人员姓名" prop="checkPersonName">
			      <el-input v-model="receiptForm.checkPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="检查日期">
			      	<el-date-picker
				      type="date"
				      v-model="receiptForm.checkTime"
				      value-format="yyyy-MM-dd"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="编号" prop="checkCode">
			      <el-input v-model="receiptForm.checkCode" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患整改部门名称" prop="rectificationOrgName">
			      <el-input v-model="receiptForm.rectificationOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="整改部门负责人姓名" prop="rectificationPersonName">
			      <el-input v-model="receiptForm.rectificationPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患内容及整改要求" prop="rectificationContent">
			      <el-input v-model="receiptForm.rectificationContent" autocomplete="off"></el-input>
			      <el-upload
			    		v-model="receiptForm.rectificationContentUrl"
			    		limit="1"
			    		ref="rectificationContentUrl"
					  action=""
					  :on-change="rectificationContentUrlChange"
					  :auto-upload="false"
					  list-type="picture-card">
					  <i class="el-icon-plus"></i>
					</el-upload>
			    </el-form-item>
			    <el-form-item label="整改期限" prop="rectificationTime">
			      	<el-date-picker
				      type="date"
				      v-model="receiptForm.rectificationTime"
				      value-format="yyyy-MM-dd"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="整改措施" prop="rectificationMeasure">
			      <el-input v-model="receiptForm.rectificationMeasure" autocomplete="off"></el-input>
			    </el-form-item>
			    
			    <el-form-item label="整改结果" prop="rectificationResult">
			      <el-input v-model="receiptForm.rectificationResult" autocomplete="off"></el-input>
			      <el-upload
			    		v-model="receiptForm.rectificationResultUrl"
			    		limit="1"
			    		ref="rectificationResultUrl"
					  action=""
					  :on-change="rectificationResultUrlChange"
					  :auto-upload="false"
					  list-type="picture-card">
					  <i class="el-icon-plus"></i>
					</el-upload>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="receiptVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitReceiptForm('threeForm')">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript" >
	new Vue({
		el:"#app",
		data:function(){
			return {
				role:'${MEMBER_ROLE}',
				curData:{state:1},
				topselect:{
					orgs:{
						value:'${MEMBER_ORGID}',
						data:[]
					}
				},
				tableHeight: window.innerHeight - 270,
				tableData:[],
				data:{},
				dateValue:'',
				inputValue:'${MEMBER_USER_REAL_NAME}',
				dialogFormVisible:false,
	            dangerLedgerVisible:false,
	            receiptVisible:false,
	            listForm:{
	            	riskPosition:'',
	            	harmfulFactors:'',
	            	measure:'',
	            	runawayPerformance:'',
	            	controlOrgName:'${MEMBER_ORGNAME}',
	            	controlOrgPersonName:'',
	            	investigationOrgName:'${MEMBER_ORGNAME}',
	            	investigationOrgPersonName:'${Session.MEMBER_USER_PERSON.realname}',
	            	investigationCount:'',
	            	remark:''
	            },
	            dangerForm:{
	            	investigationTime:'',
	            	investigationOrgPersonName:'${Session.MEMBER_USER_PERSON.realname}',
	            	rectificationPosition:'',
	            	rectificationName:'',
	            	rectificationLevel:'',
	            	governmentMeasure:'',
	            	rectificationPositionUrl:'',
	            	complateTime:'',
	            	controlOrgName:'${MEMBER_ORGNAME}',
	            	controlOrgPersonName:'',
	            	reviewTime:'',
	            	reviewResultUrl:'',
	            	reviewPersonName:'${Session.MEMBER_USER_PERSON.realname}',
	            	offgradeListFk:'',
	            	checkType:'',
	            	reviewResult:''
	            },
	            receiptForm:{
	            	checkOrgName:'${MEMBER_ORGNAME}',
	            	fillTime:'',
	            	checkPersonName:'${Session.MEMBER_USER_PERSON.realname}',
	            	checkTime:'',
	            	checkCode:'',
	            	rectificationOrgName:'',
	            	rectificationPersonName:'',
	            	rectificationContent:'',
	            	rectificationContentUrl:'',
	            	rectificationTime:'',
	            	rectificationMeasure:'',
	            	rectificationResult:'',
	            	rectificationResultUrl:''
	            }
			};
		},
		created:function(){
			this.$data.dangerForm.investigationTime = this.getDate(new Date());
	    	this.$data.dangerForm.reviewTime = this.getDate(new Date());
	    	this.$data.dangerForm.investigationTime = this.getDate(new Date());
	
			this.$data.receiptForm.fillTime = this.getDate(new Date());
			this.$data.receiptForm.checkTime = this.getDate(new Date());
			this.$data.receiptForm.rectificationTime = this.getDate(new Date());
			
			this.$data.dateValue = this.getDate(new Date());
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
		methods:{
			search(){
				const loading = this.$loading({
		          lock: true,
		          text: 'Loading',
		          spinner: 'el-icon-loading',
		          background: 'rgba(0, 0, 0, 0.7)'
		        });
				var date = new Date();
				var year = date.getFullYear();
				var that = this;
				axios.get('/safety/checkWeekRecord/checkWeekRecord',{params:{year:year,orgId:this.topselect.orgs.value}}).then(response=>{
					loading.close();
					sessionStorage.setItem("noResult", 0);
					if(response.data.success === true){
						that.$data.data = response.data.data;

						that.$data.tableData = [];
						response.data.data.checkWeekList.forEach(e=>{
							that.$data.tableData.push(e);
						});
					}else{
						that.$message.warning(response.data.msg);
					}
				}).catch(err=>{
					loading.close();
					this.$message.error('服务器异常，请稍后再试！');
				});
			},
			arraySpanMethod({ row, column, rowIndex, columnIndex }){
				if(columnIndex === 0) {
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
			del(row){
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		        	axios.delete('/safety/checkWeekRecord/checkWeekRecord',{params:{id:row.id}}).then(response=>{
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
			change(row){
				var noResult = ~~sessionStorage.getItem("noResult");
				if(row.result==1){
					row.checkDangerCheckList = null;
					row.checkDangerLedger = null;
					row.checkRectificationReceipt = null;
					if(noResult>0){
						noResult--;
					}
				}else{
					noResult++;
				}
				sessionStorage.setItem("noResult", noResult);
			},
			submitForm(){
				const loading = this.$loading({
		          lock: true,
		          text: 'Loading',
		          spinner: 'el-icon-loading',
		          background: 'rgba(0, 0, 0, 0.7)'
		        });
				this.$data.data.checkStartTime = this.$data.dateValue;
				this.$data.data.checkPersonName = this.$data.inputValue;
				var notify = false;
				for(var i=0;i<this.$data.tableData.length;i++){
					if(this.$data.tableData[i].result == 0){
						notify = true;
						break;
					}
				}
				axios.post('/safety/checkWeekRecord/checkWeekRecord',this.$data.data).then(response=>{
					loading.close();
					if(response.data.success === true){
						this.$message.success(response.data.msg);
						if(notify){
							this.$notify({
					          title: '警告',
					          message: '请去未合格检查页面填写清单台账',
					          type: 'warning'
					        });
						}
						this.search();
					}else{
						loading.close();
						this.$message.warning(response.data.msg);
					}
				}).catch(err=>{
					this.$message.error('服务器异常，请稍后再试！');
				});
			},getDate(date){
				var year = date.getFullYear();
				var month = date.getMonth()+1;
				var day = date.getDate();
				if(month < 10){
					month = '0'+month;
				}
				if(day<10){
					day = '0' + day;
				}
				return year + '-' + month + '-' + day;
			},
			addBtn(index,row,formName){
	        	this.$data.dialogFormVisible = true;
	        	this.$data.listForm.offgradeListFk = row.id;
	        	this.$data.listForm.orgFk = row.orgFk;
	        	this.$data.listForm.checkType = row.checkType;
	        	this.$data.listForm.index = index;
	        	this.$data.listForm.harmfulFactors = row.riskIdentificationList.harmfulFactors;
	        	this.$data.listForm.measure = row.riskIdentificationList.measure;
	        	this.$data.listForm.riskPosition = row.riskIdentificationList.incidence;
	        	this.$data.listForm.runawayPerformance = row.riskIdentificationList.consequence;
	        	this.$data.listForm.controlOrgPersonName = row.riskIdentificationList.personName;
	        },
	        addDanger(index,row,formName){
	        	this.$data.dangerLedgerVisible = true;
	        	this.$data.dangerForm.orgFk = row.orgFk;
	        	this.$data.dangerForm.offgradeListFk = row.id;
	        	this.$data.dangerForm.checkType = row.checkType;
	        	this.$data.dangerForm.index = index;
	        	this.$data.dangerForm.rectificationPosition = row.riskIdentificationList.incidence;
	        	this.$data.dangerForm.governmentMeasure = row.riskIdentificationList.measure;
	        	this.$data.dangerForm.controlOrgPersonName = row.riskIdentificationList.personName;
	        },
	        closedDialog(formName){
	        	if(this.$refs['rectificationPositionUrl']){
		       		this.$refs['rectificationPositionUrl'].clearFiles();
		       		this.$refs['reviewResultUrl'].clearFiles();
	        	}
	        	if(this.$refs['rectificationContentUrl']){
		       		this.$refs['rectificationContentUrl'].clearFiles();
		       		this.$refs['rectificationResultUrl'].clearFiles();
	        	}
	        	this.$refs[formName].resetFields();
	        },
	        submitListForm(formName){
	        	var that = this;
	        	this.$data.dialogFormVisible = false;
	        	this.$data.data.checkWeekList[this.$data.listForm.index].checkDangerChecklist = JSON.parse(JSON.stringify(this.$data.listForm));
	        	this.$refs[formName].resetFields();
				this.$data.listForm.offgradeListFk = '';
	        	
	        },
	        submitDangerForm(formName){
	        	this.$data.dangerLedgerVisible = false;
	        	this.$data.data.checkWeekList[this.$data.dangerForm.index].checkDangerLedger = JSON.parse(JSON.stringify(this.$data.dangerForm));
		        this.$refs[formName].resetFields();
				this.$data.dangerForm.offgradeListFk = '';
	        },
	        submitReceiptForm(formName){
	        	var that = this;
	        	this.$data.dangerLedgerVisible = false;
	        	this.$data.data.checkWeekList[this.$data.receiptForm.index].checkRectificationReceipt = JSON.parse(JSON.stringify(this.$data.receiptForm));
	        	this.$refs[formName].resetFields();
				this.$data.receiptForm.offgradeListFk = '';
				this.$data.receiptVisible = false;
	        	
	        },
	        rectificationChange(file,fileList){
	        	var that = this;
	        	var reader = new FileReader();
	        	reader.readAsDataURL(file.raw);
	        	reader.onload = function(e){
	        		var imgCode = e.target.result;
	        		that.$data.dangerForm.rectificationPositionUrl = imgCode;
	        	}
	        },
	        reviewChange(file,fileList){
	        	var that = this;
	        	var reader = new FileReader();
	        	reader.readAsDataURL(file.raw);
	        	reader.onload = function(e){
	        		var imgCode = e.target.result;
	        		that.$data.dangerForm.reviewResultUrl = imgCode;
	        	}
	        },
	        rectificationContentUrlChange(file,fileList){
	        	var that = this;
	        	var reader = new FileReader();
	        	reader.readAsDataURL(file.raw);
	        	reader.onload = function(e){
	        		var imgCode = e.target.result;
	        		that.$data.receiptForm.rectificationContentUrl = imgCode;
	        	}
	        },
	        rectificationResultUrlChange(file,fileList){
	        	var that = this;
	        	var reader = new FileReader();
	        	reader.readAsDataURL(file.raw);
	        	reader.onload = function(e){
	        		var imgCode = e.target.result;
	        		that.$data.receiptForm.rectificationResultUrl = imgCode;
	        	}
	        },
			addReceipt(index,row,formName){
				this.$data.receiptVisible = true;
	        	this.$data.receiptForm.orgFk = row.orgFk;
	        	this.$data.receiptForm.offgradeListFk = row.id;
	        	this.$data.receiptForm.checkType = row.checkType;
				this.$data.receiptForm.index = index;
			}
		}
	});
</script>

</html>