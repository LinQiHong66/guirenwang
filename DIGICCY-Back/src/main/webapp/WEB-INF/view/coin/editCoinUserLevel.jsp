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
    <script src="/app/assets/laydate/laydate.js"></script>
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
                       虚拟货币交易条件信息
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">虚拟货币交易条件信息</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">新增货币交易条件</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>新增货币交易条件</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">虚拟货币类型:</label>&nbsp;&nbsp;
                                    <input type="hidden" name="id" id="id" class="form-control" style="border-radius: 5px" value="${level.dto.id}">
                                	<select  class="input-large m-wrap" tabindex="1" id="coin_no" name="coin_no" disabled >
										<c:forEach items="${level_coin.coinList}" var="level_coin" >
											<option value="${level_coin.coin_no}" ${level.dto.coin_no==level_coin.coin_no ? "selected" : ""}>${level_coin.coin_name}</option>
										</c:forEach>
									</select>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                					<label class="form-label">经纪人1比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_one" value="${level.dto.level_one}" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
            					<div class="form-group" style="margin-top: 5px">
                					<label class="form-label">经纪人2比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_two" value="${level.dto.level_two}" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
            					<div class="form-group" style="margin-top: 5px">
                					<label class="form-label">子机构比例:</label>&nbsp;&nbsp;
                					<input type="text"  id="level_three" value="${level.dto.level_three}" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                					<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
            					</div>
           					 	<div class="form-group" style="margin-top: 5px">
            					    <label class="form-label">机构比例:</label>&nbsp;&nbsp;
           					     	<input type="text"  id="level_four" value="${level.dto.level_four}" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
           					     	<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
       					    	 </div>
        					    <%-- <div class="form-group" style="margin-top: 5px">
          					      	<label class="form-label">机构比例:</label>&nbsp;&nbsp;
           					     	<input type="text"  id="level_five" value="${level.dto.level_five}" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
           					     	<span style="color:red">注意：请填小数并且比例相加不能大于1</span>
          					 	</div> --%>
          					 	<div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">分红货币类型:</label>&nbsp;&nbsp;
                                	<select  class="input-large m-wrap" tabindex="1" id="level_type">
               					        <option value="0" selected="selected">买：交易数量*手续费*分润比例；卖：交易数量*兑换货币金额*手续费*分润比例</option>
             					   </select>
                              	</div>
          					  	<%-- <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">分红货币类型:</label>&nbsp;&nbsp;
                                	<select  class="input-large m-wrap" tabindex="1" id="level_type">
										<c:forEach items="${coins.data}" var="coins">
											<option value="${coins.coin_no}" ${level.dto.level_type==coins.coin_no ? "selected" : ""}>${coins.coin_name}</option>
										</c:forEach>
									</select>
                              	</div> --%>
          					  	<div class="form-group" style="margin-top: 5px">
             					   <label class="form-label">分红状态:</label>&nbsp;&nbsp;
             					   <select  class="input-large m-wrap" tabindex="1" id="state">
                                    	<c:if test="${level.dto.state==0}">
											<option value="0" selected="selected">禁止</option>
                                    		<option value="1">启用</option>
										</c:if>
										<c:if test="${level.dto.state==1}">
											<option value="0">禁止</option>
                                    		<option value="1" selected="selected">启用</option>
										</c:if>
             					   </select>
           					 	</div>
                            <div class="form-group" style="margin-top: 5px">
                                <button id="editLevel"  class="btn">修改</button>
                                <!-- <input id="submits" type="submit" class="btn" name="files" id="files" class="form-control" style="border-radius: 5px; value="上传图片"> -->
                                <%--<button type="button" class="btn blue" id="add_Role" onclick="add()"><i class="icon-ok"></i>新增</button>--%>
                                <button type="reset" class="btn"><i class=" icon-remove"></i>重置</button>
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
<script src="/app/js/ajax/userCoinLevel.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
//判断是否数字
function isNumber(v) {
	var r = /^[0-9]+.?[0-9]*$/;
	if (!r.test(v.value)) { //isNaN也行的,正则可以随意扩展
		alert('只能输入数字');
		v.value = "0";
	}
}
</script>
<!-- <script type="text/javascript">
$("#command_prize_type").change(function() {
    if($("#command_prize_type").val()=="0") {
    	$("#command_name_price").change(function() {
    		isNumber($("#command_name_price"));
    	})
    	$("#command_name_price").val(null)
    	function isNumber(v) {//判断是否数字
    		var r = /^[0-9]+.?[0-9]*$/;
    		if (!r.test(v.val())) { //isNaN也行的,正则可以随意扩展
    			alert('只能输入数字');
    			v.val(null);
    		}
    	}
    }else {
    	
    	$("#command_name_price").unbind("change")
    }
})
</script> -->
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
