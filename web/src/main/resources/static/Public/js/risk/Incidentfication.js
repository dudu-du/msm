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

new Vue({
	el: '#app',
	data: function() {
		return {
			firstcol: '',
			dialogFormVisible: false,
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
			tableData: []
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
				console.log("add new");
			}
			if(isAdd == false){
				console.log("add same");
				tableData[index].union = tableData[index].union + 1;
				tableData.push(formData);
			}
			
			
		}
	}
});