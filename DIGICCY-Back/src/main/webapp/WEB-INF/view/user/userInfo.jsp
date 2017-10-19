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
                       用户管理
                        <%--<small>simple form layouts</small>--%>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">用户管理</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">用户信息</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                </div>
            </div>
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span3 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>用户信息</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <form class="form-inline">
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">用户编号   :</label>&nbsp;&nbsp;
                                    <label class="form-label">${data.user_no}</label>&nbsp;&nbsp;
                                    <input type="hidden" id="no" class="form-control" style="border-radius: 5px" value="${data.user_no}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">用户账号   :</label>&nbsp;&nbsp;
                                    <label class="form-label">${data.username}</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">真实姓名   :</label>&nbsp;&nbsp;
                                    <label class="form-label">${data.real_name}</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">登录密码   :</label>&nbsp;&nbsp;
                                    <input type="password" id="password" class="form-control" style="border-radius: 5px" value="${data.password}" disabled>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">交易密码   :</label>&nbsp;&nbsp;
                                    <input type="password" id="deal_pwd" class="form-control" style="border-radius: 5px" value="${data.deal_pwd}" disabled>
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">电子邮箱    :</label>&nbsp;&nbsp;
                                    <input type="text" id="mail" class="form-control" style="border-radius: 5px" value="${data.mail}">
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">手机号码     :</label>&nbsp;&nbsp;
                                    <label class="form-label">${data.phone}</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">身份证:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label class="form-label">${data.certificate_num}</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <label class="form-label">支付宝:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label class="form-label">${data.alipay}</label>&nbsp;&nbsp;
                                </div>
                                <div class="form-group" style="margin-top: 5px">
                                    <button type="button" class="btn blue" id="add_Role" onclick="updateInfo()"><i class="icon-ok"></i>修改</button>
                                    <button type="button" class="btn" onclick="updatePass()"><i class=" icon-remove"></i>重置密码</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
                </div>
                <div class="span9 sortable">
                    <!-- BEGIN SAMPLE FORMPORTLET-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-reorder"></i>用户资产</h4>
                            <span class="tools">
                                        <a href="javascript:;" class="icon-chevron-down"></a>
                                        <a href="javascript:;" class="icon-remove"></a>
                            </span>
                        </div>
                        <div class="widget-body">
                            <div class="row-fluid">
                                <div class="span12">
                                    <!--BEGIN TABS-->
                                    <div class="tabbable tabbable-custom">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#tab_1_1" onclick="initWalletTab()" data-toggle="tab">用户资产</a></li>
                                            <li><a href="#tab_1_2" onclick="initEntrustTab()" data-toggle="tab">委托记录</a></li>
                                            <li><a href="#tab_1_3" onclick="initTradeTab()" data-toggle="tab">交易记录</a></li>
                                            <li><a href="#tab_1_4" onclick="initAddressTab()" data-toggle="tab">收货地址</a></li>
                                             <li><a href="#tab_1_5" onclick="initICOTab()" data-toggle="tab">购酒记录</a></li>
                                             <li><a href="#tab_1_6" onclick="initIntegralTab(10,1)" data-toggle="tab">积分获取记录</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab_1_1">
                                                <table id="wallet_table"></table>
                                            </div>
                                            <div class="tab-pane" id="tab_1_2">
                                                <table id="entrust_table"></table>
                                            </div>
                                            <div class="tab-pane" id="tab_1_3">
                                                <table id="deal_table"></table>
                                            </div>
                                            <div class="tab-pane" id="tab_1_4">
                                                <table id="address_table"></table>
                                            </div>
                                              <div class="tab-pane" id="tab_1_5">
                                                <table id="ico_table"></table>
                                            </div>
                                              <div class="tab-pane" id="tab_1_6">
                                                <table id="initIntegral_table"></table>
                                            </div>
                                        </div>
                                    </div>
                                    <!--END TABS-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END SAMPLE FORM PORTLET-->
                </div>
            </div>
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
     <!-- 修改角色 -->
    <div class="boxK" id="box_update">
        <div class="closeK clearfix"><i class="icon-remove icon-white" id="close_update"></i></div>
        <h4>修改收货地址</h4><br/><br/>
        <form class="form-inline">
            <input type="hidden" id="update_id">
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">用户ID:</label>&nbsp;&nbsp;
                <input type="text" disabled="true" style="" id="user_no" class="form-control" style="border-radius: 5px"/>
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">用户名称:</label>&nbsp;&nbsp;
                <input type="text"  id="user_name" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">手机号码:</label>&nbsp;&nbsp;
                <input type="text"  id="user_phone" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">省市地址:</label>&nbsp;&nbsp;
                <input type="text"  id="remark_address" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">详细地址:</label>&nbsp;&nbsp;
                <input type="text"  id="address" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <label class="form-label">邮政编码:</label>&nbsp;&nbsp;
                <input type="text"  id="card" class="form-control" style="border-radius: 5px">
            </div>
            <div class="form-group" style="margin-top: 5px">
                <button type="button" class="btn blue" id="update_Role" onclick="updateAddress()"><i class="icon-ok"></i> Save</button>
                <button type="reset" class="btn"><i class=" icon-remove"></i> Cancel</button>
            </div>
        </form>
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
<script src="/app/assets/bootstrap-table/bootstrap-table.js"></script>
<script src="/app/assets/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/app/js/bootstrap-table/front-user-tab.js"></script>
<script src="/app/js/ajax/userInfo.js"></script>
<script type="text/javascript">
    function updateAddress() {
        var userNo = $("#user_no").val();
        var name = $("#user_name").val();
        var phone = $("#user_phone").val();
        var remarkAddress = $("#remark_address").val();
        var address = $("#address").val();
        var card = $("#card").val();
        $.ajax({
            url:"/user/editAddress.do",
            type: "post",
            dataType: "json",
            data:{
            	userNo:userNo,
                name:name,
                phone:phone,
                remarkAddress:remarkAddress,
                address:address,
                card:card
            },
            success: function (msg) {
                $.alertable.alert(msg.desc);
                $("#box_update").css({"display":"none"});
                window.location.reload();
            }
        });
    }
</script>
<script type="text/javascript">
 

$("#close_update").click(function(){
	$("#box_update").css({"display":"none"});
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
