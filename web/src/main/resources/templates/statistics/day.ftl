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
				<el-select placeholder="请选择" v-model="topselect.orgs.value">
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
				    	<el-button :type="btn" @click="levelSort">按风险级别排序</el-button>
				    </el-header>
				    <el-main>
				    	<el-row>
				    		<el-table
						      :data="tableData"
						      style="width: 100%"
						      center
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
						        prop="create_time"
						        label="日期">
						      </el-table-column>
						      <el-table-column
						        prop="level_name"
						        label="风险等级" v-if="btn=='success'">
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
	  		this.gradient = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)
	        this.gradient2 = this.$refs.canvas.getContext('2d').createLinearGradient(0, 0, 0, 450)
	    
	        this.gradient.addColorStop(0, 'rgba(255, 0,0, 0.5)')
	        this.gradient.addColorStop(0.5, 'rgba(255, 0, 0, 0.25)');
	        this.gradient.addColorStop(1, 'rgba(255, 0, 0, 0)');
	        
	        this.gradient2.addColorStop(0, 'rgba(0, 231, 255, 0.9)')
	        this.gradient2.addColorStop(0.5, 'rgba(0, 231, 255, 0.25)');
	        this.gradient2.addColorStop(1, 'rgba(0, 231, 255, 0)');
	        if(data2.length == 1){
	        	data2[0].label = '总表';
	        }
	        if(data2){
	        	data2.forEach(e=>{
	        		e.backgroundColor = this.gradient;
	        	});
	        }
	        console.log(data2);
		    this.renderChart({
		      labels: data,
		      datasets: data2
		    }, {responsive: true, maintainAspectRatio: false});
	  	}
	  }
	});
	new Vue({
		el:'#app',
		created:function(){
			var that = this;
			this.value = this.defaultDate();
			axios.get('/View/allOrgList',{params:{parentId:'0'}}).then(response=>{
				if(response.data.success === true){
					if(response.data.data.length>0){
						that.$data.topselect.orgs.value = response.data.data[0].id;
					}
					response.data.data.forEach(e=>that.$data.topselect.orgs.data.push(e));
					that.search();

				}else{
					that.$message.warning(response.data.msg);
				}
			}).catch(err=>{
				this.$message.error('服务器异常，请稍后再试！');
			});
			
		},
		data:function(){
			return {
				labels:['重大风险', '较大风险', '一般风险', '低风险'],
				btn:'info',
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
		        topselect:{
		        	orgs:{
		        		value:'',
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
				for(var i = start;i<=end;){
					var month = i.getMonth() + 1;
					var date = i.getDate();
					labels.push(month+'月'+date+'日');
					i.setDate(date+1);
				}
				var url = '';
				if(this.$data.btn == 'info'){
					url = '/safety/Statistics/dayChecklistResultCount';
				}else{
					url = '/safety/Statistics/dayCheckListLevelCount';
				}
				var startDate = this.$data.value[0];
				var endDate = this.$data.value[1];
				var that = this;
				var datasets = [];
				var level1 = level2=level3=level4={
			              label: '',
			              borderColor: '#FC2525',
			              pointBackgroundColor: 'white',
			              borderWidth: 1,
			              pointBorderColor: 'white',
			              backgroundColor: '',
			              data: []
			            };
				axios.get(url,{params:{orgId:this.$data.topselect.orgs.value,startTime:startDate,endTime:endDate}}).then(response=>{
					if(response.data.success === true){
						console.log(response.data);
						
						that.$data.tableData = [];
						
						response.data.data.forEach(e=>{
							that.$data.tableData.push(e);
							if(e.level_name){
								if(level1.label == '' || level1.label == e.level_name){
									level1.label = e.level_name;
									level1.data.push(e.count);
								}else if(level2.label == '' || level2.label == e.level_name){
									level2.label = e.level_name;
									level2.data.push(e.count);
									level2.borderColor = '#05CBE1';
								}else if(level3.label == '' || level3.label == e.level_name){
									level3.label = e.level_name;
									level3.data.push(e.count);
									level3.borderColor = '#05CBE1';
								}else if(level4.label == '' || level4.label == e.level_name){
									level4.label = e.level_name;
									level4.data.push(e.count);
									level4.borderColor = '#05CBE1';
								}
							}else{
								level1.label = '总和';
								level1.data.push(e.count);
							}
						});
						if(level1.label=='' || level1.label == '总和'){
							datasets.push(level1);
						}else{
							datasets.push(level1);
							datasets.push(level2);
							datasets.push(level3);
							datasets.push(level4);
						}
						that.$refs.chart.render(labels,datasets);
					}else{
						that.$message.warning(response.data.msg);
						that.$refs.chart.render(labels);
					}
				}).catch(err=>{
					this.$message.error('服务器异常，请稍后再试！');
					that.$refs.chart.render(labels);
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
			levelSort(){
				if(this.$data.btn == 'info'){
					this.$data.btn = 'success';
				}else{
					this.$data.btn = 'info';
				}
				
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
			}
		}
	});
</script>

</html>