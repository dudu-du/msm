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
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-col :span="8">
							<el-select placeholder="请选择" v-model="defaultType">
							    <el-option
							      v-for="item in typeList"
							      :key="item.code"
							      :label="item.codeName"
							      :value="item.code">
							    </el-option>
							  </el-select>
							  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button></el-col>
						<el-col :span="8" s>&nbsp;</el-col>
						<el-col :span="8" style="text-align:right;"><el-button @click="dialogFormVisible=true" circle type="success" icon="el-icon-plus" ></el-button></el-col>
					</el-row>
					<el-table border resizable align="center" style="width: 100%" ref="singleTable" :data="data" >
						<el-table-column type="index" label="序号" width="100px"></el-table-column>
						<el-table-column prop="name" label="名称">
						</el-table-column>
						<el-table-column prop="codeName" label="类型" align="center">
						</el-table-column>
						<el-table-column prop="measure" label="操作" align="center">
							 <template slot-scope="scope">
						        <el-button type="primary" icon="el-icon-edit" circle @click="dialogFormVisible=true;edit(scope.row)"></el-button>
						        <el-button type="danger" icon="el-icon-delete" circle @click="del(scope.row)"></el-button>
						      </template>
						</el-table-column>
					</el-table>
				</el-main>
			</el-container>
			<el-dialog title="新增" :visible.sync="dialogFormVisible" @open="dialogOpen" @closed="dialogClosed('addForm')">
			  <el-form :model="form" ref="addForm" label-width="120px">
			    <el-form-item label="名称" prop="name">
			      <el-input v-model="form.name"  autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="类型" prop="type">
			      <el-select v-model="form.code" placeholder="类型">
			        <el-option v-for="item in typeList" :label="item.codeName" :value="item.code" :key="item.code"></el-option>
			      </el-select>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript">
new Vue({
	el:"#app",
	data:function(){
		return {
			dialogFormVisible:false,
			form:{
				name:'',
				code:'',
				codeName:'',
				id:'',
				value:''
			},
			data:[],
			typeList:[],
			defaultType:''
		};
	},
	created:function(){
		var that = this;
		axios.get('/safety/riskDict/riskDictCodeList').then(response=>{
			if(response.data.success === true){
				that.$data.typeList=[];
				if(response.data.data.length >0){
					that.$data.defaultType = response.data.data[0].code;
				}
				response.data.data.forEach(e=>{
					that.$data.typeList.push(e);
				});
				that.search();
			}else{
				that.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
		this.search();
	},
	methods:{
		dialogOpen(){
			
		},
		dialogClosed(formName){
			this.$data.form = {name:'',value:'',code:'',codeName:''};
			this.$refs[formName].resetFields();
		},
		del(row){
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	        	axios.delete('/safety/riskDict/riskDict',{params:{id:row.id}}).then(response=>{
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
		edit(row){
			this.$data.form = row;
			
		},
		submitForm(){
			this.$data.dialogFormVisible = false;
			
			if(this.$data.form.id){
				axios.put('/safety/riskDict/riskDict',this.$data.form).then(response=>{
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
				axios.post('/safety/riskDict/riskDict',this.$data.form).then(response=>{
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
		},
		search(){
			
			axios.get('/safety/riskDict/riskDictList',{params:{code:this.$data.defaultType}}).then(response=>{
				if(response.data.success === true){
					this.$data.data = [];
					response.data.data.forEach(e=>this.$data.data.push(e));
				}else{
					this.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
		}
	}
});
</script>
</html>