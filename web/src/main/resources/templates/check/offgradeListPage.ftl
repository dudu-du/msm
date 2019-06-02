<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="//at.alicdn.com/t/font_1205992_y6fcnyw4tpf.css">
		<link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
		<script src="/Public/js/vue.min.js"></script>
		<script src="/Public/js/axios.min.js"></script>
		<script src="https://unpkg.com/element-ui/lib/index.js"></script>
	</head>
	<style>
		.el-table th{
			text-align: center;
		}
		/*.el-button{*/
		/*	margin-left: 152px;*/
		/*}*/
	</style>

	<body>
		<div id="app">
			<el-container>
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">未合格项记录列表</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-table border style="width: 100%" ref="singleTable" :data="data.list" >
						<el-table-column type="index" label="序号" align="center"></el-table-column>
						<el-table-column prop="checkType" label="来源" align="center">
						</el-table-column>
						<el-table-column prop="content" label="内容" align="center">
						</el-table-column>
						<el-table-column prop="levelName" label="风险等级" align="center">
						</el-table-column>
						<el-table-column prop="levelNum" label="风险等级" align="center" v-if="false">
						</el-table-column>
						<el-table-column prop="createTime" label="创建时间" align="center">
						</el-table-column>
						<el-table-column label="操作" align="center">
							<template slot-scope="scope">

<#--								<el-button  @click="" type="primary" size="mini" icon="el-icon-search" circle></el-button>-->

								<el-tooltip class="item" effect="dark" content="清单" placement="top-start">
						        <el-button style="margin-left:0" @click="addBtn(scope.row,'firstForm')" type="primary" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						        <el-tooltip class="item" effect="dark" content="台账" placement="top-start">
						        <el-button style="margin-left:0" @click="addDanger(scope.row,'secondForm')" type="warning" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						        <el-tooltip class="item" effect="dark" content="回执" placement="top-start">
						        <el-button style="margin-left:0" @click="addReceipt(scope.row,'threeForm')" type="danger" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer style="text-align:center">
						<el-pagination
						  background
						  layout="prev, pager, next"
						  :total="page.total" :page-size="page.pageSize" :current-page.sync="curPage">
						</el-pagination>
				</el-footer>
			</el-container>
			<el-dialog title="隐患排查清单" :visible.sync="dialogFormVisible" ref="dialogForm" @closed="closedDialog('firstForm')">
			  <el-form :model="form" label-width="120px" ref="firstForm">
			    <el-form-item label="风险部位">
			      <el-input v-model="form.riskPosition" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="风险因素">
			      <el-input v-model="form.harmfulFactors" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="风险管控措施">
			      <el-input v-model="form.measure" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="措施失控表现">
			      <el-input v-model="form.runawayPerformance" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="管控部门">
			      <el-input v-model="form.controlOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="管控责任人">
			      <el-input v-model="form.controlOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查部门">
			      <el-input v-model="form.investigationOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查责任人">
			      <el-input v-model="form.investigationOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="排查频次">
			      <el-input v-model="form.investigationCount" type="number" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="备注">
			      <el-input v-model="form.remark" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('firstForm')">确 定</el-button>
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
<script type="text/javascript">



new Vue({
    el:'#app',
    components: {axios},
    created:function(){
    	this.$data.dangerForm.investigationTime = this.getDate(new Date());
    	this.$data.dangerForm.reviewTime = this.getDate(new Date());
    	this.$data.dangerForm.investigationTime = this.getDate(new Date());

		this.$data.receiptForm.fillTime = this.getDate(new Date());
		this.$data.receiptForm.checkTime = this.getDate(new Date());
		this.$data.receiptForm.rectificationTime = this.getDate(new Date());
		
        var that = this;
        axios.get('/safety/checkOffgradeList/checkOffgradeListByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
        }).catch(err=>{
            this.$message.error('服务器异常，请稍后再试！');
    });
    },
    data:function(){
        return{
        	curPage:1,
			page:{
				total:0,
				pageSize:10
			},
            data:[],
            dialogFormVisible:false,
            dangerLedgerVisible:false,
            receiptVisible:false,
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
            },
            dangerForm:{
            	investigationTime:'',
            	investigationOrgPersonName:'',
            	rectificationPosition:'',
            	rectificationName:'',
            	rectificationLevel:'',
            	governmentMeasure:'',
            	rectificationPositionUrl:'',
            	complateTime:'',
            	controlOrgName:'',
            	controlOrgPersonName:'',
            	reviewTime:'',
            	reviewResultUrl:'',
            	reviewPersonName:'',
            	offgradeListFk:'',
            	checkType:''
            },
            receiptForm:{
            	checkOrgName:'',
            	fillTime:'',
            	checkPersonName:'',
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
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkOffgradeList/checkOffgradeListByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkOffgradeList/checkOffgradeListByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
        },
        addBtn(row,formName){
        	this.$data.dialogFormVisible = true;
        	this.$data.form.offgradeListFk = row.id;
        	this.$data.form.orgFk = row.orgFk;
        	this.$data.form.checkType = row.checkType;
        },
        addDanger(row,formName){
        	this.$data.dangerLedgerVisible = true;
        	this.$data.dangerForm.orgFk = row.orgFk;
        	this.$data.dangerForm.offgradeListFk = row.id;
        	this.$data.dangerForm.checkType = row.checkType;
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
        submitForm(formName){
        	var that = this;
        	this.$data.dialogFormVisible = false;
        	this.$refs[formName].resetFields();
        	axios.post('/safety/checkDangerChecklist/checkDangerChecklist',this.$data.form).then(function(response){
				that.$data.form.offgradeListFk = '';
        		if(response.data.success === true){
        			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        },
        submitDangerForm(formName){
        	var that = this;
        	this.$data.dangerLedgerVisible = false;
        	axios.post('/safety/checkDangerLedger/checkDangerLedger',this.$data.dangerForm).then(function(response){
	        	that.$refs[formName].resetFields();
				that.$data.dangerForm.offgradeListFk = '';
				console.log(response.data);
        		if(response.data.success === true){
        			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        },
        submitReceiptForm(formName){
        	var that = this;
        	this.$data.dangerLedgerVisible = false;
        	axios.post('/safety/checkRectificationReceipt/checkRectificationReceipt',this.$data.receiptForm).then(function(response){
	        	that.$refs[formName].resetFields();
				that.$data.receiptForm.offgradeListFk = '';
				that.$data.receiptVisible = false;
        		if(response.data.success === true){
        			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
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
        getDate(date){
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
		addReceipt(row,formName){
			this.$data.receiptVisible = true;
        	this.$data.receiptForm.orgFk = row.orgFk;
        	this.$data.receiptForm.offgradeListFk = row.id;
        	this.$data.receiptForm.checkType = row.checkType;
			
		},
		watch:{
			curPage(val){
				console.log(val);
			}
		}
    }

});
</script>

</html>