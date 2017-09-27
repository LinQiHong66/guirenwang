<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Fullscreen Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="app/css/reset.css">
        <link rel="stylesheet" href="app/css/supersized.css">
        <link rel="stylesheet" href="app/css/login.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>
        <div class="page-container">
            <%--<h1>多币种数字货币</h1>--%>
            <%--<h1>交易平台</h1>--%>
            <img src="/app/img/login_logo.png">
            <form id="f" action="/j_spring_security_check" onsubmit="onsub()" method="post">
				<div style="margin-top:15px";>
                    <span style="float: left">账号:</span>
					<input type="text" name="j_username" class="username" placeholder="Username" autocomplete="off"/>
				</div>
                <div style="margin-top:15px;">
                    <span style="float: left">密码:</span>
					<input type="password" name="j_password" class="password" placeholder="Password"  />
                </div>
                <div style="margin-top:15px;">
                    <span style="float: left">验证码:</span>
                    <label id="Jacodan">
                        <input type="text" name="code" id="code" placeholder="Code" style="width: 60%;"/>
                        <a href="javascript:;" id="checkCode" class="code" onclick="createCode();" style="display:inline-block"></a>
                    </label>
                </div>
                <button id="submit" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                <div id="ts">${ErrorMessage}</div>
            </form>
            <div class="footer">
                <p> 2016 &copy; 宏强网络</p>
            </div>
        </div>

        <!-- Javascript -->



		<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
        <script src="app/js/supersized.3.2.7.min.js"></script>
        <script src="app/js/supersized-init.js"></script>
		<script>
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
			
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }

        var code; //在全局 定义验证码
        function createCode() {
            code = new Array();
            var codeLength = 4;//验证码的长度
            var checkCode = document.getElementById("checkCode");
            checkCode.innerHTML = "";
            var selectChar = new Array(2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C',
                    'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
                    'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

            for (var i = 0; i < codeLength; i++) {
                var charIndex = Math.floor(Math.random() * 32);
                code += selectChar[charIndex];
            }
            if (code.length != codeLength) {
                createCode();
            } else {
                checkCode.innerHTML = code;
            }

        }

        function validate() {
            var inputCode = document.getElementById("code").value.toUpperCase();
            if (inputCode.length <= 0) {
                return false;
            } else if (inputCode != code || inputCode.length != 4) {
                createCode();
                return false;
            } else {
                return true;
            }
        }
        createCode();
		</script>
    </body>

</html>

