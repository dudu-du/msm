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
		img{
			width:50px;
			height:50px;
		}
	</style>

	<body>
		<div id="app">
			<el-container>
				<el-header>
					<el-col :span="8">&nbsp;</el-col><el-col :span="8" style="text-align:center;font-size:32px;">隐患治理信息台账列表</el-col><el-col :span="8">&nbsp;</el-col>
				</el-header>
				<el-main>
					<el-table border style="width: 100%" ref="singleTable" :data="data" >
						<el-table-column type="index" label="序号" align="center"></el-table-column>
						<el-table-column prop="investigationTime" label="排查时间">
						</el-table-column>
						<el-table-column prop="investigationOrgPersonName" label="排查人" align="center">
						</el-table-column>
						<el-table-column prop="rectificationPosition" label="隐患部位" align="center">
							<template slot-scope="scope">
								{{scope.row.rectificationPosition}}
								<img :src="scope.row.rectificationPositionUrl"></img>
							</template>
						</el-table-column>
						<el-table-column prop="rectificationName" label="隐患名称" align="center"></el-table-column>
						<el-table-column prop="rectificationLevel" label="隐患等级">
						</el-table-column>
						<el-table-column prop="governmentMeasure" label="治理措施" align="center">
						</el-table-column>
						<el-table-column prop="complateTime" label="完成时限" align="center">
						</el-table-column>
						<el-table-column prop="controlOrgName" label="责任部门" align="center"></el-table-column>
						<el-table-column prop="controlOrgPersonName" label="责任人">
						</el-table-column>
						<el-table-column prop="reviewTime" label="复查时间" align="center">
						</el-table-column>
						<el-table-column prop="reviewPersonName" label="复查人" align="center">
						</el-table-column>
						<el-table-column prop="reviewResult" label="复查结果" align="center">
							<template slot-scope="scope">
								{{scope.row.reviewResult}}
								<img :src="scope.row.reviewResultUrl"></img>
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
<script type="text/javascript" src="/Public/js/check/dangerLedgerPage.js" ></script>
</html>