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
						<el-select placeholder="请选择" v-model="topselect.date">
						    <el-option
						      key="2019"
						      label="2019年"
						      value="2019">
						    </el-option>
						 </el-select>
						<el-select placeholder="请选择" v-model="topselect.orgs.value" @change="orgsChange">
						    <el-option
						      v-for="item in topselect.orgs.data"
						      :key="item.id"
						      :label="item.name"
						      :value="item.id">
						    </el-option>
						  </el-select>
						  <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
					</el-row>
					<el-table :data="tableData" style="width: 100%" :span-method="arraySpanMethod" :cell-class-name="cellClassMethod">
						<el-table-column prop="index" label="序号" width="150"></el-table-column>
						<el-table-column prop="post_name" label="岗位（设备设施/作业活动）单元" width="150">
						</el-table-column>
						<el-table-column label="安全风险辨识">
							<el-table-column prop="factor" label="危险有害因素" width="120">
							</el-table-column>
							<el-table-column prop="trouble" label="事故类型">
								<template slot-scope="scope">
							        <el-tag type="warning" disable-transitions v-for="item in scope.row.trouble">{{item}}</el-tag>
							    </template>
							</el-table-column>
							<el-table-column prop="cause" label="原因"></el-table-column>
							<el-table-column prop="consequence" label="后果"></el-table-column>
							<el-table-column prop="incidence" label="影响范围"></el-table-column>
						</el-table-column>
						<el-table-column label="安全风险分析">
							<el-table-column prop="possibility" label="可能性"></el-table-column>
							<el-table-column prop="seriousness" label="严重性"></el-table-column>
							<el-table-column prop="measure" label="现有措施有效性"></el-table-column>
						</el-table-column>
						<el-table-column label="LEC风险分析法">
							<el-table-column prop="num_l" label="事故发生的可能性(L)"></el-table-column>
							<el-table-column prop="num_e" label="暴露于危险环境的频繁程度(E)"></el-table-column>
							<el-table-column prop="num_c" label="发生事故产生的后果(C)"></el-table-column>
							<el-table-column prop="num_d" label="D值"></el-table-column>
						</el-table-column>
						<el-table-column prop="level_name" label="安全风险评价">
							
						</el-table-column>
						<el-table-column prop="level_name" label="操作" width="140px">
							<template slot-scope="scope">
								<el-popover
								  placement="top"
								  width="160" >
								  <div style="text-align: right; margin: 0">
								    <el-button size="mini" type="text" @click="addcheck(1,1,'checkForm')">月检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(1,1,'checkForm')">周检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(0,1,'checkForm')">日检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(1,0,'checkForm')">专项检查</el-button>
								    <el-button type="text" size="mini" @click="addcheck(0,0,'checkForm')">综合检查(节假日、复产前)</el-button>
								    <el-button type="text" size="mini" @click="addcheck(0,0,'checkForm')">综合检查(季节性)</el-button>
								  </div>
								  <el-button slot="reference"  type="info" size="mini" icon="el-icon-s-unfold" circle></el-button>
								</el-popover>
						        <el-button @click="edit(scope.row,'validateForm')" type="primary" size="mini" icon="el-icon-edit" circle></el-button>
						        <el-button style="margin-left:0" @click="del(scope.row)" type="danger" size="mini" icon="el-icon-delete" circle></el-button>
						     </template>
						</el-table-column>
					</el-table>
				</el-main>
				<el-footer>
					<div style="width:100%;height:100%">
						<span style="width:48.5%;display: inline-block;"></span>
						<el-button circle type="success" icon="el-icon-plus" @click="dialogFormVisible = true"></el-button>
					</div>
				</el-footer>
			</el-container>
			<el-dialog title="安全风险辨识项" :visible.sync="dialogFormVisible" @open="dialogFormOpen">
			  <el-form :model="form" label-width="110px" label-position="right" ref="validateForm">
			    <el-form-item label="岗位单元" prop="post_name">
			    	<el-select v-model="form.post_name" placeholder="请选择">
					    <el-option
					      v-for="item in post_options"
					      :key="item.id"
					      :label="item.name"
					      :value="item.id">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item label="危险有害因素" prop="factor">
			    	<el-select v-model="form.factor" placeholder="请选择">
					    <el-option
					      v-for="item in facotrs"
					      :key="item.id"
					      :label="item.name"
					      :value="item.id">
					    </el-option>
					 </el-select>
			    </el-form-item>
			    <el-form-item prop="trouble">
			    	<el-transfer v-model="form.trouble" :props="{key:'id',label:'name'}" :data="troubles" :titles=["事故类型","已选择"]></el-transfer>			    	
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
			    <el-form-item label="L" prop="num_l">
			      <el-input v-model="form.num_l" autocomplete="off" placeholder="事故发生的可能性(L)"></el-input>
			    </el-form-item>
			    <el-form-item label="E" prop="num_e">
			      <el-input v-model="form.num_e" autocomplete="off" placeholder="暴露于危险环境的频繁程度(E)"></el-input>
			    </el-form-item>
			    <el-form-item label="C" prop="num_c">
			      <el-input v-model="form.num_c" autocomplete="off" placeholder="发生事故产生的后果(C)"></el-input>
			    </el-form-item>
			    <el-form-item label="D值" prop="num_d">
			      <el-input v-model="form.num_d" autocomplete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="安全风险评价" prop="level_name">
				    <el-select v-model="form.level_name" placeholder="请选择风险等级">
				      <el-option label="重大风险" value="重大风险"></el-option>
				      <el-option label="较大风险一般风险" value="较大风险一般风险"></el-option>
				      <el-option label="一般风险" value="一般风险"></el-option>
				      <el-option label="低风险" value="低风险"></el-option>
				    </el-select>
			  	</el-form-item>
			  </el-form>
			  <div slot="footer" class="dialog-footer">
			    <el-button @click="dialogFormVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitForm('validateForm')">确 定</el-button>
			  </div>
			</el-dialog>
			<el-dialog title="隐患排查治理" :visible.sync="checkFormVisible" >
				<addcheck :checktype="checks.checktype" :checkitem="checks.checkitem"></addcheck>
			</el-dialog>	
		</div>
	</body>
<script type="text/javascript" src="/Public/js/risk/Incidentfication.js" ></script>
<script type="text/x-template" id="addcheck">
	<el-form  ref="checkForm" label-width="66px">
	  <el-form-item
	    v-for="(domain, index) in checkForm.domains"
	    :key="domain.key"
	    :prop="'domains.' + index + '.value'"
	  >
	  <el-col :span="4">
	  <el-select  placeholder="活动区域" v-if="checktype==1">
	      <el-option label="区域一" value="shanghai"></el-option>
	      <el-option label="区域二" value="beijing"></el-option>
	    </el-select>
	  </el-col>
	  <el-col :span="10">
	    <el-input v-model="domain.value" v-if="checkitem==1" placeholder="检查项目及相关要求"></el-input>
	  </el-col>
	  <el-col :span="6">
	    <el-input v-model="domain.value" placeholder="检查方法"></el-input>
	  </el-col>
	  <el-col :span="4">
	  	<el-button @click.prevent="removeDomain(domain)">删除</el-button>
	  </el-col>
	  </el-form-item>
	  <el-form-item>
	  	<span style="width:27%;display: inline-block;"></span>
	    <el-button type="primary" @click="submitForm('checkFormForm')">提交</el-button>
	    <el-button @click="addDomain('checkFormForm')">新增</el-button>
	    <el-button @click="resetForm('checkFormForm')">重置</el-button>
	  </el-form-item>
	</el-form>
</script>
</html>