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
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-col :span="22">&nbsp;</el-col>
						<el-col :span="2" style="padding-left:58px" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'">
							<el-upload :show-file-list="false" action="/safety/file/upload" :data="{type:${fileType}}" :on-success="uploadSuccess" :on-error="uploadError">
							  <el-button size="small" type="primary">点击上传</el-button>
							</el-upload>
						</el-col>
					</el-row>
					<el-table border :data="tableData" style="width: 100%" ref="singleTable">
						<el-table-column type="index" label="序号" width="60"></el-table-column>
						<el-table-column prop="name" label="文件名" width="150">
							<template slot-scope="scope">
								<el-link :href="'/file/'+scope.row.name" target="_blank">{{scope.row.name}}</el-link>
							</template>
						</el-table-column>
						<el-table-column prop="createTime" label="创建时间">
							<template slot-scope="scope">
						        {{scope.row.createTime[0]}}年{{scope.row.createTime[1]}}月{{scope.row.createTime[2]}}日&nbsp;{{scope.row.createTime[3]}}:{{scope.row.createTime[4]}}
						     </template>
						</el-table-column>
						<el-table-column prop="modifyTime" label="修改时间">
							<template slot-scope="scope">
						        {{scope.row.modifyTime[0]}}年{{scope.row.modifyTime[1]}}月{{scope.row.modifyTime[2]}}日&nbsp;{{scope.row.modifyTime[3]}}:{{scope.row.modifyTime[4]}}
						     </template>
						</el-table-column>
						<el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
						<el-table-column label="操作" width="140px" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'">
							<template slot-scope="scope">
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
			</el-container>
			
		</div>
	</body>
<script type="text/javascript">
	new Vue({
		el:'#app',
		created:function(){
			this.search();
		},
		data:function(){
			return {
				curType:${fileType},
				role:'${MEMBER_ROLE}',
				tableData:[]
			};
		},
		methods:{
			del(row){
				this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
		          confirmButtonText: '确定',
		          cancelButtonText: '取消',
		          type: 'warning'
		        }).then(() => {
		        	axios.delete('/safety/file/fileById',{params:{id:row.id}}).then(response=>{
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
			uploadSuccess(response, file, fileList){
				if(response.success === true){
					this.$message.success(response.msg);
				}else{
					this.$message.warning(response.msg);
				}
			},
			uploadError(err, file, fileList){
				this.$message.warning('上传文件异常！');
			},
			uploadParam(){
				console.log({type:${fileType}});
				return {type:${fileType}};
			},
			search(){
				var that = this;
				axios.get('/safety/file/fileList',{params:{type:this.$data.curType}}).then(response=>{
					if(response.data.success === true){
						that.$data.tableData = [];
						response.data.data.forEach(e=>that.$data.tableData.push(e));
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