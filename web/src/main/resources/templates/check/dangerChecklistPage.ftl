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
					<el-button type="success" @click="print" style="margin-bottom: 20px">打印</el-button>
					<div style="height: 650px;width: 1650px;">
						<el-scrollbar style="height:100%;width: 100%;">
					<el-table border style="width: 100%" ref="singleTable" :data="data" >
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
<script type="text/javascript" src="/Public/js/check/dangerChecklistPage.js" ></script>
</html>