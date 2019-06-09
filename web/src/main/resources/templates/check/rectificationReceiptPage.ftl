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
		img{
			width:50px;
			height:50px;
		}
	</style>

	<body>
		<div id="app">
			<el-container>
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患整改回执单列表</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-col :span="24" >
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
					</el-row>
					<el-table border :max-height="tableHeight" style="width: 100%" ref="singleTable" :data="data" >
						<el-table-column type="index" label="序号" width="50px" align="center"></el-table-column>
						<el-table-column prop="checkOrgName" label="受检单位名称">
						</el-table-column>
						<el-table-column prop="fillTime" label="填写时间" align="center">
						</el-table-column>
						<el-table-column prop="checkPersonName" label="检查人员姓名" align="center">
						</el-table-column>
						<el-table-column prop="checkTime" label="检查日期" align="center"></el-table-column>
						<el-table-column prop="checkCode" label="编号">
						</el-table-column>
						<el-table-column prop="rectificationOrgName" label="隐患整改部门名称" align="center">
						</el-table-column>
						<el-table-column prop="rectificationPersonName" label="整改部门负责人姓名" align="center">
						</el-table-column>
						<el-table-column prop="rectificationContent" label="隐患内容及整改要求" align="center">
							<template slot-scope="scope">
								{{scope.row.rectificationContent}}
								<img :src="scope.row.rectificationContentUrl" v-if="scope.row.rectificationContentUrl && scope.row.rectificationContentUrl !== ''"></img>
							</template>
						</el-table-column>
						<el-table-column prop="rectificationTime" label="整改期限">
						</el-table-column>
						<el-table-column prop="rectificationMeasure" label="整改措施" align="center">
						</el-table-column>
						<el-table-column prop="rectificationResult" label="整改结果" align="center">
							<template slot-scope="scope">
								{{scope.row.rectificationResult}}
								<img :src="scope.row.rectificationResultUrl" v-if="scope.row.rectificationResultUrl && scope.row.rectificationResultUrl !== ''"></img>
							</template>
						</el-table-column>
						<el-table-column label="操作" align="center">
							<template slot-scope="scope">
								<el-button type="primary" @click="openPrint(scope.row)">打印</el-button>
							</template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer style="text-align:center;">
					<el-pagination
					  background
					  layout="prev, pager, next"
					  :total="page.total" :page-size="page.pageSize" :current-page.sync="curPage">
					</el-pagination>
				</el-footer>
			</el-container>
		</div>
	</body>
<script type="text/javascript">
new Vue({
    el:'#app',
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
    data:function(){
        return {
        	role:'${MEMBER_ROLE}',
        	topselect:{
				orgs:{
					value:'${MEMBER_ORGID}',
					data:[]
				}
			},
        	curPage:1,
			page:{
				total:0,
				pageSize:10
			},
            data: [],
            tableHeight: window.innerHeight - 230
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
        	var that = this;

			axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize,orgId:this.topselect.orgs.value}}).then(function(response){
        		loading.close();
        		if(response.data.success === true){
        			that.$data.data = [];
        			that.$data.page.total = response.data.data.total;
        			response.data.data.list.forEach(e=>{
        				that.$data.data.push(e);
        			});
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
            	loading.close();
                this.$message.error('服务器异常，请稍后再试！');
            });
        },
        openPrint(row){
            window.open("/safety/checkRectificationReceipt/checkRectificationReceiptPrint?"+row.id);
        }
	},
    watch:{
		curPage(val){
			this.search();
		}
	}
});
</script>
</html>