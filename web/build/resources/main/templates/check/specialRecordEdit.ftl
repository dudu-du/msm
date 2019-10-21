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
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">专项检查</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-select placeholder="请选择" v-model="topselect.orgs.value">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
					</el-row>
					<el-row>
						<el-col :span="18">
							<span>排查时间：</span>
							 <el-date-picker
						      type="date"
						      v-mode="dateValue"
						      disabled
						      placeholder="选择日期">
						    </el-date-picker>
						</el-col>
						<el-col :span="6">
						<el-input placeholder="请输入内容" disabled>
						    <template slot="prepend">排查人员：</template>
						  </el-input>
						</el-col>
					</el-row>
					<el-table border header-align="center" :data="tableData" :span-method="arraySpanMethod"  style="width: 100%" ref="singleTable" :show-header="true">
						<el-table-column label="检查项目及相关要求" colspan="3">
							<el-table-column prop="checkTypeName" label="类型" v-show="false">
							</el-table-column>
							<el-table-column type="index">
							</el-table-column>
							<el-table-column prop="checkContent" label="内容">
							</el-table-column>
						</el-table-column>
						<el-table-column prop="levelName" label="符合性">
							<template slot-scope="scope">
						        <el-radio disabled v-model="scope.row.radio" label="1">是</el-radio>
  								<el-radio disabled v-model="scope.row.radio" label="2">否</el-radio>
						     </template>
						</el-table-column>
						<el-table-column label="操作" width="100px">
							<template slot-scope="scope">
						         <el-button type="danger" @click="del(scope.row)" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer>

				</el-footer>
			</el-container>
		</div>
	</body>
<script type="text/javascript" >
	new Vue({
		el:"#app",
		data:function(){
			return {
				curData:{state:1},
				topselect:{
					orgs:{
						value:'',
						data:[]
					}
				},
				tableData:[],
				dateValue:''
			};
		},
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
		methods:{
			search(){
				var date = new Date();
				var year = date.getFullYear();
				var that = this;
				that.$data.tableData = [];
				axios.get('/safety/checkSpecialRecord/checkSpecialRecord',{params:{year:year,orgId:this.topselect.orgs.value}}).then(response=>{
					if(response.data.success === true){

						that.$data.tableData = [];
						response.data.data.checkSpecialList.forEach(e=>{
							that.$data.tableData.push(e);
						});
					}else{
						that.$message.warning(response.data.msg);
					}
				}).catch(err=>{
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
		        	axios.delete('/safety/checkSpecialList/checkSpecialList',{params:{id:row.id}}).then(response=>{
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
			}
		}
	});
</script>

</html>