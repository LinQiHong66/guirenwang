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
								<form action="/entrust/getExcel.do" method="POST">
									<div>
										用戶名<select name='userName' id='names' style='width: 20%'></select>状态<select
											style='width: 20%' name='state' id='state'>
											<option value='-1' selected>请选择</option>
											<option value='1'>已完成</option>
											<option value='0'>委托中</option>
											<option value='2'>撤销</option>
										</select>时间段<input type='date' style='width: 20%' name='startDate'
											id='startDate' />---<input style='width: 20%' type='date'
											name='endDate' id='endDate' /> <br /> <input type="button"
											value='查询' onclick='selectEntrust(1);' /><input type="submit"
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
	<script type="text/javascript"
		src="/app/assets/bootstrap/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript">
		//获取所有用户
		$.ajax({
			url : '/user/getAllUser.do',
			type : 'GET',
			dataType : 'json',
			success : function(data) {
				if (data.code == 100) {
					var obj = $('#names');
					obj.html('<option value="-1" selected>请选择</option>');
					$.each(data.data, function(a, b) {
						obj.html(obj.html() + '<option value="'+b.user_no+'">'
								+ b.user_no + '--' + b.username + '</option>');
					});
				}
			}
		});
		function nextPage(){
			var pageNum = $('#pageNum').html();
			pageNum ++;
			selectEntrust(pageNum);
		}
		function beforePage(){
			var pageNum = $('#pageNum').html();
			pageNum --;
			selectEntrust(pageNum);
		}
		function selectEntrust(pageNum) {
			var userName = $('#names').val();
			var state = $('#state').val();
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			var countItem = $('#countPage').html();
			var pageItem = $('#pageItem').val();
			var countPage = (countItem%pageItem)==0?(countItem/pageItem):((countItem/pageItem)+1);
			if(pageNum <= 0){
				pageNum = 1;
			}
			if(pageNum > countPage){
				pageNum = countPage;
			}
			console.log(pageNum);
			console.log(countPage);
			console.log(countItem/pageItem);
			$
					.ajax({
						url : '/entrust/getAllEntrustRecord.do',
						type : 'GET',
						dataType : 'json',
						success : function(data) {
							console.log('_________________');
							console.log(data);

							var tab = $('#entrust_all_table');
							tab.html('<thead style="width:100%">'
									+ '<tr style="width:100%">' + '<td>用户编号</td>'
									+ '<td>委托币种</td>' + '<td>委托类型</td>' + '<td>委托价格</td>'
									+ '<td>委托数量(个)</td>' + '<td>成交数量(个)</td>'
									+ '<td>手续费</td>' + '<td>状态</td>' + '<td>委托时间</td>'
									+ '<td>操作</td>' + '</tr>'
									+ '</thead><tbody style="width:100%">');
							if (data.code == 100) {
								var d = data.data;
								console.log(d);
								$
										.each(
												d,
												function(a, b) {
													console.log(b);
													var state = '';
													if (b.state == 0)
														state = "<span style='color: blue'>委托中</span>";
													if (b.state == 1)
														state = "<span style='color: green'>已完成</span>";
													if (b.state == 2)
														state = "撤销";
													if (b.state == 3)
														state = "全部";
													console.log(state);
													var e;
													var id = b.id;
													if (b.state != 0) {
														e = '&nbsp;&nbsp;';
													} else {
														e = '&nbsp;&nbsp;<button class="btn btn-warning" id="btn_update" onclick="updateEntrustManage('
																+ id
																+ ');">'
																+ '<i class="icon-pencil icon-white"></i>撤销</button> &nbsp;&nbsp;';
													}
													console.log(e);
													/**
													 * 
													 */
													var k;

													if (b.entrust_type == 0) {
														k = '买';
													} else {
														k = '卖';
													}
													var str = '<tr style="width:100%;">'

															+ '<td style="width:10%" align="center">'
															+ b.user_no
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.entrust_coin
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ k
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.entrust_price
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.entrust_num
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.deal_num
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.piundatge
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ state
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ b.date
															+ '</td>'
															+ '<td style="width:10%" align="center">'
															+ e + '</td>' + '</tr>';
													tab.html(tab.html() + str);
												});
							}

							tab.html(tab.html() + "</tbody>");
							var numval = parseInt(pageNum);
							//分页部分
							console.log(data.count);
							$('#countPage').html(data.count);
							$('#pageNum').html(numval);
							$('#pageItem').html('');
							//if(data.count>=0){
								if(pageItem == 10){
									$('#pageItem').html($('#pageItem').html()+'<option value="10" selected>10</option>');	
								}else{
									$('#pageItem').html($('#pageItem').html()+'<option value="10">10</option>');
								}
							//}
							if(data.count > 10){
								if(pageItem == 20){
									$('#pageItem').html($('#pageItem').html()+'<option value="20" selected>20</option>');	
								}else{
									$('#pageItem').html($('#pageItem').html()+'<option value="20">20</option>');
								}
							}
							if(data.count > 20){
								if(pageItem == 50){
									$('#pageItem').html($('#pageItem').html()+'<option value="50" selected>50</option>');	
								}else{
									$('#pageItem').html($('#pageItem').html()+'<option value="50">50</option>');
								}
							}
							if(data.count > 50){
								if(pageItem == 100){
									$('#pageItem').html($('#pageItem').html()+'<option value="100" selected>100</option>');	
								}else{
									$('#pageItem').html($('#pageItem').html()+'<option value="100">100</option>');
								}
							}
							
						},
						data : {
							userName : userName,
							state : state,
							startDate : startDate,
							endDate : endDate,
							pageItem : pageItem,
							pageNum : pageNum
						},
						error : function(e) {
							console.log(e.responseText);
						}
					});
		}
	</script>
</body>

<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp"%>
<!-- END FOOTER -->