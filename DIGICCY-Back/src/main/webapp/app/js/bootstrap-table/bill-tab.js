$("#bill_update_table").bootstrapTable({
    // search:true,
    pagination:true,
    pageList: [10, 25, 50, 100],
    pageSize:10,
    // sortable: true, //是否启用排序
    // sortOrder: "asc", //排序方式
    url:'/bill/getAllBill.do',
    search: true,// 数据源
    // strictSearch: true,
    // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
    // 设置为limit可以获取limit, offset, search, sort, order
    /*
     * queryParamsType : "undefined", queryParams: function
     * queryParams(params) { // 设置查询参数 var param = { // limit: params.limit,
     * //第几条记录 pageIndex: params.pageNumber, pageSize: params.pageSize,
     * search:params.search }; return param; },
     */
    // server
    sidePagination: "client", // 服务端请求
    idField:'id',
    cache:false,
    columns:[[// 列
        {title:'用户编号',field:'user_no',width:10,align:'center'},
        {title:'电话号码',field:'recharge_phone',width:10,align:'center'},
        {title:'充值金额',field:'recharge_price',width:10,align:'center'},
        {title:'支付类型',field:'pay_type',width:10,align:'center'},
        {title:'支付金额',field:'pay_price',width:10,align:'center'},
        {title:'处理日期',field:'handle_date',width:10,align:'center'},
        {title:'充值状态',field:'state',width:10,align:'center',formatter:function(value){
            if(value == 1) return "已充值";
            if(value == 0) return "未充值";
        }},
        {title:'充值日期',field:'date',width:10,align:'center'},
        {title:'操作',field:'id',width:120,align:'center',
            formatter : function(value, row, index) {
                //value => id, row.recharge_phone ==> pohone
                var id = value;
                var phone = row.recharge_phone;
                var e;
                if(row.state == 1) return e;
                e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" onclick="updateBillState('+id+','+phone+');">' +
                    '<i class="icon-pencil icon-white"></i>充值</button> &nbsp;&nbsp;';
                return e;
            }},
    ]]
});

function updateBillState(id,phone) {
    if(id == null || id == ''){
        return false;
    }
    if(phone == null || phone == ''){
        return false;
    }

    layer.confirm('确认充值?', {icon: 3, title:'提示'}, function(index){
        //do something
        var load = layer.load(2);
        var url = '/bill/updateBillRechargeRecord.do';
        $.post(url,{recharge_phone:phone,id:id},function (e) {
            layer.close(load);
            console.log(e);
            if(e.msg == 200){
                //success
                layer.msg("充值成功");
                window.location.reload();
            }else{
                //fail
                layer.msg("充值失败");
            }
        });
        layer.close(index);
    });



}
