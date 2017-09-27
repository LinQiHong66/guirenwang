window.onload=function() {
    var bUl = document.getElementById('slId');
    bUl.addEventListener('touchstart', touch, false);
    var tStart = 0, tOstart = 0;

    function touch(event) {
        var event = event || window.event;
        switch (event.type) {
            case "touchstart":
                tStart = event.touches[0].clientX;
                tOstart = bUl.offsetLeft;
                console.log(tStart);
                bUl.addEventListener('touchmove', touch, false);
                break;
            case "touchend":
                break;
            case "touchmove":
                if (bUl.offsetLeft <= 0) {
                    bUl.style.left = tOstart + (event.touches[0].clientX - tStart) + 'px';
                    if (bUl.offsetLeft >= 0) {
                        bUl.style.left = '0px';
                    }
                }
                if (bUl.offsetLeft < -(bUl.offsetWidth - window.screen.width)) {
                    bUl.style.left = -(bUl.offsetWidth - window.screen.width) + 'px';
                }
                break;
        }
    }
    var flag=true;
    $('#close').click(function(){
        if(flag){
            $('#ul2').slideToggle(400);
            // alert(1);
            $('#close').css({'transform':'rotate(180deg)'});
            flag=!flag;
        }else{
            $('#ul2').slideDown(400);
            // alert(1);
            $('#close').css({'transform':'rotate(0deg)'});
            flag=!flag;
        }
    })
};