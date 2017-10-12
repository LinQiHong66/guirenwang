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
							用户权限访问记录
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#"> 用户管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">用户权限访问记录</a><span class="divider-last">&nbsp;</span></li>
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
									<i class="icon-reorder"></i>用户权限访问记录
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">
								<div>
									查询条件：<br /> 用户名：<input type="text" name="userName"
										id="userName" style="width: 10%" />权限信息：<input type="text"
										name="powerInfo" id="powerInfo" style="width: 10%" />权限url：<input
										type="text" name="powerUrl" id="powerUrl" style="width: 10%" />时间段：<input
										type="date" name="startDate" id="startDate" style="width: 10%" />-<input
										type="date" name="endDate" id="endDate" style="width: 10%" /><br />
									<button onclick="searchByItems()">查询</button>
								</div>
								<table id="power_table" style="width: 100%">

								</table>
								<div align="center">
									一共<span id="countItem"></span>条数据，当前页数：<span id="curPage"></span>，每页<select
										style="width: 5%" id="pageItem" onchange="checkPageItem()">

									</select>条数据。
									<button onclick="overPage()">上一页</button>
									<button onclick="nextPage()">下一页</button>
								</div>
								<span hidden='true' id='orderName'>id</span>
								<span hidden='true' id='orderType'>down</span>
							</div>
						</div>
						<!-- END SAMPLE FORM PORTLET-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
	<!-- 模态框 -->
	<div class="modal fade" id="showInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="infoTitle"></h4>
            </div>
            <div class="modal-body">
            	<span id="infoLoad">正在加载，请稍等.....</span>
            	<table id="tab_powerInfo" style="width:100%"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
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
	<script type="text/javascript" src="/app/js/ajax/powerLog.js"></script>
	<script type="text/javascript">
		//下一页
		function nextPage() {
			var countItem = $('#countItem').html();
			var pageItem = $('#pageItem').val();
			var curPage = $('#curPage').html();
			var maxPage = countItem / pageItem;
			
			var orderName = $('#orderName').html();
			var orderType = $('#orderType').html();
			
			if (countItem % pageItem != 0) {
				maxPage = maxPage + 1;
			}
			curPage = curPage / 1 + 1;
			if (curPage > maxPage) {
				curPage = curPage - 1;
			}
			getCurPageInfos(pageItem, curPage, orderName, orderType);
		}
		//上一页
		function overPage() {
			var pageItem = $('#pageItem').val();
			var curPage = $('#curPage').html();
			var orderName = $('#orderName').html();
			var orderType = $('#orderType').html();
			curPage = curPage / 1 - 1;
			if (curPage <= 0) {
				curPage = curPage / 1 + 1;
			}
			getCurPageInfos(pageItem, curPage, orderName, orderType);
		}
		//切换每页条目
		function checkPageItem() {
			var pageItem = $('#pageItem').val();
			var orderName = $('#orderName').html();
			var orderType = $('#orderType').html();

			getCurPageInfos(pageItem, 1, orderName, orderType);
		}
		//查询
		function searchByItems() {
			var orderName = $('#orderName').html();
			var orderType = $('#orderType').html();
			getCurPageInfos(10, 1, orderName, orderType);
		}
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
