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
                    <el-button-group style="float: right;margin-bottom: 10px;" v-if="'${MEMBER_ROLE}'=='ROLE_SUPERADMIN'|| '${MEMBER_ROLE}'=='ROLE_ORGADMIN'">
                        <el-tooltip class="item" effect="dark" content="新增" placement="top-start">
                            <el-button @click="add" type="primary" size="mini" icon="el-icon-plus" circle></el-button>
                        </el-tooltip>
                    </el-button-group>
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
<script type="text/javascript" src="/Public/js/check/notificationCardPage.js" ></script>

</html>