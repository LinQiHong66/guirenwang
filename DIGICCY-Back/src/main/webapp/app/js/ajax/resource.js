$(function () {
    $("#btn_add").click(function () {
        $("#box_add").css({"display":"block"});
    });
    $("#close_add").click(function () {
        $("#box_add").css({"display":"none"});
    })
    // $("#btn_update").click(function () {
    //     $("#box_update").css({"display":"block"});
    // });
    $("#close_update").click(function () {
        $("#box_update").css({"display":"none"});
    })
    getTopMenu();
})

function getTopMenu() {
    $.ajax({
        url:"/auth/getRes.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var topMenu = $("#top_menu");
            var updateMenu = $("#update_menu");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                topMenu.append("<option value='"+data[i].id+"'>"+data[i].type+"</option>");
                updateMenu.append("<option value='"+data[i].id+"'>"+data[i].type+"</option>");
            }
        }
    });
}

function addRes() {
    var type = $("#res_type").val();
    var value = $("#res_value").val();
    var parent = $("#top_menu").val();
    var common = $('input[name="add_radio"]:checked').val();
    $.ajax({
        url:"/auth/addRes.do",
        type: "post",
        dataType: "json",
        data:{
            type:type,
            value:value,
            parent:parent,
            common:common
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

function updateRes() {
    var id = $("#update_id").val();
    var type = $("#update_type").val();
    var value = $("#update_value").val();
    var parent = $("#update_menu").val();
    var common = $('input[name="update_radio"]:checked').val();
    $.ajax({
        url:"/auth/updateRes.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            type:type,
            value:value,
            parent:parent,
            common:common
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