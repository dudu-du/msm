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
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">安全风险告知卡</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-col :span="12">
							<el-select placeholder="请选择" v-model="topselect.date">
							    <el-option
							      key="2019"
							      label="2019年"
							      value="2019">
							    </el-option>
							 </el-select>
							<el-select placeholder="请选择" v-model="topselect.orgs.value" v-if="role=='ROLE_SUPERADMIN'">
							    <el-option
							      v-for="item in topselect.orgs.data"
							      :key="item.id"
							      :label="item.name"
							      :value="item.id">
							    </el-option>
							  </el-select>
							  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
							<el-button type="primary" @click="print">打印</el-button>
						</el-col>
						<el-col :span="12" style="text-align:right;">
							<el-button circle type="success" v-if="role=='ROLE_SUPERADMIN' || role=='ROLE_ORGADMIN'" icon="el-icon-plus" @click="add"></el-button>
						</el-col>
					</el-row>
					<div id="divprint" style="width: 1650px;">
						<el-scrollbar style="height:100%;width: 100%;">
					<el-table border style="width: 100%" ref="singleTable" :data="data.list" >
						<el-table-column type="index" label="序号" align="center" width="50px"></el-table-column>
						<el-table-column prop="jobName" label="工作内容" align="center">
						</el-table-column>
						<el-table-column prop="jobPosition" label="工作场所" align="center">
						</el-table-column>
						<el-table-column label="应急措施" prop="emergencyMeasure">
						</el-table-column>
						<el-table-column label="操作" width="200px">
							<template slot-scope="scope">
                                <el-tooltip class="item" effect="dark" content="修改" placement="top-start" v-if="'${MEMBER_ROLE}'=='ROLE_SUPERADMIN'|| '${MEMBER_ROLE}'=='ROLE_ORGADMIN'">
                                    <el-button @click="edit(scope.row)" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
                                </el-tooltip>
                                <el-tooltip class="item" effect="dark" content="删除" placement="top-start" v-if="'${MEMBER_ROLE}'=='ROLE_SUPERADMIN'|| '${MEMBER_ROLE}'=='ROLE_ORGADMIN'">
                                    <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
                                </el-tooltip>
                                <el-button type="primary" size="mini" @click="openPrint(scope.row)">打印</el-button>
							</template>
						</el-table-column>
					</el-table>
						</el-scrollbar>
					</div>
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
        return{
        	role:'${MEMBER_ROLE}',
            curPage:1,
            page:{
                total:0,
                pageSize:10
            },
            data:[],
            topselect:{
				orgs:{
					value:'${MEMBER_ORGID}',
					data:[]
				},
				date:'2019'
			}
        }
    },
    methods:{
        openPrint(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardPrint?"+row.id);
        },edit(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardEdit?"+row.id);
        },add(row){
            window.open("/safety/safetyNotificationCard/safetyNotificationCardAdd?orgId="+this.$data.topselect.orgs.value+'&year='+this.$data.topselect.date);
        },del(row){
            var that=this;
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete('/safety/safetyNotificationCard/safetyNotificationCard',{params:{id:row.id}}).then(response=>{
                    if(response.data.success === true){
                        this.$message.success(response.data.msg);
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
        search(){
            var that = this;
            axios.get('/safety/safetyNotificationCard/safetyNotificationCardByPage',{params:{year:this.$data.topselect.date,currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize,orgId:this.$data.topselect.orgs.value}}).then(function(res){
                if(res.data.success === true){
                    that.$data.data = [];
                    that.data = res.data.data;
                    that.$data.page.total = res.data.data.total;
                }else{
                    that.$message.warning(res.data.msg);
                }
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
        });
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