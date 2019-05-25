var Incidentfication = function(obj) {
	if(!obj) {
		this.cause = '';
		this.post_name = '';
		this.factor = '';
		this.trouble = [];
		this.consequence = '';
		this.incidence = '';
		this.possibility = '';
		this.seriousness = '';
		this.measure = '';
		this.num_l = '';
		this.num_e = '';
		this.num_c = '';
		this.num_d = '';
		this.level_name = '';
	}
};
Incidentfication.prototype = {};

var monthweek = {
	template:'#month-week',
	data:function(){
		return {
			checkForm:{
	        	domains:[{value:''}]
	        }
		};
	},
	methods:{
		addDomain(formName) {
	        this.$data.checkForm.domains.push({
	          value: '',
	          key: Date.now()
	        });
	    },
	    removeDomain(domain){
	    	var index = this.$data.checkForm.domains.indexOf(domain)
	        if (index !== -1) {
	          this.$data.checkForm.domains.splice(index, 1)
	        }
	    }
	}
};


new Vue({
	el: '#app',
	components: {monthweek,axios},
	created:function(){
		axios.get('/View/allOrgList',{params:{parentId:'0'}}).then(response=>{
			if(response.data.success === true){
				if(response.data.data.length>0){
					this.$data.topselect.orgs.value = response.data.data[0].id;
				}
				response.data.data.forEach(e=>this.$data.topselect.orgs.data.push(e));
			}else{
				this.$message.warning(response.data.msg);
			}
		}).catch(err=>{
			this.$message.error('服务器异常，请稍后再试！');
		});
	},
	data: function() {
		return {
			firstcol: '',
			dialogFormVisible: false,
			checkFormVisible: false,
			activeNames:['1'],
			form: new Incidentfication(),
			post_options: [{
					value: '工作平台',
					label: '工作平台'
				},
				{
					value: '装矿平台',
					label: '装矿平台'
				}
			],
			facotrs:[{
					value: '人的因素',
					label: '人的因素'
				},
				{
					value: '环境因素',
					label: '环境因素'
				}
			],
			troubles: [{
				key: '坍塌',
				label: '坍塌'
			}, {
				key: '其它伤害',
				label: '其它伤害'
			}],
			tableData: [],
			topselect:{
				orgs:{
					value:'',
					data:[]
				},
				date:'2019'
			}
			
		}
	},
	methods: {
		arraySpanMethod({
			row,
			column,
			rowIndex,
			columnIndex
		}) {

			if(columnIndex === 0 || columnIndex === 1) {
				if(row.union) {
					return {
						rowspan: row.union,
						colspan: 1
					};
				} else {
					return {
						rowspan: 0,
						colspan: 0
					};
				}

			}
		},
		submitForm(formName){
			this.$data.dialogFormVisible = false;
			var formData = JSON.parse(JSON.stringify(this.$data.form));
			this.$refs[formName].resetFields();
			var isNew=true,index,isAdd=false,unionIndex=0;
			var tableData = this.$data.tableData;
			for(var i=0;i<tableData.length;i++){
				if(tableData[i].union){
					if(!isNew){
						tableData[index].union = tableData[index].union + 1;
						tableData.splice(i,0,formData);
						isAdd = true;
						break;
					}
					index=i;
					unionIndex = tableData[i].index;
				}
				if(tableData[i].post_name === formData.post_name){
					isNew = false;
					continue;
				}
			}
			if(isNew == true){
				formData.union = 1;
				formData.index = unionIndex + 1;
				tableData.push(formData);
				isAdd = true;
			}
			if(isAdd == false){
				tableData[index].union = tableData[index].union + 1;
				tableData.push(formData);
			}
			
			
		},edit(row,formName){
			this.$data.form = row;
			this.$data.dialogFormVisible = true;
		},del(row){
			this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	          this.$message({
	            type: 'success',
	            message: '删除成功!'
	          });
	        }).catch(() => {
	          this.$message({
	            type: 'info',
	            message: '已取消删除'
	          });          
	        });
		},
		monthweek(formName){
			this.$data.checkFormVisible = true;
		},
		orgsChange(val){
//			alert(val);
		},
		refreshTable(){
			axios.get
		}
	}
});