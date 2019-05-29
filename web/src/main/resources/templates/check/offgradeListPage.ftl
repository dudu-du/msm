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
						<el-table-column type="index" label="序号" width="230" align="center"></el-table-column>
						<el-table-column prop="checkType" label="来源" width="240" align="center">
						</el-table-column>
						<el-table-column prop="content" label="内容" width="240" align="center">
						</el-table-column>
						<el-table-column prop="levelName" label="风险等级名称" width="240" align="center">
						</el-table-column>
						<el-table-column prop="levelNum" label="风险等级" width="240" align="center">
						</el-table-column>
						<el-table-column prop="createTime" label="创建时间" width="240" align="center">
						</el-table-column>
						<el-table-column label="操作" width="232" align="center">
							<template slot-scope="scope">
								<!--
								<el-button  @click="" type="primary" size="mini" icon="el-icon-search" circle></el-button>
								--!>
								<el-tooltip class="item" effect="dark" content="清单" placement="top-start">
						        <el-button style="margin-left:0" @click="addBtn(scope.row,'firstForm')" type="primary" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						        <el-tooltip class="item" effect="dark" content="台账" placement="top-start">
						        <el-button style="margin-left:0" @click="addDanger(scope.row,'secondForm')" type="warning" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						        <el-tooltip class="item" effect="dark" content="回执" placement="top-start">
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-plus" circle></el-button>
						        </el-tooltip>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer style="text-align:center">
						<el-button-group>
							<el-button type="primary" v-model="data" @click="prev(data.prePage,10)" icon="el-icon-arrow-left">上一页</el-button>
							<el-button type="primary" v-model="data" @click="next(data.nextPage,10)">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
						</el-button-group>
				</el-footer>
			</el-container>
			<el-dialog title="隐患排查清单" :visible.sync="dialogFormVisible" ref="dialogForm" @closed="closedDialog('firstForm')">
			  <el-form :model="form" label-width="120px" ref="firstForm">
			    <el-form-item label="风险部位" prop="riskPosition">
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
			  <el-form :model="form" label-width="120px" ref="secondForm">
			    <el-form-item label="排查时间" >
			      	<el-date-picker
				  
				      type="datetime"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="排查人">
			      <el-input v-model="form.harmfulFactors" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患部位">
			      <el-input v-model="form.measure" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患名称">
			      <el-input v-model="form.runawayPerformance" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="隐患等级">
			      <el-input v-model="form.controlOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="治理措施">
			      <el-upload
					  action="https://jsonplaceholder.typicode.com/posts/"
					  list-type="picture-card"
					  :auto-upload="false">
					  <i class="el-icon-plus"></i>
					</el-upload>
			    </el-form-item>
			    <el-form-item label="完成时限">
			      <el-input v-model="form.investigationOrgName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任部门">
			      <el-input v-model="form.investigationOrgPersonName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任人">
			      <el-input v-model="form.investigationCount" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="复查时间">
			      	<el-date-picker
				
				      type="datetime"
				      placeholder="选择日期时间">
				    </el-date-picker>
			    </el-form-item>
			    <el-form-item label="复查人">
			      <el-input v-model="form.remark" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="复查结果">
			      <el-input v-model="form.remark" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dangerLedgerVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('secondForm')">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript">



new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkOffgradeList/checkOffgradeListByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
        }).catch(err=>{
            this.$message.error('服务器异常，请稍后再试！');
    });
    },
    data:function(){
        return{
            data:[],
            dialogFormVisible:false,
            dangerLedgerVisible:false,
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
        	
        },
        addDanger(row,formName){
        	this.$data.dangerLedgerVisible = true;
        	
        },
        closedDialog(formName){
       
        	this.$refs[formName].resetFields();
        },
        submitForm(formName){
        	this.$data.dialogFormVisible = false;
        	this.$refs[formName].resetFields();
        	axios.post('/safety/checkDangerChecklist/checkDangerChecklist',this.$data.form).then(function(response){
        		this.$data.form.offgradeListFk = '';
        		if(response.data.success === true){
        			that.$message.success(response.data.msg);
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
        }
    }

});
</script>

</html>