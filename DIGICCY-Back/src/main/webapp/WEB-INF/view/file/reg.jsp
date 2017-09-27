<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../taglib.jsp" %>
<!DOCTYPE html>
<!--
Template Name: Admin Lab Dashboard build with Bootstrap v2.3.1
Template Version: 1.2
Author: Mosaddek Hossain
Website: http://thevectorlab.net/
-->

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>主页</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <script src="/app/js/header.js"></script>
    <link href="/app/assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/app/css/tab.css" rel="stylesheet" />
    <%--<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--<link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />--%>
    <%--<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />--%>
    <%--<link href="css/style.css" rel="stylesheet" />--%>
    <%--<link href="css/style_responsive.css" rel="stylesheet" />--%>
    <%--<link href="css/style_default.css" rel="stylesheet" id="style_color" />--%>

    <%--<link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />--%>
    <%--<link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />--%>
    <%--<link href="assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />--%>
    <%--<link href="assets/jqvmap/jqvmap/jqvmap.css" media="screen" rel="stylesheet" type="text/css" />--%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">

<!-- BEGIN HEADER -->
<%@include file="../header.jsp" %>
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <%@include file="../left.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <h3 class="page-title">
                        批量注册
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">批量处理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">批量注册</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>批量注册</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body" align="center" align="center">
                          	<a href="/file/getFile.do?fileType=regTemplate">获取批量注册模板</a>
                          	<!-- 批量处理表单 -->
                          	<form id="regform" method="post" enctype="multipart/form-data">
								<input type="file" name="excelFile" onchange="changeExcel()" /><br />
								<input type="hidden" name="fileType" value="regTemplate" /><br />
								
								<input id="check_cb" type="checkbox" disabled="disabled" name="addAddress" />是否添加用户地址
							</form>
							<span id="executeableInfo"></span><br />
							<button onclick="checkExcel()">检验表单</button>
							
							<div id="bachReg" style="display: none">
								<button onclick="startReg()">开始注册</button>
								<button onclick="showResult()">查看结果</button><a href="/file/getResultExcel.do?fileType=regTemplate">导出结果</a><br />
								
								注：<br />
								查看到的结果是点击按钮时刻的结果，可能不是最终结果！
								<span id="regResult">
								</span>
							</div>
							
							<br /><br /><br />
							<span id="showInfo"></span>
							<!-- 获取结果表单 -->
							<!-- <form action="file/getResult.do" method="post">
								<input type="text" name="userName" /><br />
								<input type="hidden" name="fileType" value="regTemplate" /><br />
								<input type="submit" />
							</form> -->
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- END PAGE -->
</div>
<%@include file="../footer.jsp" %>
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/js/ajax/regFile.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
</body>
<!-- END BODY -->
</html>
