/**
 * Created by Administrator on 2016/12/1 0001.
 */
$(function () {
    $("#coin option[value='1']").attr("selected","selected");
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
    $.ajax({
        url:"/subCore/getSubCoreById.do?id="+getQueryString('id'),
        type: "get",
        dataType: "json",
        success: function (msg) {
            var dataJson = msg.data;
            $('#name').val(dataJson.sub_name);
            $('#price').val(dataJson.price);
            $('#num').val(dataJson.num);
            $('#limit').val(dataJson.limit_buy);
            $('#thaw').val(dataJson.thaw_num);
            $('#cycle').val(dataJson.cycle);
            $("#coin option").each(function (){
                if($(this).val() == dataJson.coin_type){
                    $("#coin option[value='1']").removeAttr("selected");
                    $("#coin option[value='"+dataJson.coin_type+"']").attr("selected","selected");
                }
            });

        }
    });
})

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}


function update() {
    var sub_name = $('#name').val();
    var coin_type = $('#coin').val();
    var price = $('#price').val();
    var num = $('#num').val();
    var limit_buy = $('#limit').val();
    var thaw_num = $('#thaw').val();
    var cycle = $('#cycle').val();
    var status = $('input:radio[name="sub_status"]:checked').val();
    $.ajax({
        url:"/subCore/updateSubCore.do",
        type: "post",
        dataType: "json",
        data:{
            id:getQueryString('id'),
            sub_name:sub_name,
            coin_type:coin_type,
            price:price,
            num:num,
            limit_buy:limit_buy,
            thaw_num:thaw_num,
            cycle:cycle,
            status:status
        },
        success: function (data) {
            alert(data.desc);
            window.location.href = '/subCore/gotoSubCore.do';
        }
    });
}