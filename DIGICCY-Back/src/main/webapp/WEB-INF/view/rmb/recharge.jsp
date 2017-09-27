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
                        充值提现管理
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">充值提现管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">人民币充值</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>人民币充值</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                        	<button class="btn btn-success" >&nbsp;总入金额:${sumPrice}</button>
                        	<button class="btn btn-success" >&nbsp;今日总入金额:${dayPrice}</button>
                        	<br>
                            <%--<button class="btn btn-success" id="btn_add"><i class="icon-plus icon-white"></i>&nbsp;新增</button>--%>
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                            <form action="/rmb/getRechargeExcel.do" method="post">
                            <div>
                            	用戶名<input type="text" name='userName' id='names' style='width:20%'/>状态<select style='width:20%' name='state' id='state'>
                            		<option value='-1' selected>请选择</option>
                            		<option value='1'>已到账</option>
                            		<option value='0'>未到账</option>
                            	</select>时间段<input type='date' style='width:20%' name='startDate' id='startDate' />---<input style='width:20%' type='date' name='endDate' id='endDate' />
                            	<br />
                            	<input type="button" value='查询' onclick='search()' /><input type="submit" value='导出Excel' />
                            </div>
                            </form>
                            <table id="recharge_table" style='width:100%;'>
								
                            </table>
                            <div id="rechargeD" align="center">
                            	一共<span id='countItemR'></span>条数据，当前第<span id="curPageR"></span>页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	每页<select onchange="changeItem()" id="pageItemR" style='width:7%'></select>条
                            	<button onclick="overPage()">上一页</button><button onclick="nextPage()">下一页</button>
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
<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/js/ajax/coin.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/rmb-tab.js"></script>
<script type="text/javascript">
	function search(){
		var pageItem = $('#pageItemR').val();
		if(pageItem == null && pageItem == ""){
			pageItem=10;
		}
		selectRecharge(1, pageItem);
	}
	function nextPage(){
		var curPage = $('#curPageR').html();
		var pageItem = $('#pageItemR').val();
		var counItem = $('#countItemR').html();
		var counPage = 1;
		if(counItem%pageItem == 0){
			counPage = counItem/pageItem;
		}else{
			counPage = (counItem/pageItem)+1;
		}
		if(pageItem == null && pageItem == ""){
			pageItem=10;
		}
		if(curPage >= counItem/pageItem){
			curPage = counPage;
		}else{
			curPage = (curPage/1)+1;
		}
		
		selectRecharge(curPage, pageItem);
	}
	function overPage() {
		var curPage = $('#curPageR').html();
		var counItem = $('#countItemR').html();
		var pageItem = $('#pageItemR').val();

		if(pageItem == null && pageItem == ""){
			pageItem=10;
		}
		if(curPage <= 1){
			curPage = 1;
		}else{
			curPage = curPage-1;
		}
		selectRecharge(curPage, pageItem);
	}
	function changeItem(){
		var pageItem = $('#pageItemR').val();
		var curPage = $('#curPageR').html();
		selectRecharge(curPage, pageItem);
	}
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
