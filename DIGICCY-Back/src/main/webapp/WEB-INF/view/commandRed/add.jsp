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
    <link rel="stylesheet" type="text/css" href="/app/assets/wangEditor/dist/css/wangEditor.min.css" />
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
                       口令红包管理
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">口令红包管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">新增口令红包项目</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>新增众筹项目</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">红包项目名称:</label>&nbsp;&nbsp;
                                    <input type="text" name="command_name" id="command_name" class="form-control" style="border-radius: 5px">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">红包奖品类型:</label>&nbsp;&nbsp;
                                    <select  class="input-large m-wrap" tabindex="1" id="command_prize_type" name="command_prize_type" >
                                    	<option value="0" selected="selected">人民币</option>
                                    	<!-- <option value="1" selected="selected">奖品</option>
                                    	<option value="0" >人民币</option> -->
                                    </select>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">奖品名称/货币金额:</label>&nbsp;&nbsp;
                                    <input type="text" id="command_name_price" name="command_name_price" onblur="isNumber(this)" class="form-control" style="border-radius: 5px">
                                	<span style="color:red">注意：类型为人民币，请填写金额；类型为奖品，请填写奖品名称</span>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">中奖人数:</label>&nbsp;&nbsp;
                                    <input type="text" id="number" name="number" class="form-control" style="border-radius: 5px">
                                	<span style="color:red">注意：中奖人数越高，系统执行速度会有所下降，最好中奖人数控制在100以内</span>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">红包项目简介:</label>&nbsp;&nbsp;
                                    <div class="controls">
                                        <textarea id="commandRemark" placeholder="这里输入内容" style="height: 200px"></textarea>
                                    </div>
                                    <input type="hidden" id="command_remark"  value="" name="command_remark"/>
                                    <!-- <input type="text"id="icoRemark" name="icoRemark" class="form-control" style="border-radius: 5px"> -->
                                </div>
                            <div class="form-group" style="margin-top: 5px">
                                <button id="addCommandRed"  class="btn">提交</button>
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
<script src="/app/js/ajax/commandRed_add.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="/app/assets/wangEditor/dist/js/wangEditor.js"></script>
<script type="text/javascript">
var commandRemark = new wangEditor('commandRemark');
commandRemark.create();
</script>
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
