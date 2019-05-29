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

	<body>
		<div id="app">
			<el-container>
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">综合检查(节假日、复产前)</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
					<el-col :span="20">
						<el-select placeholder="请选择" v-model="topselect.orgs.value">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
					</el-col>
					<el-col :span="4" style="text-align:right;">
					   <el-button type="success" @click="submitForm">保存</el-button>
					</el-col>
					</el-row>
					<el-row>
						<el-col :span="18">
							<span>排查时间：</span>
							 <el-date-picker
						      type="daterange"
						      v-model="dateValue"
						      range-separator="至"
						      start-placeholder="开始日期"
						      end-placeholder="结束日期">
						    </el-date-picker>
						</el-col>
						<el-col :span="6">
						<el-input placeholder="请输入内容" v-model="inputValue">
						    <template slot="prepend">排查人员：</template>
						  </el-input>
						</el-col>
					</el-row>
					<!--
					<div style="width:100%" class="el-table el-table--fit el-table--border el-table--group el-table--enable-row-hover el-table--enable-row-transition">
						<div class="el-table__header-wrapper">
							<table class="el-table__header" style="width:100%;">
								<thead class="is-group has-gutter">
							        <tr>
							          <th rowspan="2" colspan="3" class="is-leaf">检查项目及相关要求</th>
							          <th rowspan="2" class="is-leaf">检查方法</th>
							          <th colspan="2" class="is-leaf">符合性</th>
							        </tr>
							        <tr>
							          <th class="is-leaf">是</th>
							          <th class="is-leaf">否</th>
							        </tr>
							      </thead>
							</table>
						
						</div>
					</div>
					--!>
					<el-table border header-align="center" :data="tableData" :span-method="arraySpanMethod"  style="width: 100%" ref="singleTable" :show-header="true">
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
				data:{},
				dateValue:'',
				inputValue:''
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
				axios.get('/safety/checkHolidayRecord/checkHolidayRecord',{params:{year:year,orgId:this.topselect.orgs.value}}).then(response=>{
					if(response.data.success === true){
						that.$data.data = response.data.data;
						that.$data.tableData=[];
						response.data.data.checkHolidayList.forEach(e=>{
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
		        	axios.delete('/safety/checkHolidayRecord/checkHolidayRecord',{params:{id:row.id}}).then(response=>{
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
				console.log(row);
			},
			submitForm(){
				this.$data.data.checkHolidayList = this.$data.tableData;
				var notify = false;
				for(var i=0;i<this.$data.tableData.length;i++){
					if(this.$data.tableData[i].result == 0){
						notify = true;
						break;
					}
				}
				axios.post('/safety/checkHolidayRecord/checkHolidayRecord',this.$data.data).then(response=>{
					if(response.data.success === true){
						this.$message.success(response.data.msg);
						if(notify){
							this.$notify({
					          title: '警告',
					          message: '请去未合格检查页面填写清单台账',
					          type: 'warning'
					        });
						}
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