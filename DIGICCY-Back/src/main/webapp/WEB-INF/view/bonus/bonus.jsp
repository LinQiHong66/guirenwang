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
                        分润管理
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">分润管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">分润管理</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>分润管理</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <button class="btn btn-success" id="btn_add"><i class="icon-plus icon-white"></i>&nbsp;新增</button>
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                            <table id="bonus_table">

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
    <!-- 新增角色 -->
    <div class="boxK" id="box_add">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_add"></i></div>
        <h4>新增分润</h4><br/><br/>
        <form class="form-inline">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润名称:</label>&nbsp;&nbsp;
                <input type="text"  id="name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润币种:</label>&nbsp;&nbsp;
                <select  class="input-large m-wrap" tabindex="1" id="coin">
                </select>
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润总额:</label>&nbsp;&nbsp;
                <input type="text"  id="num" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="add_Role" onclick="addBonus()"><i class="icon-ok"></i> Save</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
    <!-- 新增角色 -->
    <div class="boxK" id="box_update">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_add"></i></div>
        <h4>修改分润</h4><br/><br/>
        <form class="form-inline">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润名称:</label>&nbsp;&nbsp;
                <input type="hidden"  id="up_id" class="form-control" style="border-radius: 5px">
                <input type="text"  id="up_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润币种:</label>&nbsp;&nbsp;
                <select  class="input-large m-wrap" tabindex="1" id="up_coin">
                </select>
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">分润总额:</label>&nbsp;&nbsp;
                <input type="text"  id="up_num" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="add_Role" onclick="updateBonus()"><i class="icon-ok"></i> Save</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="../footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="/app/js/footer.js"></script>
<script src="/app/js/echarts.min.js"></script>
<script src="/app/js/ajax/bonus.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/bonus-update-tab.js"></script>

<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
