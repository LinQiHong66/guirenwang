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
                       ICO众筹管理
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">众筹管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">修改众筹项目</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>修改众筹项目</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                            <form id="myform" enctype="multipart/form-data" method="POST" class="form-inline">
                                <div class="form-group" style="margin-top: 5px">
                                    	<label class="form-label">众筹图片:</label>&nbsp;&nbsp;
                                    	<img style="width:120px;height:120px" alt="" src="${crowd.data.ico_photo}"><br />
                                    	<label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;&nbsp;
                                    	<input name="photoFile" type="file" id="photoFile"><br />
                                    	<span style="color:red">请上传：350 x 250px 图片</span>
                                    <%--<label class="form-label">上传币种图片:</label>&nbsp;&nbsp;
                                    <input type="submit" name="myfiles" id="myfiles" class="form-control" style="border-radius: 5px; value="上传图片">--%>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    	<label class="form-label">详情图片:</label>&nbsp;&nbsp;
                                    	<img style="width:120px;height:120px" alt="" src="${crowd.data.attr1}"><br />
                                    	<label class="form-label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;&nbsp;
                                    	<input name="photoRemarkFile" type="file" id="photoRemarkFile"><br />
                                    	<span style="color:red">请上传：720 x 360px 详情图片</span>
                                    <%--<label class="form-label">上传币种图片:</label>&nbsp;&nbsp;
                                    <input type="submit" name="myfiles" id="myfiles" class="form-control" style="border-radius: 5px; value="上传图片">--%>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">众筹名称:</label>&nbsp;&nbsp;
                                    <input type="hidden" name="icoNo" id="icoNo" class="form-control" style="border-radius: 5px" value="${crowd.data.ico_no}">
                                    <input type="text" name="icoName" id="icoName" class="form-control" style="border-radius: 5px" value="${crowd.data.ico_name}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">众筹货种:</label>&nbsp;&nbsp;
                                    <%-- <select  class="input-large m-wrap" tabindex="1" id="icoPriceType" name="icoPriceType" value="${data.ico_price_type}">
                                    </select> --%>
                                    <select  class="input-large m-wrap" tabindex="1" id="icoPriceType" name="icoPriceType">
										<c:forEach items="${coin.data}" var="coin">
											<option value="${coin.coin_no}" ${crowd.data.ico_price_type==coin.coin_no ? "selected" : ""}>${coin.coin_name}</option>
										</c:forEach>
									</select>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">目标份数:</label>&nbsp;&nbsp;
                                    <input type="text"id="icoTarget" name="icoTarget" class="form-control" style="border-radius: 5px" value="${crowd.data.ico_target}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">份数单价:</label>&nbsp;&nbsp;
                                    <input type="text"id="icoPrice" name="icoPrice" class="form-control" style="border-radius: 5px" value="${crowd.data.ico_price}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">最低限购份数:</label>&nbsp;&nbsp;
                                    <input type="text"id="minNumber" name="minNumber" class="form-control" style="border-radius: 5px" value="${crowd.data.attr2}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">最高限购份数:</label>&nbsp;&nbsp;
                                    <input type="text" id="maxNumber" name="maxNumber" class="form-control" style="border-radius: 5px" value="${crowd.data.attr3}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">最低分数整数倍:</label>&nbsp;&nbsp;
                                    	<select  class="input-large m-wrap" tabindex="1" id="numberInteger" name="numberInteger">
                                    		<c:if test="${crowd.data.attr4==0}">
												<option value="0" selected="selected">关闭</option>
                                    			<option value="1">开启</option>
											</c:if>
											<c:if test="${crowd.data.attr4==1}">
												<option value="0">关闭</option>
                                    			<option value="1" selected="selected">开启</option>
											</c:if>
										</select>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">最低限购份数与兑换物品比例:</label>&nbsp;&nbsp;
                                    <input type="text" id="convert" name="convert" class="form-control" style="border-radius: 5px" value="${crowd.data.attr5}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">兑换功能:</label>&nbsp;&nbsp;
                                    <select  class="input-large m-wrap" tabindex="1" id="convertFunction" name="convertFunction" >
                                    		<c:if test="${crowd.data.attr6==0}">
												<option value="0" selected="selected">关闭</option>
                                    			<option value="1">开启</option>
											</c:if>
											<c:if test="${crowd.data.attr6==1}">
												<option value="0">关闭</option>
                                    			<option value="1" selected="selected">开启</option>
											</c:if>
										</select>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">开始时间:</label>&nbsp;&nbsp;
                                    <input type="text" id="beginDate" name="beginDate" onclick="laydate()" class="laydate-icon" class="form-control" style="border-radius: 5px" value="${crowd.data.begin_date}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">结束时间:</label>&nbsp;&nbsp;
                                    <input type="text" id="endDate" name="endDate" onclick="laydate()" class="laydate-icon" class="form-control" style="border-radius: 5px" value="${crowd.data.end_date}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">项目简介:</label>&nbsp;&nbsp;
                                    <div class="controls">
                                        <textarea id="editorRemark" placeholder="这里输入内容" style="height: 200px"></textarea>
                                    </div>
                                    <input type="hidden" id="icoRemark"  value="" name="icoRemark"/>
                                    <!-- <input type="text"id="icoRemark" name="icoRemark" class="form-control" style="border-radius: 5px"> -->
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">风险说明:</label>&nbsp;&nbsp;
                                    <div class="controls">
                                        <textarea id="editorExplain" placeholder="这里输入内容" style="height: 200px"></textarea>
                                    </div>
                                    <input type="hidden" id="icoExplain"  value="" name="icoExplain"/>
                                    <!-- <input type="text"id="icoExplain" name="icoExplain" class="form-control" style="border-radius: 5px"> -->
                                </div>
                            </form>
                            <div class="form-group" style="margin-top: 5px">
                                <button id="editCrowd"  class="btn">修改</button>
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
<script src="/app/js/ajax/crowd_edit.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="/app/assets/wangEditor/dist/js/wangEditor.js"></script>
<script type="text/javascript">
var editorRemark = new wangEditor('editorRemark');
editorRemark.create();

var editorExplain = new wangEditor('editorExplain');
editorExplain.create();

editorRemark.$txt.append('${crowd.data.ico_remark}')
editorExplain.$txt.append('${crowd.data.ico_explain}')
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
