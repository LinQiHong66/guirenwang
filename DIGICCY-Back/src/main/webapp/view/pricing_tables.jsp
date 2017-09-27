<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/3 0003
  Time: 上午 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Pricing Tables</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" />
    <link href="assets/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="css/style_responsive.css" rel="stylesheet" />
    <link href="css/style_default.css" rel="stylesheet" id="style_color" />

    <link href="assets/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/uniform/css/uniform.default.css" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<!-- BEGIN HEADER -->
<%@include file="header.jsp" %>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div id="container" class="row-fluid">
    <!-- BEGIN SIDEBAR -->
    <%@include file="left.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div id="main-content">
        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN THEME CUSTOMIZER-->
                    <div id="theme-change" class="hidden-phone">
                        <i class="icon-cogs"></i>
                        <span class="settings">
                            <span class="text">Theme:</span>
                            <span class="colors">
                                <span class="color-default" data-style="default"></span>
                                <span class="color-gray" data-style="gray"></span>
                                <span class="color-purple" data-style="purple"></span>
                                <span class="color-navy-blue" data-style="navy-blue"></span>
                            </span>
                        </span>
                    </div>
                    <!-- END THEME CUSTOMIZER-->
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        Pricing Tables
                        <small>pricing tables samples</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <a href="#"><i class="icon-home"></i></a><span class="divider">&nbsp;</span>
                        </li>
                        <li>
                            <a href="#">Extra</a> <span class="divider">&nbsp;</span>
                        </li>
                        <li><a href="#">Pricing Tables</a><span class="divider-last">&nbsp;</span></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN INLINE NOTIFICATIONS widget-->
                    <div class="widget">
                        <div class="widget-title">
                            <h4><i class="icon-cogs"></i>Pricing Tables</h4>
                            <div class="tools">
                                <a href="javascript:;" class="collapse"></a>
                                <a href="#widget-config" data-toggle="modal" class="config"></a>
                                <a href="javascript:;" class="reload"></a>
                                <a href="javascript:;" class="remove"></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="row-fluid">
                                <div class="pricing-title">
                                    <h2>30 days Free Trial on All Accounts</h2>
                                    <h4>No risk. No hidden fees. Cancel at anytime. </h4>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span3">
                                    <div class="pricing-table">
                                        <div class="pricing-head">
                                            <h3> Micro </h3>
                                            <h4>
                                                <span class="note">$</span>20 <span>Per Month</span></h4>
                                        </div>
                                        <ul>
                                            <li><strong>Free</strong> setup</li>
                                            <li><strong>1</strong> Website</li>
                                            <li><strong>2</strong> Projects</li>
                                            <li><strong>1GB</strong> Storage</li>
                                            <li><strong>$0</strong> Google Adwords Credit</li>
                                        </ul>
                                        <div class="price-actions">
                                            <a class="btn" href="javascript:;">Purchase Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="spance10 visible-phone"></div>
                                <div class="span3">
                                    <div class="pricing-table">
                                        <div class="pricing-head">
                                            <h3> Starter </h3>
                                            <h4>
                                                <span class="note">$</span>30 <span>Per Month</span></h4>
                                        </div>
                                        <ul>
                                            <li><strong>Free</strong> setup</li>
                                            <li><strong>5</strong> Website</li>
                                            <li><strong>10</strong> Projects</li>
                                            <li><strong>15GB</strong> Storage</li>
                                            <li><strong>$30</strong> Google Adwords Credit</li>
                                        </ul>
                                        <div class="price-actions">
                                            <a class="btn" href="javascript:;">Purchase Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="spance10 visible-phone"></div>
                                <div class="span3">
                                    <div class="pricing-table green">
                                        <div class="pricing-head ">
                                            <h3> Business </h3>
                                            <h4>
                                                <span class="note">$</span>60 <span>Per Month</span></h4>
                                        </div>
                                        <ul>
                                            <li><strong>Free</strong> setup</li>
                                            <li><strong>10</strong> Website</li>
                                            <li><strong>20</strong> Projects</li>
                                            <li><strong>Unlimited</strong> Storage</li>
                                            <li><strong>$50</strong> Google Adwords Credit</li>
                                        </ul>
                                        <div class="price-actions">
                                            <a class="btn" href="javascript:;">Purchase Now</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="spance10 visible-phone"></div>
                                <div class="span3">
                                    <div class="pricing-table">
                                        <div class="pricing-head">
                                            <h3> Enterprise </h3>
                                            <h4>
                                                <span class="note">$</span>120 <span>Per Month</span></h4>
                                        </div>
                                        <ul>
                                            <li><strong>Free</strong> setup</li>
                                            <li><strong>20</strong> Website</li>
                                            <li><strong>30</strong> Projects</li>
                                            <li><strong>Unlimited</strong> Storage</li>
                                            <li><strong>$100</strong> Google Adwords Credit</li>
                                        </ul>
                                        <div class="price-actions">
                                            <a class="btn" href="javascript:;">Purchase Now</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END INLINE NOTIFICATIONS widget-->
                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
        <!-- END PAGE CONTAINER-->
    </div>
    <!-- BEGIN PAGE -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<%@include file="footer.jsp" %>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS -->
<!-- Load javascripts at bottom, this will reduce page load time -->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.blockui.js"></script>
<!-- ie8 fixes -->
<!--[if lt IE 9]>
<script src="js/excanvas.js"></script>
<script src="js/respond.js"></script>
<![endif]-->
<script type="text/javascript" src="assets/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="assets/uniform/jquery.uniform.min.js"></script>
<script src="js/scripts.js"></script>
<script>
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
