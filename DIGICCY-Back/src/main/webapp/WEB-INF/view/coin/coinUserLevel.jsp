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
    <script src="/app/js/jquery-1.8.3.min.js"></script>
    <script src="/app/js/header.js"></script>
    <link href="/app/assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <link href="/app/assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
    <link href="/app/css/tab.css" rel="stylesheet" />
    <script src="/app/js/ajax/coin_user_level.js"></script>
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
                        虚拟货币信息
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">虚拟货币管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">虚拟货币分红</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>虚拟货币分红</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <button class="btn btn-success" id="btn_add"><i class="icon-plus icon-white"></i>&nbsp;新增</button>
                            <%--<button class="btn btn-inverse" id="btn_update"><i class="icon-pencil icon-white"></i>&nbsp;修改</button>--%>
                            <%--<button class="btn btn-primary" id="btn_delete"><i class="icon-remove icon-white"></i>&nbsp;删除</button>--%>
                            <table id="coin_user_level_table">

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
        <h4>分红信息</h4><br/><br/>
        <form class="form-inline">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币编号:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_no" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币名称:</label>&nbsp;&nbsp;
                <input type="text"  id="coin_name" class="form-control" style="border-radius: 5px">
            </div>
           <div class="form-group" style="margin-top: 5px">
                <label class="form-label">推荐人比例:</label>&nbsp;&nbsp;
                <input type="text"  id="level_one" class="form-control" style="border-radius: 5px">
            </div>
             <div class="form-group" style="margin-top: 5px">
                <label class="form-label">推荐人上级比例:</label>&nbsp;&nbsp;
                <input type="text"  id="level_two" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="add_Role" onclick="addCoin()"><i class="icon-ok"></i> 新增</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
    <!-- 修改角色 -->
    <div class="boxK" id="box_update">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_update"></i></div>
        <h4>修改虚拟货币</h4><br/><br/>
        <form class="form-inline">
            <input type="hidden" id="update_id">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币编号:</label>&nbsp;&nbsp;
                <input type="text" disabled="true" style="" id="update_coin_no" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">货币名称:</label>&nbsp;&nbsp;
                <input type="text"  id="update_coin_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">推荐人比例:</label>&nbsp;&nbsp;
                <input type="text"  id="update_level_one" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">推荐人上级比例:</label>&nbsp;&nbsp;
                <input type="text"  id="update_level_two" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="update_Role" onclick="updateCoin()"><i class="icon-ok"></i> Save</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
    </div>
    <!-- 投票结果 -->
    <div class="boxK" id="box_vote">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_vote"></i></div>
        <h4>投票结果</h4><br/><br/>
        <div id="count" style="width:80%;margin:0 auto">
            <div class="control-group">
                <label class="control-label">支持:</label>
                <div class="controls">
                    <div class="progress progress-striped progress-success" id="sup">
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">反对:</label>
                <div class="controls">
                    <div class="progress progress-striped progress-danger" id="opp">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="boxK" id="box_icon">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_icon"></i></div>
        <h4>货币图标</h4><br/><br/>
        <div class="control-group">
            <div class="controls">
                <div class="fileupload fileupload-new" data-provides="fileupload">
                    <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
                    <div>
                        <form action="/coin/uploadIcon.do" method="post" enctype="multipart/form-data">
                            <input type="hidden" class="default" id="id" name="id"/>
                            <span class="btn btn-file"><span class="fileupload-new">选择图片</span>
                            <span class="fileupload-exists">更改</span>
                            <input type="file" class="default" id="icon" name="icon"/></span>
                            <input type="submit" type="submit" class="btn fileupload-exists" />
                        </form>

                    </div>
                </div>
            </div>
        </div>
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
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/coin_user_level_table.js"></script>

<script type="text/javascript" src="/app/assets/bootstrap/js/bootstrap-fileupload.js"></script>


</body>

</html>