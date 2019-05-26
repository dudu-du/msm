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

	<body>
		<div id="app">
			<el-container>
				<el-main>
					<el-row style="margin-bottom:10px">
						<el-select placeholder="请选择" v-model="topselect.date">
						    <el-option
						      key="2019"
						      label="2019年"
						      value="2019">
						    </el-option>
						 </el-select>
						<el-select placeholder="请选择" v-model="topselect.orgs.value">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
					</el-row>
					<el-table border :header-cell-style="headerStyle" :data="tableData" style="width: 100%" :span-method="arraySpanMethod" :cell-class-name="cellClassMethod" ref="singleTable">
						<el-table-column prop="index" label="序号" width="60"></el-table-column>
						<el-table-column prop="postName" label="岗位（设备设施/作业活动）单元" width="150">
						</el-table-column>
						<el-table-column prop="harmfulFactors" label="危险有害因素" width="120">
						</el-table-column>
						<el-table-column prop="levelName" label="安全风险等级"></el-table-column>
						<el-table-column prop="measure" label="现有措施有效性" show-overflow-tooltip></el-table-column>
						<el-table-column prop="departmentName" label="责任部门"></el-table-column>
						<el-table-column prop="personName" label="责任人"></el-table-column>
						<el-table-column label="操作" width="140px" v-if="curData.state==1">
							<template slot-scope="scope">
						        <el-button @click="edit(scope.row,'validateForm')" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer>
					<div style="width:100%;height:100%">
						<span style="width:48.5%;display: inline-block;"></span>
						<el-button circle type="success" v-if="curData.state==1" icon="el-icon-plus" @click="dialogFormVisible = true"></el-button>
					</div>
				</el-footer>
			</el-container>
			<el-dialog title="安全风险分级管控" :visible.sync="dialogFormVisible" @open="dialogFormOpen" @close="dialogClose('validateForm')">
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
			    <el-form-item label="安全风险评价" prop="levelName">
				    <el-select v-model="form.levelName" placeholder="请选择风险等级">
				      <el-option v-for="item in levels" :label="item.name" :key="item.name" :value="item.name"></el-option>
				    </el-select>
			  	</el-form-item>
			    <el-form-item label="现有措施有效性" prop="measure">
			      <el-input v-model="form.measure" autocomplete="off" type="textarea"></el-input>
			    </el-form-item>
			    <el-form-item label="责任部门" prop="departmentName">
			      <el-input v-model="form.departmentName" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="责任人" prop="personName">
			      <el-input v-model="form.personName" autocomplete="off"></el-input>
			    </el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('validateForm')">确 定</el-button>
			  </div>
			</el-dialog>
		</div>
	</body>
<script type="text/javascript" src="/Public/js/risk/Control.js" ></script>

</html>