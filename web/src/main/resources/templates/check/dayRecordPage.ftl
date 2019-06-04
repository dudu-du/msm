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
                    <el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患排查记录查询</el-col><el-col :span="8">&nbsp;</el-col>
                </el-header>
				<el-main>
					<el-row style="margin-bottom:10px;">
						<el-select placeholder="请选择" v-model="checkType">
						    <el-option key="1" label="日治理记录" value="0"></el-option>
						    <el-option key="2" label="周排查记录" value="1"></el-option>
						    <el-option key="3" label="月排查记录" value="2"></el-option>
						    <el-option key="4" label="专项检查" value="3"></el-option>
						    <el-option key="5" label="综合检查（节假日、复产前）" value="4"></el-option>
						    <el-option key="6" label="综合检查（季节性）" value="5"></el-option>
						 </el-select>
						 <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
					</el-row>
					<el-table border style="width: 100%" ref="singleTable" :data="tableData" >
						<el-table-column type="index" label="序号" align="center"></el-table-column>
						<el-table-column prop="checkContent" label="检查内容">
						</el-table-column>
						<el-table-column prop="createTime" label="创建时间" align="center">
						</el-table-column>
						<el-table-column label="检查人" prop="checkPersonName" align="center">
						</el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                            	<el-button type="primary" size="mini" @click="openPrint(scope.row)">打印</el-button>
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
	data:function(){
		return {
			curPage:1,
			page:{
				total:0,
				pageSize:10
			},
			tableData:[],
			checkType:'0',
			searchUrl:[
                "/safety/checkDayRecord/checkDayRecordByPage",
                "/safety/checkWeekRecord/checkWeekRecordByPage",
                "/safety/checkMonthRecord/checkMonthRecordByPage",
                "/safety/checkSpecialRecord/checkSpecialRecordByPage",
                "/safety/checkHolidayRecord/checkHolidayRecordByPage",
                "/safety/checkSeasonRecord/checkSeasonRecordByPage"
			],
			printUrl:[
                "/safety/checkDayRecord/checkDayRecordPrint",
                "/safety/checkWeekRecord/checkWeekRecordPrint",
                "/safety/checkMonthRecord/checkMonthRecordPrint",
                "/safety/checkSpecialRecord/checkSpecialRecordPrint",
                "/safety/checkHolidayRecord/checkHolidayRecordPrint",
                "/safety/checkSeasonRecord/checkSeasonRecordPrint"
			]
		};
	},
	created:function(){
		this.search();
	},
	methods:{
		search(){
			var that = this;
			var ind = this.$data.checkType;
			axios.get(this.$data.searchUrl[ind],{params:{currentPage:this.$data.curPage,pageSize:this.$data.page.pageSize}}).then(function(response){
        		if(response.data.success === true){
        			that.$data.tableData = [];
        			that.$data.page.total = response.data.data.total;
                    response.data.data.list.forEach(e=>{
        			    e.curUrl = that.$data.printUrl[ind];
        				that.$data.tableData.push(e);
        			});
				}else{
					that.$message.warning(response.data.msg);
				}
            }).catch(err=>{
                this.$message.error('服务器异常，请稍后再试！');
            });
		},
		openPrint(row){
	        window.open(row.curUrl+"?"+row.id);
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