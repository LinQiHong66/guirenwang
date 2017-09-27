<%--
  Created by IntelliJ IDEA.
  User: SKINK
  Date: 2016/10/21
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/j_spring_security_check" method="post">
            用户名:<input name="j_username" type="text"><br />
            密码:<input name="j_password" type="password"><br />
            <input type="checkbox" name="_spring_security_remember_me"/>两周内免登陆
            <input type="submit" value="login">
        </form>
</body>
</html>
