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
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患排查清单列表</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
					<el-col :span="20" >
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
						<el-button type="success" @click="print">打印</el-button>
					</el-col>
					</el-row>
					<el-table border :max-height="tableHeight" style="width: 100%" ref="singleTable" :data="data" >
						<el-table-column type="index" label="序号" align="center"></el-table-column>
						<el-table-column prop="riskPosition" label="风险部位">
						</el-table-column>
						<el-table-column prop="harmfulFactors" label="风险因素" align="center">
						</el-table-column>
						<el-table-column prop="measure" label="风险管控措施" align="center">
						</el-table-column>
						<el-table-column prop="runawayPerformance" label="措施失控表现" align="center"></el-table-column>
						<el-table-column prop="controlOrgName" label="管控部门">
						</el-table-column>
						<el-table-column prop="controlOrgPersonName" label="管控责任人" align="center">
						</el-table-column>
						<el-table-column prop="investigationOrgName" label="排查部门" align="center">
						</el-table-column>
						<el-table-column prop="investigationOrgPersonName" label="排查责任人" align="center"></el-table-column>
						<el-table-column prop="investigationCount" label="排查频次">
						</el-table-column>
						<el-table-column prop="remark" label="备注" align="center">
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
<script type="text/javascript" >
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
        	var that = this;
            axios.get('/safety/checkDangerChecklist/checkDangerChecklistByPage',{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize,orgId:this.topselect.orgs.value}}).then(function(res){
            	if(res.data.success === true){
            		that.$data.data = [];
            		that.$data.page.total = res.data.data.total;
                	res.data.data.list.forEach(e=>{
                		that.$data.data.push(e);
                	});
                }else{
                	that.$message.warning(res.data.msg);
                }
	        }).catch(err=>{
	              this.$message.error('服务器异常，请稍后再试！');
	        });
        },print(){
			window.open("/safety/checkDangerChecklist/checkDangerChecklistPrint?currentPage="+this.$data.curPage+"&pageSize="+this.$data.page.pageSize+"");
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