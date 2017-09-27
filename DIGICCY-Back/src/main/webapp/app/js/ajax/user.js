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
    getRoleList();
})

function getRoleList() {
    $.ajax({
        url:"/auth/getRoles.do",
        type: "get",
        dataType: "json",
        success: function (msg) {
            var arole = $("#add_role");
            var urole = $("#update_role");
            var dataJson = JSON.stringify(msg.data);
            var data = JSON.parse(dataJson);
            for(var i=0;i<data.length;i++){
                arole.append("<option value='"+data[i].id+"'>"+data[i].description+"</option>");
                urole.append("<option value='"+data[i].id+"'>"+data[i].description+"</option>");
            }
        }
    });
}

function addUser() {
    var name = $("#add_name").val();
    var pass = $("#add_pass").val();
    var role =Number($("#add_role").val());
    $.ajax({
        url:"/auth/addUser.do",
        type: "post",
        dataType: "json",
        data:{
            name:name,
            pass:pass,
            roleId:role
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

function updateUser() {
    var id = $("#update_id").val();
    var name = $("#update_name").val();
    var pass = $("#update_pass").val();
    var role = $("#update_role").val();
    $.ajax({
        url:"/auth/updateUser.do",
        type: "post",
        dataType: "json",
        data:{
            userId:id,
            name:name,
            pass:pass,
            roleId:role
        },
        success: function (msg) {
            // if(msg.code == "100"){
            $.alertable.alert(msg.desc);
            $("#box_update").css({"display":"none"});
            window.location.reload();
            // }
        }
     });
    $("#box_update").css({"display":"none"});
}