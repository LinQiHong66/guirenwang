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
    $("#close_res").click(function () {
        $("#box_res").css({"display":"none"});
    })
})

function addRole() {
    var name = $("#role_name").val();
    var desc = $("#role_desc").val();
    $.ajax({
        url:"/auth/addRole.do",
        type: "post",
        dataType: "json",
        data:{
            name:name,
            desc:desc
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

function updateRole() {
    var id = $("#update_id").val();
    var name = $("#update_name").val();
    var desc = $("#update_desc").val();
    $.ajax({
        url:"/auth/updateRole.do",
        type: "post",
        dataType: "json",
        data:{
            id:id,
            name:name,
            desc:desc
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

function updateRes() {
    var res = $("#role_res").val();
    var role = $("#rolename").val();
    if(res != ""){
        $.ajax({
            url:"/auth/updateRoleRes.do",
            type: "post",
            dataType: "json",
            data:{
                roleId:Number(role),
                resources:res
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
    $("#box_res").css({"display":"none"});
}