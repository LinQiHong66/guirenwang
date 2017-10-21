<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>
<!--
Template Name: Admin Lab Dashboard build with Bootstrap v2.3.1
Template Version: 1.2
Author: Mosaddek Hossain
Website: http://thevectorlab.net/
-->

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>主页</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<script src="/app/js/jquery-1.8.3.min.js"></script>
<script src="/app/js/layer/layer.js"></script>
<script src="/app/js/header.js"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
<link href="/app/css/tab.css" rel="stylesheet" />

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">

	<!-- BEGIN HEADER -->
	<%@include file="../header.jsp"%>
	<!-- BEGIN CONTAINER -->
	<div id="container" class="row-fluid">
		<!-- BEGIN SIDEBAR -->
		<%@include file="../left.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div id="main-content">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<h3 class="page-title">
							所有委托记录
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">委托记录管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">所有委托记录</a><span class="divider-last">&nbsp;</span></li>
						</ul>
					</div>
				</div>
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12 sortable">
						<!-- BEGIN SAMPLE FORMPORTLET-->
						<div class="widget">
							<div class="widget-title">
								<h4>
									<i class="icon-reorder"></i>所有委托记录
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div> 
							<div class="widget-body">                 
								<form action="/entrust/getExcel.do" method="GET">
									<div>
										用户编号 : <input id="userCode" name="userCode" style="width:5%;"/>	
										手机号码 : <input id="phone" name="phone"  style="width:5%;"/>
                            			真实姓名 : <input id="realName" name="realName"  style="width:5%;"/>
										状态<select style='width: 20%' name='state' id='state'>
											<option value='-1' selected>请选择</option>
											<option value='1'>已完成</option>
											<option value='0'>委托中</option>
											<option value='2'>撤销</option>
										</select>
										时间段<input type='date' style='width: 20%' name='startDate'
											id='startDate' />---<input style='width: 20%' type='date'
											name='endDate' id='endDate' /> <br /> <input type="button"
											value='查询' onclick='selectEntrust();' /><input type="submit"
											value='导出Excel' />
									</div>
								</form>
								<table id="entrust_all_table" style='width:99%'>

								</table>
								<div align="center" style="width:100%">
									总条数：<span id='countPage'></span>当前页数：第<span id='pageNum'></span>页，选择每页条数<select onchange="selectEntrust(1);" style='width:70px' id='pageItem'></select>&nbsp;&nbsp;&nbsp;
										<input type='button' value='上一页' onclick="beforePage();" />
										<span id='numbers'></span>
										<input type="button" value='下一页' onclick="nextPage();">
								</div>
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
	<!-- END CONTAINER -->

	<!-- BEGIN JAVASCRIPTS -->
	<!-- Load javascripts at bottom, this will reduce page load time -->
	<script src="/app/js/footer.js"></script>
	<script src="/app/js/echarts.min.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
	<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
	<script src="/app/js/bootstrap-table/entrust-tab.js"></script>
	<script type="text/javascript" src="/app/assets/bootstrap/js/bootstrap-fileupload.js"></script>
 
</body>

<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp"%>
<!-- END FOOTER -->