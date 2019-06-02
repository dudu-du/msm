

new Vue({
    el:'#app',
    components: {axios},
    created:function(){
        var that = this;
        axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:1,pageSize:10}}).then(function(res){
            that.data = res.data.data;
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
            });
        },prev(currentPage,pageSize){
            var that = this;
            axios.get('/safety/checkRectificationReceipt/checkRectificationReceiptByPage',{params:{currentPage:currentPage,pageSize:pageSize}}).then(function(res){
                that.data = res.data.data;
            });
        },
        openPrint(row){
            window.open("/safety/checkRectificationReceipt/checkRectificationReceiptPrint?"+row.id);
        }
}
});