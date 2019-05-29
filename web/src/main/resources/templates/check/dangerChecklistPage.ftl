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
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患排查清单列表</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-table border style="width: 100%" ref="singleTable" :data="data.list" >
						<el-table-column type="index" label="序号" width="150" align="center"></el-table-column>
						<el-table-column prop="riskPosition" label="风险部位" width="150">
						</el-table-column>
						<el-table-column prop="harmfulFactors" label="风险因素" width="150" align="center">
						</el-table-column>
						<el-table-column prop="measure" label="风险管控措施" width="150" align="center">
						</el-table-column>
						<el-table-column prop="runawayPerformance" label="措施失控表现" width="150" align="center"></el-table-column>
						<el-table-column prop="controlOrgName" label="管控部门" width="152">
						</el-table-column>
						<el-table-column prop="controlOrgPersonName" label="管控责任人" width="152" align="center">
						</el-table-column>
						<el-table-column prop="investigationOrgName" label="排查部门" width="152" align="center">
						</el-table-column>
						<el-table-column prop="investigationOrgPersonName" label="排查责任人" width="152" align="center"></el-table-column>
						<el-table-column prop="investigationCount" label="排查频次" width="150">
						</el-table-column>
						<el-table-column prop="remark" label="备注" width="150" align="center">
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer style="text-align:center;">
					<el-button-group>
						<el-button type="primary" v-model="data" @click="prev(data.prePage,10)" icon="el-icon-arrow-left">上一页</el-button>
						<el-button type="primary" v-model="data" @click="next(data.nextPage,10)">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
					</el-button-group>
				</el-footer>
			</el-container>
		</div>
	</body>
<script type="text/javascript" src="/Public/js/check/dangerChecklistPage.js" ></script>
</html>