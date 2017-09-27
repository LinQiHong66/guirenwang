<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String url = request.getContextPath() + "/crowFunding/addCrowdFunding.do" ;
	%>
	<form action="<%=url%>" method="post" enctype="multipart/form-data">
		icoName<input type="text" name="icoName" id="icoName"><br>
		icoTarget<input type="text" name="icoTarget" id="icoTarget"><br>
		icoPrice<input type="text" name="icoPrice" id="icoPrice"><br>
		icoRemark<input type="text" name="icoRemark" id="icoRemark"><br>
		上传<input type="file" name="phoneFile" id="phoneFile"><br>
		<input type="submit" value="上传">
	</form>
</body>
</html>