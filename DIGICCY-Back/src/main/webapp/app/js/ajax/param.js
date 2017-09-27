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
})

function addParam() {
    var param = $("#add_param").val();
    var value = $("#add_value").val();
    $.ajax({
        url:"/param/addStaticParam.do",
        type: "post",
        dataType: "json",
        data:{
            param:param,
            value:value
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

function updateParam() {
    var id = $("#param_id").val();
    var param = $("#update_param").val();
    var value = $("#update_value").val();
    $.ajax({
        url:"/param/updateStaticParam.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            param:param,
            value:value
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