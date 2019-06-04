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
					<div style="height: 650px;width: 1650px;">
						<el-scrollbar style="height:100%;width: 100%;">
					<el-table border style="width: 100%" ref="singleTable" :data="data" >
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
								<img :src="scope.row.rectificationContentUrl"></img>
							</template>
						</el-table-column>
						<el-table-column prop="rectificationTime" label="整改期限">
						</el-table-column>
						<el-table-column prop="rectificationMeasure" label="整改措施" align="center">
						</el-table-column>
						<el-table-column prop="rectificationResult" label="整改结果" align="center">
							<template slot-scope="scope">
								{{scope.row.rectificationResult}}
								<img :src="scope.row.rectificationResultUrl"></img>
							</template>
						</el-table-column>
						<el-table-column label="操作" align="center">
							<template slot-scope="scope">
								<el-button type="primary" @click="openPrint(scope.row)">打印</el-button>
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
<script type="text/javascript" src="/Public/js/check/rectificationReceiptPage.js" ></script>
</html>