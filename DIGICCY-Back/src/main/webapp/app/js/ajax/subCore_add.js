/**
 * Created by Administrator on 2016/12/1 0001.
 */
$(function () {
    $.ajax({
        url:"/coin/getAllCoin.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var coin = $("#coin");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                coin.append("<option value='"+data[i].coin_no+"'>"+data[i].coin_name+"</option>");
            }
        }
    });
})

$(document).ready(function () {
    $("#coin").change(function(){
        var coinType = $('#coin').val();
        $.ajax({
            url:"/coin/checkCoinType.do",
            type:"post",
            dataType:"json",
            data:{
                coin:coinType
            },
            success : function (data) {
                if(data.code == "200"){
                    document.getElementById("span").style.display = "block";
                }else{
                    document.getElementById("span").style.display = "none";
                }
            },
            error : function (data) {
                alert("查询币种是否存在失败");
            }
        })
    });
});

/*
function add() {
    var sub_name = $('#name').val();
    var coin_type = $('#coin').val();
    var price = $('#price').val();
    var num = $('#num').val();
    var limit_buy = $('#limit').val();
    var thaw_num = $('#thaw').val();
    var cycle = $('#cycle').val();
    $.ajax({
        url:"/subCore/addSubCore.do",
        type: "post",
        dataType: "json",
        data:{
            sub_name:sub_name,
            coin_type:coin_type,
            price:price,
            num:num,
            limit_buy:limit_buy,
            thaw_num:thaw_num,
            cycle:cycle
        },
        success: function (data) {
            alert(data.desc);
            window.location.href = '/subCore/gotoSubCore.do';
        }
    });
}*/
