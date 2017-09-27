<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>username : <sec:authentication property="name"/></div>
<a href="/j_spring_security_logout"><button>退出登录</button></a>
<a href="/notice/selectNotice.do"><button>公告</button></a>
<a href="/dayMarket/selectDayMarket.do"><button>每日行情</button></a>

<form action="/inesvuser/updateInesvUser.do" method="post">
    原始密码：<input type="password" name="password1" id="password1"><br/><br/>
    输新密码：<input type="password" name="password" id="password"><br/><br/>
    <%--重复密码：<input type="password" name="password2" id="password2"><br/><br/>--%>
    <input type="submit" value="修改密码">
</form>

<form action="/inesvuser/updateDealPwd.do" method="post">
    原始交易密码：<input type="password" name="pealpwd1" id="pealpwd1"><br/><br/>
    输新交易密码：<input type="password" name="pealpwd" id="pealpwd"><br/><br/>
    <%--    重复密码：<input type="password" name="password2" id="password2"><br/><br/>--%>
    <input type="submit" value="修改交易密码">
</form>

<form action="/inesvuser/updatePhone.do" method="post">
    绑定手机号：<input type="text" name="phone" id="phone"><br/><br/>
    <%--    重复密码：<input type="password" name="password2" id="password2"><br/><br/>--%>
    <input type="submit" value="绑定手机">
</form>

<form action="/inesvuser/updateAlipay.do" method="post">
    绑定支付宝：<input type="text" name="alipay" id="alipay"><br/><br/>
    <%--    重复密码：<input type="password" name="password2" id="password2"><br/><br/>--%>
    <input type="submit" value="绑定支付宝">
</form>
<form action="/inesvuser/upPwdState.do" method="post">
    <input type="text" name="pwdState" id="pwdState">
    <input type="text" name="dealPwd1" id="dealPwd1"><br/><br/>
    <input type="submit" value="保存">
</form>

<form action="/inesvuser/upValidatePwd.do" method="post">
   <%-- <input type="text" name="pwdState" id="pwdState">--%>
    <input type="text" name="validate_pwd" id="validate_pwd"><br/><br/>
    <input type="submit" value="保存">
</form>

<form action="/inesvuser/authenticationUser.do" method="post" enctype="multipart/form-data">
    图片:<input name="myfiles" type="file" id="myfiles"><br />
    <input type="submit" value="实名认证">
</form>

<form action="/inervbank/delete.do" method="post">
    <input type="text" value="9" name="id" id="id">
    <input type="submit" value="删除">
</form>

<form action="/inervbank/bankinfo.do" method="post">
    备注名称：<input type="text" name="remark_name" id="remark_name"><br/>
    开户银行：<input type="text" name="bank" id="bank"><br/>
    开户省份：<input type="text" name="province" id="province"><br/>
    开户城市：<input type="text" name="city" id="city"><br/>
    开户支行：<input type="text" name="branch" id="branch"><br/>
    开户姓名：<input type="text" name="name" id="name"><br/>
    银行卡号：<input type="text" name="bank_num" id="bank_num"><br/>
    <input type="submit" value="保存">
</form>

</body>
</html>
