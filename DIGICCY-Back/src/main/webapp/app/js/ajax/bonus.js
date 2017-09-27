$(function () {
    $("#btn_add").click(function () {
        $("#box_add").css({"display":"block"});
    });
    $("#close_add").click(function () {
        $("#box_add").css({"display":"none"});
    });
    $("#close_update").click(function () {
        $("#box_update").css({"display":"none"});
    });
    $.ajax({
        url:"/bonus/getCoinCount.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var coin = $("#coin");
            var up_coin = $("#up_coin");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                coin.append("<option value='"+data[i].coinId+"'>"+data[i].coinname+"(全站持有"+data[i].total+")</option>");
                up_coin.append("<option value='"+data[i].coinId+"'>"+data[i].coinname+"(全站持有"+data[i].total+")</option>");
            }
        }
    });
})

function addBonus() {
    var name = $("#name").val();
    var num = $("#num").val();
    var coin = $("#coin").val();
    $.ajax({
        url:"/bonus/addBonusDetail.do",
        type: "post",
        dataType: "json",
        data:{
            bonusName:name,
            coin:coin,
            num:num
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            $("#box_add").css({"display":"none"});
            window.location.reload();
            // }
        }
    });
}

function updateBonus() {
    var id = $("#up_id").val();
    var name = $("#up_name").val();
    var num = $("#up_num").val();
    var coin = $("#up_coin").val();
    $.ajax({
        url:"/bonus/updateBonusDetail.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            bonusName:name,
            coin:coin,
            num:num
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            $("#box_update").css({"display":"none"});
            window.location.reload();
            // }
        }
    });
}