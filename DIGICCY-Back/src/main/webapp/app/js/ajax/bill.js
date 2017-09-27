$(function () {
    $('#bill').hide();
    $('#name').show();
    $.ajax({
        url:"/bill/updateBillRechargeRecord.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var bill = $("#bill");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                coin.append("<option value='"+data[i].coin_no+"'>"+data[i].coin_name+"</option>");
            }
        }
    });
})

