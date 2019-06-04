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
		<style>
			.el-table .warning-row {
			    background: #DAA520;
			    color:#000000;
			 }
			
			 .el-table .success-row {
			    background: #4169E1;
			    color:#000000;
			 }
			 .el-table .danger-row{
			 	background:#FF0000;
			 	color:#000000;
			 }
			 .el-table .common-row{
			 	background:#FFFF00;
			 	color:#000000;
			 }
		</style>
	</head>

	<body>
		<div id="app">
			<el-container>
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
							<el-select placeholder="请选择" v-model="topselect.orgs.value" >
							    <el-option
							      v-for="item in topselect.orgs.data"
							      :key="item.id"
							      :label="item.name"
							      :value="item.id">
							    </el-option>
							  </el-select>
							  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
							<el-button type="success" @click="print">打印</el-button>
						</el-col>
						<el-col :span="12" style="text-align:right;">
							<el-button circle type="success" v-if="curData.state==1" icon="el-icon-plus" @click="dialogFormVisible = true"></el-button>
						</el-col>
					</el-row>
					<div id="divprint" style="width: 1650px;">
						<el-scrollbar style="height:100%;width: 100%;">
					<el-table border resizable highlight-current-row :data="tableData" style="width: 100%" :span-method="arraySpanMethod" :cell-class-name="cellClassMethod" ref="singleTable">
						<el-table-column prop="index" label="序号" width="60"></el-table-column>
						<el-table-column prop="postName" label="岗位（设备设施/作业活动）单元" width="100">
						</el-table-column>
						<el-table-column label="安全风险辨识">
							<el-table-column prop="harmfulFactors" label="危险有害因素" width="100">
							</el-table-column>
							<el-table-column prop="troubleNameList" label="事故类型">
								<template slot-scope="scope">
							        <el-tag type="warning" disable-transitions v-for="item in scope.row.troubleNameList">{{item}}</el-tag>
							    </template>
							</el-table-column>
							<el-table-column prop="cause" label="原因"></el-table-column>
							<el-table-column prop="consequence" label="后果"></el-table-column>
							<el-table-column prop="incidence" label="影响范围" ></el-table-column>
						</el-table-column>
						<el-table-column label="安全风险分析">
							<el-table-column prop="possibility" label="可能性"></el-table-column>
							<el-table-column prop="seriousness" label="严重性"></el-table-column>
							<el-table-column prop="measure" label="现有措施有效性"></el-table-column>
						</el-table-column>
						<el-table-column label="LEC风险分析法">
							<el-table-column prop="numL" label="事故发生的可能性(L)"></el-table-column>
							<el-table-column prop="numE" label="暴露于危险环境的频繁程度(E)"></el-table-column>
							<el-table-column prop="numC" label="发生事故产生的后果(C)"></el-table-column>
							<el-table-column prop="numD" label="D值"></el-table-column>
						</el-table-column>
						<el-table-column prop="levelName" label="安全风险评价">
							
						</el-table-column>
						<el-table-column label="操作" width="140px" v-if="curData.state==1">
							<template slot-scope="scope">
						        <el-button @click="edit(scope.row,'validateForm')" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
						</el-scrollbar>
                    </div>
				</el-main>
			</el-container>
			<el-dialog title="安全风险辨识项" :visible.sync="dialogFormVisible" @open="dialogFormOpen" @close="dialogClose('validateForm')">
			  <el-form :model="form" label-width="110px" label-position="right" ref="validateForm">
			    <el-form-item label="岗位单元" prop="postName">
			    	<el-select v-model="form.postName" placeholder="请选择">
					    <el-option
					      v-for="item in post_options"
					      :key="item.name"
					      :label="item.name"
					      :value="item.name">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item label="危险有害因素" prop="harmfulFactors">
			    	<el-select v-model="form.harmfulFactors" placeholder="请选择">
					    <el-option
					      v-for="item in facotrs"
					      :key="item.name"
					      :label="item.name"
					      :value="item.name">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item prop="troubleNameList">
			    	<el-transfer v-model="form.troubleNameList" :props="{key:'name',label:'name'}" :data="troubles" :titles=["事故类型","已选择"]></el-transfer>			    	
			    </el-form-item>
			    <el-form-item label="原因" prop="cause">
			      <el-input v-model="form.cause" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="后果" prop="consequence">
			      <el-input v-model="form.consequence" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="影响范围" prop="incidence">
			      <el-input v-model="form.incidence" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="可能性" prop="possibility">
			      <el-input v-model="form.possibility" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="严重性" prop="seriousness">
			      <el-input v-model="form.seriousness" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="现有措施有效性" prop="measure">
			      <el-input v-model="form.measure" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="L" prop="numL">
			      <el-input v-model="form.numL" type="number" step="0.01" autocomplete="off" placeholder="事故发生的可能性(L)"></el-input>
			    </el-form-item>
			    <el-form-item label="E" prop="numE">
			      <el-input v-model="form.numE" type="number" step="0.01" autocomplete="off" placeholder="暴露于危险环境的频繁程度(E)"></el-input>
			    </el-form-item>
			    <el-form-item label="C" prop="numC">
			      <el-input v-model="form.numC" type="number" step="0.01" autocomplete="off" placeholder="发生事故产生的后果(C)"></el-input>
			    </el-form-item>
			    <el-form-item label="D值" prop="numD">
			      <el-input v-model="form.numD" type="number" step="0.01" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="安全风险评价" prop="levelName">
				    <el-select v-model="form.levelName" placeholder="请选择风险等级">
				      <el-option v-for="item in levels" :label="item.name" :key="item.name" :value="item.name"></el-option>
				    </el-select>
			  	</el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('validateForm')">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript" src="/Public/js/risk/Evaluation.js" ></script>
    <script src="/node_modules/jquery/jquery-3.3.1.min.js"></script>
    <script src="/node_modules/jquery/jquery.PrintArea.js"></script>
    <script>
        $(document).ready(function(){
            $("#print").click(function(){
                $("#divprint").printArea();
            });
        });
    </script>
</html>