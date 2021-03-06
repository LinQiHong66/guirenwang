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
<script src="/app/js/header.js"></script>
<link href="/app/assets/bootstrap-table/bootstrap-table.css"
	rel="stylesheet" />
<link href="/app/css/chosen.css" rel="stylesheet" />
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
							ICO众筹管理
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">ICO众筹管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">用户众筹列表</a><span class="divider-last">&nbsp;</span>
							</li>
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
									<i class="icon-reorder"></i>用户众筹列表
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">
								<a href="/crowFundingDetail/excelDetail.do"><button
										class="btn btn-success" id="btn_add">
										<i class="icon-plus icon-white"></i>&nbsp;导出Excel
									</button></a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="btn btn-success">&nbsp;总购酒数量:${crowd3}</button>
								<button class="btn btn-success">&nbsp;今日购酒数量:${crowd4}</button>
								<button class="btn btn-success">&nbsp;总贵人通数量:${crowd1}</button>
								<button class="btn btn-success">&nbsp;今日贵人通数量:${crowd2}</button>
								<div class="row-fluid">
									<div class="span12">
										<!--BEGIN TABS-->
										<div class="tabbable tabbable-custom">
											<ul class="nav nav-tabs">
												<li class="active"><a href="#tab_1_1" onclick=""
													data-toggle="tab">众筹详情记录</a></li>
												<li><a href="#tab_1_2" id="aaa" data-toggle="tab">机构TOP-50</a></li>
												<li><a href="#tab_1_3" id="bbb" data-toggle="tab">用户TOP-50</a></li>
												<li><a href="#tab_1_4" id="ccc" data-toggle="tab">机构会员众筹信息</a>
													<%-- <select class="input-large m-wrap" tabindex="1"
													id="orgCode" name="orgCode">
														<c:forEach items="${userOrgCode}" var="code">
															<option value="${code.org_code}">${code.org_code}</option>
														</c:forEach>
												</select> --%></li>
											</ul>
											<div class="tab-content">
												<div class="tab-pane active" id="tab_1_1">

													<table id="crowdDetail_table">
														<div>
															查询条件： <br /> 用户编号：<input id="userOrgCode" type="text" />手机号：<input
																id="phone" type="text" />姓名：<input id="userName"
																type="text" />购酒日期<input id="startDate" type="date" />-<input
																id="endDate" type="date" />项目类型：<select id="icoId">
																<option value="">请选择</option>
															</select><br />
															<button onclick="updateLogistics()">查询</button>
														</div>
														<!-- <div style="float:left;margin-top:10px">输入需要更新的订单ID：<input type="text" id="a_id" style="height:17px;margin-bottom:0;" /> <button type="button"   style="height: 27px;
											    line-height: 27px;
											    padding: 0 10px;
											    background-color: #25c3cf;
											    color: #fff;
											    box-shadow: 1px 1px 2px #000;" class="btn btn-default" onclick="updateLogistics();">更新</button>
											    </div> -->
													</table>
												</div>
												<div class="tab-pane" id="tab_1_2">
													<table id="crowdDetail_table_jigou"></table>
												</div>
												<div class="tab-pane" id="tab_1_3">
													<table id="crowdDetail_table_user"></table>
												</div>
												<div class="tab-pane" id="tab_1_4">
													<table id="crowdDetail_table_jigou_detail"></table>
												</div>
											</div>
										</div>
										<!--END TABS-->
									</div>
								</div>
								<!-- <table id="crowdDetail_table">
                            </table> -->
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

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="height: 250px;">
				<div class="modal-header">
					添加任务
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" style="height: 150px;">
					<form action="" id="submitForm">
						<div class="form-group" align="center">

							<div class="col-sm-10">
								<label for="firstname" class="col-sm-2 control-label">快递公司名称</label>
								<select name="dept" data-placeholder="" id="selectId"
									class="form-control">
									<option value='1'>请选择或者搜索</option>
								</select>
							</div>
						</div>
						<div class="form-group" align="center">
							<label for="lastname" class="col-sm-2 control-label">快递单号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="numValue"
									placeholder="请输入快递单号"> <input type="hidden"
									class="form-control" id="numId" placeholder="请输入快递单号">
							</div>
						</div>
					</form>
					<div align="center" id="errInput" style="color: red;"></div>
				</div>
				<div class="modal-footer">
					<button id="closeW" type="button" class="btn btn-default"
						data-dismiss="modal">关闭</button>
					<button type="button" id="submit" onclick="submitWl();"
						class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
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
	<script src="/app/js/bootstrap-table/crowdDetail-tab.js"></script>
	<script src="/app/js/ajax/crowd.js"></script>
	<script src="/app/js/ajax/choosen.js"></script>


	<%--<script src="js/jquery-1.8.3.min.js"></script>--%>
	<%--<script src="assets/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script>--%>
	<%--<script src="assets/jquery-slimscroll/jquery.slimscroll.min.js"></script>--%>
	<%--<script src="assets/fullcalendar/fullcalendar/fullcalendar.min.js"></script>--%>
	<%--<script src="assets/bootstrap/js/bootstrap.min.js"></script>--%>
	<%--<script src="js/jquery.blockui.js"></script>--%>
	<%--<script src="js/jquery.cookie.js"></script>--%>
	<%--<!-- ie8 fixes -->--%>
	<%--<!--[if lt IE 9]>--%>
	<%--<script src="js/excanvas.js"></script>--%>
	<%--<script src="js/respond.js"></script>--%>
	<%--<![endif]-->--%>
	<%--<script src="assets/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>--%>
	<%--<script src="assets/jquery-knob/js/jquery.knob.js"></script>--%>
	<%--<script src="assets/flot/jquery.flot.js"></script>--%>
	<%--<script src="assets/flot/jquery.flot.resize.js"></script>--%>

	<%--<script src="assets/flot/jquery.flot.pie.js"></script>--%>
	<%--<script src="assets/flot/jquery.flot.stack.js"></script>--%>
	<%--<script src="assets/flot/jquery.flot.crosshair.js"></script>--%>

	<%--<script src="js/jquery.peity.min.js"></script>--%>
	<%--<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>--%>
	<%--<script src="js/scripts.js"></script>--%>
	<%--<script>--%>
	<%--jQuery(document).ready(function() {--%>
	<%--// initiate layout and plugins--%>
	<%--App.setMainPage(true);--%>
	<%--App.init();--%>
	<%--});--%>
	<%--</script>--%>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
