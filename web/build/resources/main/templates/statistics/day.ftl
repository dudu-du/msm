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
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
		<script src="https://unpkg.com/vue-chartjs/dist/vue-chartjs.min.js"></script>
	</head>

	<body>
		<div id="app">
			<el-container>
				<el-header>
				<el-select placeholder="请选择" v-model="topselect.orgs.value" v-if="role=='ROLE_SUPERADMIN'">
								    <el-option
								      v-for="item in topselect.orgs.data"
								      :key="item.id"
								      :label="item.name"
								      :value="item.id">
								    </el-option>
								  </el-select>
								  
						<el-date-picker
					      v-model="value"
					      type="daterange"
					      align="right"
					      unlink-panels
					      range-separator="至"
					      start-placeholder="开始日期"
					      end-placeholder="结束日期"
					      value-format="yyyy-MM-dd"
					      :picker-options="pickerOptions">
					    </el-date-picker>
				    	<el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
				    	<el-dropdown split-button type="success" @command="levelSort">
						 按{{btn}}排序
						  <el-dropdown-menu slot="dropdown">
						    <el-dropdown-item command="事故类型">按事故类型排序</el-dropdown-item>
						    <el-dropdown-item command="风险级别">按风险级别排序</el-dropdown-item>
						    <el-dropdown-item command="危害因素">按危害因素排序</el-dropdown-item>
						  </el-dropdown-menu>
						</el-dropdown>
				    </el-header>
				    <el-main>
				    	<el-row>
				    		<el-table
						      :data="tableData"
						      style="width: 100%"
						      center
						      max-height="250"
						      >
						      <el-table-column
						        type="index"
						        label="序号">
						      </el-table-column>
						      <el-table-column
						        prop="name"
						        label="机构名称">
						      </el-table-column>
						      <el-table-column
						        prop="dateofyear"
						        label="日期">
						        <template slot-scope="scope">
						        	{{getDateStr(new Date(scope.row.dateofyear))}}
						        </template>
						      </el-table-column>
						      <el-table-column
						        prop="level_name"
						        :label="btn">
						      </el-table-column>
						      <el-table-column
						        prop="count"
						        label="数量">
						      </el-table-column>
						    </el-table>
				    	</el-row>
				    	<el-row>
							<line-chart ref="chart"></line-chart>
						</el-row>
					</el-main>
			</el-container>
		</div>
	</body>
