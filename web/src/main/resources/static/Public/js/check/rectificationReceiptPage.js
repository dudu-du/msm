

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
            that.data.list.forEach(function(data,index){
                that.data.list[index].investigationTime=that.data.list[index].investigationTime[0]+"年"+that.data.list[index].investigationTime[1]+"月"+that.data.list[index].investigationTime[2]+"日";
            });
        });
    },
    data:function(){
        return {
            data: []
        }
    },
    methods:{
        next(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].investigationTime=that.data.list[index].investigationTime[0]+"年"+that.data.list[index].investigationTime[1]+"月"+that.data.list[index].investigationTime[2]+"日";
                });
            });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
                that.data.list.forEach(function(data,index){
                    that.data.list[index].investigationTime=that.data.list[index].investigationTime[0]+"年"+that.data.list[index].investigationTime[1]+"月"+that.data.list[index].investigationTime[2]+"日";
                });
            });
        }
}
});