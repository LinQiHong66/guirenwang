
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../taglib.jsp"%>
<!DOCTYPE html>

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
							公告管理
							<%--<small>simple form layouts</small>--%>
						</h3>
						<ul class="breadcrumb">
							<li><a href="#"><i class="icon-home"></i></a><span
								class="divider">&nbsp;</span></li>
							<li><a href="#">公告管理</a> <span class="divider">&nbsp;</span>
							</li>
							<li><a href="#">公告类型管理</a><span class="divider-last">&nbsp;</span></li>
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
									<i class="icon-reorder"></i>公告类型
								</h4>
								<span class="tools"> <a href="javascript:;"
									class="icon-chevron-down"></a> <a href="javascript:;"
									class="icon-remove"></a>
								</span>
							</div>
							<div class="widget-body">
								<%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
								<%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
								<table style="width: 100%;">
									<thead style='width: 100%;'>
										<tr>
											<th>id</th>
											<th>类型名称</th>
											<th>操作</th>
										</tr>
										<tr style='width: 100%;'>
											<td style='text-align: center'></td>
											<td style='text-align: center'><input type='text'
												id="newType" /></td>
											<td style='text-align: center'><button
													onclick='addItem()'>新增</button></td>
										</tr>
									</thead>
									<tbody style='width: 100%;' id="notice_table">

									</tbody>
								</table>
							</div>
						</div>
						<!-- END SAMPLE FORM PORTLET-->
					</div>
				</div>
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->
		<div id="modifymodal" class="modal hide fade in"
			style="display: none;">

			<form action="/notice/modifyNameType.do" method="POST">
				<div class="modal-header">
					<a class="close" data-dismiss="modal">×</a>
					<h3>修改类型</h3>
				</div>
				<div class="modal-body">
					<div id='success'>
						<input type="hidden" name="id" id="mid" /> 类型名<input type='text'
							name="name" id="mname" />
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" value="修改" class="btn btn-success" /> <a
						href="#" class="btn" data-dismiss="modal">取消</a>
				</div>

			</form>
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
	<script type="text/javascript">
		function addItem() {
			var ntype = $('#newType').val();
			$.ajax({
				url : '/notice/addNameType.do',
				data : {
					name : ntype
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.code == 100) {
						alert('添加成功');
					} else {
						alert(data.desc);
					}
					window.location.href = "/notice/gotoNoticeType.do";
				}
			});
		}
		function modifyItem(id) {
			$.ajax({
				url : '/notice/findTypeById.do',
				data : {
					id : id
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					var val = data.data;
					if (val != "none") {
						$('#mname').attr('value', val.name);
						$('#mid').attr('value', id);
					}
				}
			});
		}
		function deltype(id){
			$.ajax({
				url:'/notice/deleteNameType.do',
				type:'post',
				dataType:'json',
				data:{
					id:id
				},
				success:function(data){
					if(data.code == 100){
						alert('删除成功');
					}else{
						alert('删除失败');
					}
					window.location.href = "/notice/gotoNoticeType.do";
				}
			});
		}
		$(function() {
			$
					.ajax({
						url : '/notice/getNameType.do',
						type : 'post',
						dataType : 'json',
						success : function(data) {
							if (data.code == 100) {
								var result = data.data;
								if (result != 'none') {
									var Tbody = $('#notice_table');
									Tbody.html('');
									var s = '';
									$
											.each(
													result,
													function(a, b) {
														s += "<tr style='width: 100%;'>"
																+ "<td style='text-align: center'>"
																+ b.id
																+ "</td>"
																+ "<td style='text-align: center'>"
																+ b.name
																+ "</td>"
																+ "<td style='text-align:center'><a href='#modifymodal' class='btn btn-primary' data-toggle='modal' onclick='modifyItem("
																+ b.id
																+ ")'>修改</a><button class='btn btn-primary' onclick='deltype("+b.id+")'>删除</button></td>"
																+ "</tr>";
													});
									Tbody.html(s);
								}
							}
						}
					});
		});
	</script>
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