<script type="text/javascript">
	Vue.component('line-chart', {
	  extends: VueChartJs.Line,
	  data:function(){
	  	return {
          gradient: null,
          gradient2: null
       };
	  },
	  mounted:function() {
	  	
	  },
	  methods:{
	  	render(data,data2){
	  		data2.forEach(e=>{
	  			var rgb = this.getRandomColor();
	  			var gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450);
		        gradient.addColorStop(0, 'rgba('+rgb+',0.9)');
		        gradient.addColorStop(0.5, 'rgba('+rgb+',0.25)');
		        gradient.addColorStop(1, 'rgba('+rgb+',0)');
		        e.backgroundColor = gradient;
		        e.borderColor = 'rgb('+rgb+')';
	  		});
	  		this.renderChart({
		      labels: data,
		      datasets: data2
		    }, {responsive: true, maintainAspectRatio: false});
	  	},
	  	getRandomColor(){
		     this.r = Math.floor(Math.random()*255);
		     this.g = Math.floor(Math.random()*255);
		     this.b = Math.floor(Math.random()*255);
		     return this.r +','+ this.g +','+ this.b;
		}
	  }
	});
	new Vue({
		el:'#app',
		created:function(){
			var that = this;
			this.value = this.defaultDate();
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
			return {
				labels:['重大风险', '较大风险', '一般风险', '低风险'],
				btn:'事故类型',
				pickerOptions: {
		          shortcuts: [{
		            text: '最近一周',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
		              picker.$emit('pick', [start, end]);
		            }
		          }, {
		            text: '最近一个月',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
		              picker.$emit('pick', [start, end]);
		            }
		          }, {
		            text: '最近三个月',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
		              picker.$emit('pick', [start, end]);
		            }
		          }]
		        },
		        value: [],
		        role:'${MEMBER_ROLE}',
		        topselect:{
		        	orgs:{
		        		value:'${MEMBER_ORGID}',
		        		data:[]
		        	}
		        },
		        tableData:[]
			};
		},
		methods:{
			search(){

				var startDate = this.$data.value[0];
				var endDate = this.$data.value[1];
				var labels = [];
				var start = new Date(startDate);
				var end = new Date(endDate);
				
				var url = '';
				if(this.$data.btn == '事故类型'){
					url = '/safety/Statistics/dayOffgradeTroubleCount';
				}else if(this.$data.btn == '风险级别'){
					url = '/safety/Statistics/dayOffgradeLevelCount';
				}else{
					url = '/safety/Statistics/dayOffgradeHarmfulCount';
				}
				
				var that = this;
				var Sets = function(){
					return {
			              label: '',
			              borderColor: '',
			              pointBackgroundColor: 'white',
			              borderWidth: 1,
			              pointBorderColor: 'white',
			              backgroundColor: '',
			              data: []
			            };
				};
			
			    var datas = [];
			    for(var i = start;i<=end;){
					var month = i.getMonth() + 1;
					var date = i.getDate();
					labels.push(month+'月'+date+'日');
					datas.push(0);
					i.setDate(date+1);
				}
				//var labelData = {};
				axios.get(url,{params:{orgId:this.$data.topselect.orgs.value,startTime:startDate,endTime:endDate}}).then(response=>{
					if(response.data.success === true){
						
						that.$data.tableData = [];
						
						var datasets = [];
						response.data.data.forEach(e=>{
							that.$data.tableData.push(e);
							var index = -1;
							for(var ii=0;ii<datasets.length;ii++){
								if(e.level_name === datasets[ii].label || e.trouble_name === datasets[ii].label){
									index = ii;
									break;
								}
							}
							if(index < 0){
								var s = new Sets();
								if(e.level_name){
									s.label = e.level_name;								
								}else{
									s.label = e.trouble_name;	
								}
								datas.forEach(e=>{
									s.data.push(e);
								});
								
								var sdate = new Date(startDate); 
							  　　	var now = new Date(e.dateofyear); 
							  　　 var days = now.getTime() - sdate.getTime(); 
							  　　  var day = parseInt(days / (1000 * 60 * 60 * 24));
								s.data[day+1]=e.count;
								datasets.push(s);
							}else{
								var sdate = new Date(startDate); 
							  　　	var now = new Date(e.dateofyear); 
							  　　 var days = now.getTime() - sdate.getTime(); 
							  　　  var day = parseInt(days / (1000 * 60 * 60 * 24));
								datasets[index].data[day+1]=e.count;
							}
							
						});
		
						that.$refs.chart.render(labels,datasets);
					}else{
						that.$message.warning(response.data.msg);
						that.$refs.chart.render(labels);
					}
				}).catch(err=>{
					this.$message.error('服务器异常，请稍后再试！');
					//that.$refs.chart.render(labels);
				});
				
			},
			defaultDate(){
				const end = new Date();
	            const start = new Date();
	            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
	            var s = this.getDate(start);
	            var e = this.getDate(end);
	            return [s, e];
			},
			levelSort(command){
				this.$data.btn = command;
				this.search();
			},
			getDate(date){
				var year = date.getFullYear();
				var month = date.getMonth()+1;
				var day = date.getDate();
				if(month < 10){
					month = '0'+month;
				}
				if(day<10){
					day = '0' + day;
				}
				return year + '-' + month + '-' + day;
			},
			getDateStr(date){
				var year = date.getFullYear();
				var month = date.getMonth()+1;
				var day = date.getDate();
				if(month < 10){
					month = '0'+month;
				}
				if(day<10){
					day = '0' + day;
				}
				return year + '年' + month + '月' + day + '日';
			}
		}
	});
</script>

</html>