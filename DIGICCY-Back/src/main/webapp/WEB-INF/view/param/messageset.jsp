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
<script src="/app/js/header.js"></script>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
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
							后台参数
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">后台参数</a> <span class="divider">&nbsp;</span></li>
							<li><a href="#">后台短信频率设置</a><span class="divider-last">&nbsp;</span></li>
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
									<i class="icon-reorder"></i>后台短信频率设置信息
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">

								<table id="recharge_table" style="width: 99%">
									<tbody align="center" style="width: 100%">
										<input type="hidden" hidden="true" id="messageset_id" value="0" />
										<tr style="width: 100%">
											<td style="text-align: center; width: 50%">短信限制时间(分钟)</td>
											<td style="text-align: center; width: 50%"><input
												type="number" id="limit_date" name="limit_date"
												oninput="if(value.length>4)value=value.slice(0,4)" /></td>
										</tr>
										<tr style="width: 100%">
											<td style="text-align: center; width: 50%">短信限制次数</td>
											<td style="text-align: center; width: 50%"><input
												type="number" id="limit_number" name="limit_number"
												oninput="if(value.length>4)value=value.slice(0,4)" /></td>
										</tr>
										<tr style="width: 100%">
											<td colspan="2" style="width: 100%; text-align: center">
												<button onclick="saveMessageSet()">保存</button>
											</td>
										</tr>
										<span id="limit_name" class="limit_name" hidden="true"><sec:authentication property="name" /></span>
										<span id="limit_ip" hidden="true"> <script
												type="text/javascript">
											$('#limit_ip').text(
													returnCitySN["cip"]);
										</script></span>
									</tbody>
								</table>
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
		<!-- BEGIN FOOTER -->
		<%@include file="../footer.jsp"%>
		<!-- END FOOTER -->
		<!-- BEGIN JAVASCRIPTS -->
		<!-- Load javascripts at bottom, this will reduce page load time -->
		<script src="/app/js/footer.js"></script>
		<script src="/app/js/echarts.min.js"></script>
		<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
		<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
		<script src="/app/js/ajax/message.js"></script>
</body>
<!-- END BODY -->
</html>
