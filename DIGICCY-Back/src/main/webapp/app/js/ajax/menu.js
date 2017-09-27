
$.ajax({
        url:"/menu/getMenu.do",
        type: "post",
        dataType: "json",
        cache:true,
        success: function (msg) {
            //var menu = JSON.stringify(msg.menu);
            var menu = msg.menu;
            var menuStr = "";
            console.log("1");
            $.each(menu, function (n, value) {
                //var subMenu = JSON.stringify(value.subMenu);
                menuStr = generateMenu(menuStr,value);
            });
            $("#menu").append(menuStr);
        }
});

function generateMenu(menuStr,menu) {
    if(JSON.stringify(menu.subMenu).length>0){
        menuStr = menuStr + "<li class='has-sub'><a href='"+menu.url+"' class=''>" +
            "<span class='icon-box'><i class='icon-star-empty'></i></span>" +menu.name+
            "<span class='arrow'></span></a><ul class='sub'>";
        $.each(menu.subMenu, function (n, value) {
            menuStr = menuStr + "<li><a class='' href="+value.url+">"+value.name+"</a></li>"
        });
        menuStr = menuStr + "</ul></li>"
    }else{
        menuStr = menuStr + "<li><a href="+menu.url+" class=''>" +
            "<span class='icon-box'><i class='icon-star-empty'></i></span>" +menu.name+
            "<span class='arrow'></span></a></li>";
    }
    return menuStr;
}